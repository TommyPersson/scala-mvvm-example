package com.tpersson.client.test.core.view.pages.loginpage

import com.tpersson.client.common.services.logging.Logger
import com.tpersson.client.common.services.navigation.NavigationService
import com.tpersson.client.common.services.session.{Session, SessionService}
import com.tpersson.client.core.views.pages.loginpage.LoginPageViewModel
import com.tpersson.client.core.views.pages.userprofile.UserProfilePageView
import com.tpersson.client.testutils.TestExecutionContextProvider.pool
import com.tpersson.client.testutils.{TestExecutionContextProvider, TestSuiteBase, TestUtils}

import scala.concurrent.duration._
import scala.concurrent.{Await, Future, Promise}

class LoginPageViewModelTest extends TestSuiteBase {

  class Fixture {
    val loggerStub = stub[Logger]
    val sessionServiceStub = stub[SessionService]
    val navigationServiceStub = stub[NavigationService]
    val testExecutionContextProvider = new TestExecutionContextProvider
  }

  def makeViewModel(fixture: Fixture): LoginPageViewModel = {
    new LoginPageViewModel(
      fixture.loggerStub,
      fixture.sessionServiceStub,
      fixture.navigationServiceStub,
      fixture.testExecutionContextProvider)
  }

  test("Shall only be able to sign in when username and password are non-empty") {
    // Arrange
    val f = new Fixture
    val viewModel = makeViewModel(f)

    val data =
      Table(
        ("username", "password", "signInEnabled"),
        ("",         "",         false),
        ("username", "",         false),
        ("",         "secret",   false),
        ("username", "secret",   true)
      )

    forAll (data) { (username, password, signInEnabled) => {
      // Act
      viewModel.username.setValue(username)
      viewModel.password.setValue(password)

      // Assert
      assert(signInEnabled == viewModel.signInCommand.executableProperty.get)
    }}
  }

  test("Shall not be able to register while sign in is in progress") {
    val f = new Fixture
    val viewModel = makeViewModel(f)

    // Arrange
    val signInHasStarted = Promise[Unit]()
    val signInAllowedToFinish = Promise[Unit]()

    val signInFake: (String, String) => Future[Either[String, Session]] = (_, _) => Future {
      signInHasStarted.success()
      Await.result(signInAllowedToFinish.future, 1.seconds)
      Left("don't care")
    }

    (f.sessionServiceStub.signIn _).when(*,*).onCall(signInFake)

    // Act
    // Assert
    viewModel.signInCommand.execute()
    Await.result(signInHasStarted.future, 1.seconds)

    assert(!viewModel.registerCommand.executableProperty.get, "register command was executable while signing in")
    signInAllowedToFinish.success()

    assert(TestUtils.waitForCondition(() => viewModel.signInCommand.isNotRunning, 100.milliseconds), "sign in did not finish within timeout")
    assert(viewModel.registerCommand.isExecutable, "register was not executable after sign in finished")
  }

  test("Shall navigate to UserProfilePageView on successful sign in") {
    val f = new Fixture
    val viewModel = makeViewModel(f)

    // Arrange
    (f.sessionServiceStub.signIn _).when("username","secret").returns(Future.successful(Right(new Session("username", "email", "fullName"))))

    // Act
    viewModel.username.setValue("username")
    viewModel.password.setValue("secret")
    viewModel.signInCommand.execute()

    // Assert
    TestUtils.waitForCondition(() => viewModel.signInCommand.isNotRunning)
    (f.navigationServiceStub.navigateTo _).verify(classOf[UserProfilePageView])
  }

  test("Shall show error message on failed sign in") {
    val f = new Fixture
    val viewModel = makeViewModel(f)

    // Arrange
    val errorMessage = "wrong username or password"

    (f.sessionServiceStub.signIn _).when("username","wrongsecret").returns(Future.successful(Left(errorMessage)))

    // Act
    viewModel.username.setValue("username")
    viewModel.password.setValue("wrongsecret")
    viewModel.signInCommand.execute()

    // Assert
    TestUtils.waitForCondition(() => viewModel.signInCommand.isNotRunning)
    assert(viewModel.message.get == errorMessage)
  }
}


