package com.tpersson.client.core.views.mainwindow

import javafx.beans.property._

import com.google.inject.Inject
import com.tpersson.client.common.utils.ExecutionContexts.Implicits.Ui
import com.tpersson.client.common.utils.{AsyncCommand, AwaitUtils}
import com.tpersson.client.common.services.logging.Logger
import com.tpersson.client.core.services.session.SessionService
import de.saxsys.mvvmfx.ViewModel
import de.saxsys.mvvmfx.utils.commands.Command

import scala.async.Async.{async, await}
import scala.concurrent.Future

class MainWindowViewModel @Inject() (
    val logger: Logger,
    val sessionService: SessionService)
  extends ViewModel {

  private val originalMessage = "Press button to login"

  val message: StringProperty = new SimpleStringProperty(originalMessage)

  val doThingCommand: Command = new AsyncCommand(doThing)

  private def doThing(): Future[Unit] = async {
    message.setValue(s"Logging in...")

    await(sessionService.logIn("tommy", "secret")) match {
      case Left(error) => message.setValue(s"Login failed: $error")
      case Right(session) => message.setValue(s"Welcome ${session.fullName}!")
    }

    delayedRestoreMessage()
  }

  private def delayedRestoreMessage(): Unit = async {
    await(AwaitUtils.delay(2000))
    message.setValue(originalMessage)
  }
}
