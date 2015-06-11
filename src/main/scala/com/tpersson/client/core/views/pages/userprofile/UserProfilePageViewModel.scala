package com.tpersson.client.core.views.pages.userprofile

import javafx.beans.property.{SimpleStringProperty, StringProperty}

import com.google.inject.Inject
import com.tpersson.client.common.services.logging.Logger
import com.tpersson.client.common.services.navigation.NavigationService
import com.tpersson.client.common.services.session.SessionService
import com.tpersson.client.common.utils.{AsyncCommand, ExecutionContextProvider, ViewModelBase}
import com.tpersson.client.core.views.pages.examples.ExamplesPageView
import com.tpersson.client.core.views.pages.loginpage.LoginPageView
import de.saxsys.mvvmfx.utils.commands.Command

import scala.async.Async.{async, await}

class UserProfilePageViewModel @Inject() (
    logger: Logger,
    sessionService: SessionService,
    navigationService: NavigationService,
    executionContextProvider: ExecutionContextProvider)
  extends ViewModelBase(executionContextProvider) {

  val fullName: StringProperty = new SimpleStringProperty()
  val username: StringProperty = new SimpleStringProperty()
  val email: StringProperty = new SimpleStringProperty()
  val message: StringProperty = new SimpleStringProperty()

  val navigateToExamplesPageCommand: Command = new AsyncCommand(navigateToExamplesPage)
  val signOutCommand: Command = new AsyncCommand(signOut)

  sessionService.currentSession match {
    case Some(session) =>
      fullName.setValue(session.fullName)
      username.setValue(session.username)
      email.setValue(session.email)
    case _ => Unit
  }

  private def navigateToExamplesPage() = async {
    navigationService.navigateTo(classOf[ExamplesPageView])
  }

  private def signOut() = async {
    message.setValue("Signing out ...")

    await(sessionService.signOut())

    navigationService.navigateTo(classOf[LoginPageView])

    message.setValue("")
  }
}
