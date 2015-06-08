package com.tpersson.client.core.views.pages.loginpage

import javafx.beans.property.{SimpleStringProperty, StringProperty}

import com.google.inject.Inject
import com.tpersson.client.common.services.logging.Logger
import com.tpersson.client.common.services.navigation.NavigationService
import com.tpersson.client.common.services.session.SessionService
import com.tpersson.client.common.utils.{AsyncCommand, AwaitUtils, ExecutionContextProvider, PageViewModelBase}
import com.tpersson.client.core.views.pages.userprofile.UserProfilePageView

import scala.async.Async._

class LoginPageViewModel @Inject() (
    logger: Logger,
    sessionService: SessionService,
    navigationService: NavigationService,
    executionContextProvider: ExecutionContextProvider)
  extends PageViewModelBase(executionContextProvider) {

  val username: StringProperty = new SimpleStringProperty()
  val password: StringProperty = new SimpleStringProperty()
  val message: StringProperty = new SimpleStringProperty()

  val signInCommand = new AsyncCommand(signIn, canSignIn)
  val registerCommand = new AsyncCommand(register, canRegister)

  private def canSignIn = {
    username.isNotEmpty.and(password.isNotEmpty)
  }

  private def signIn() = async {
    message.setValue(s"Signing in...")

    await(sessionService.signIn(username.get(), password.get())) match {
      case Left(error) =>
        message.setValue(error)
        password.setValue("")
      case Right(session) =>
        navigationService.navigateTo(classOf[UserProfilePageView])
    }

    delayedClearMessage()
  }

  private def canRegister = {
    signInCommand.runningProperty.not()
  }

  private def register() = async {
    logger.log("LoginPageViewModel.register: TODO")
    message.set("Not possible to register right now")

    delayedClearMessage()
  }

  private def delayedClearMessage(): Unit = async {
    await(AwaitUtils.delay(2000))
    message.setValue("")
  }
}
