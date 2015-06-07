package com.tpersson.client.core.views.pages.loginpage

import javafx.beans.property.{SimpleStringProperty, StringProperty}

import com.google.inject.Inject
import com.tpersson.client.common.services.logging.Logger
import com.tpersson.client.common.utils.ExecutionContexts.Implicits.Ui
import com.tpersson.client.common.utils.{AsyncCommand, AwaitUtils}
import com.tpersson.client.core.services.session.SessionService
import de.saxsys.mvvmfx.ViewModel

import scala.async.Async._

class LoginPageViewModel @Inject() (
    val logger: Logger,
    val sessionService: SessionService)
  extends ViewModel {

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

    await(sessionService.logIn(username.get(), password.get())) match {
      case Left(error) => message.setValue(error)
      case Right(session) => message.setValue(s"Welcome ${session.fullName}!")
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
