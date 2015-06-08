package com.tpersson.client.core.views.pages.userprofile

import javafx.beans.property.{SimpleStringProperty, StringProperty}

import com.google.inject.Inject
import com.tpersson.client.common.services.logging.Logger
import com.tpersson.client.common.services.navigation.NavigationService
import com.tpersson.client.common.services.session.SessionService
import com.tpersson.client.common.utils.AsyncCommand
import com.tpersson.client.common.utils.ExecutionContexts.Implicits.Ui
import com.tpersson.client.core.views.pages.loginpage.LoginPageView
import de.saxsys.mvvmfx.ViewModel
import de.saxsys.mvvmfx.utils.commands.Command

import scala.async.Async.{async, await}

class UserProfilePageViewModel @Inject() (
    logger: Logger,
    sessionService: SessionService,
    navigationService: NavigationService)
  extends ViewModel {

  val fullName: StringProperty = new SimpleStringProperty()
  val username: StringProperty = new SimpleStringProperty()
  val email: StringProperty = new SimpleStringProperty()
  val message: StringProperty = new SimpleStringProperty()

  val signOutCommand: Command = new AsyncCommand(signOut)

  sessionService.currentSession match {
    case Some(session) =>
      fullName.setValue(session.fullName)
      username.setValue(session.username)
      email.setValue(session.email)
  }

  private def signOut() = async {
    message.setValue("Signing out ...")

    await(sessionService.signOut())

    navigationService.navigateTo(classOf[LoginPageView])

    message.setValue("")
  }
}
