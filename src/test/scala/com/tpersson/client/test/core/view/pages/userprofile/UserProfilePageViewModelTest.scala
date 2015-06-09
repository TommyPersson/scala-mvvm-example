package com.tpersson.client.test.core.view.pages.userprofile

import com.tpersson.client.common.services.logging.Logger
import com.tpersson.client.common.services.navigation.NavigationService
import com.tpersson.client.common.services.session.SessionService
import com.tpersson.client.core.views.pages.loginpage.LoginPageView
import com.tpersson.client.core.views.pages.userprofile.UserProfilePageViewModel
import com.tpersson.client.testutils.{TestExecutionContextProvider, TestSuiteBase, TestUtils}

import scala.concurrent.Future

class UserProfilePageViewModelTest extends TestSuiteBase {

  class Fixture {
    val loggerStub = stub[Logger]
    val sessionServiceStub = stub[SessionService]
    val navigationServiceStub = stub[NavigationService]
    val testExecutionContextProvider = new TestExecutionContextProvider
  }

  def makeViewModel(fixture: Fixture): UserProfilePageViewModel = {
    new UserProfilePageViewModel(
      fixture.loggerStub,
      fixture.sessionServiceStub,
      fixture.navigationServiceStub,
      fixture.testExecutionContextProvider)
  }

  test("Shall navigate to LoginPageView when signing out") {
    // Arrange
    val f = new Fixture
    val viewModel = makeViewModel(f)

    (f.sessionServiceStub.signOut _).when().returns(Future.successful())

    // Act
    viewModel.signOutCommand.execute()

    // Assert
    TestUtils.waitForCondition(() => viewModel.signOutCommand.isNotRunning)
    (f.navigationServiceStub.navigateTo _).verify(classOf[LoginPageView])
  }
}


