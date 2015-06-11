package com.tpersson.client.core.views.mainwindow

import javafx.beans.property._
import javafx.beans.value.{ChangeListener, ObservableValue}
import javafx.scene.Node

import com.google.inject.Inject
import com.tpersson.client.common.services.logging.Logger
import com.tpersson.client.common.services.navigation.NavigationService
import com.tpersson.client.common.services.session.SessionService
import com.tpersson.client.common.utils.{AsyncCommand, ExecutionContextProvider, ViewModelBase}
import com.tpersson.client.core.services.viewfactory.PageViewFactory
import com.tpersson.client.core.views.pages.loginpage.LoginPageView
import com.tpersson.client.core.views.pages.userprofile.UserProfilePageView
import de.saxsys.mvvmfx.utils.commands.Command

import scala.async.Async.async

class MainWindowViewModel @Inject() (
    logger: Logger,
    navigationService: NavigationService,
    sessionService: SessionService,
    pageViewFactory: PageViewFactory,
    executionContextProvider: ExecutionContextProvider)
  extends ViewModelBase(executionContextProvider) {

  private val _currentPageViewNode = new SimpleObjectProperty[Option[Node]](None)

  val currentPageViewNode: ReadOnlyObjectProperty[Option[Node]] = _currentPageViewNode

  val currentPageTitle = new SimpleStringProperty()

  val isLoggedIn: BooleanProperty = new SimpleBooleanProperty(false)
  val sessionFullName: StringProperty = new SimpleStringProperty()
  val sessionEmail: StringProperty = new SimpleStringProperty()

  val navigateBackCommand: Command = new AsyncCommand(navigateBack, canNavigateBack)

  val showApplicationBar: BooleanProperty = new SimpleBooleanProperty()

  showApplicationBar.bind(isLoggedIn)

  navigationService.currentPageType.addListener(new ChangeListener[Class[_]] {
    override def changed(observable: ObservableValue[_ <: Class[_]], oldValue: Class[_], newValue: Class[_]): Unit = {
      _currentPageViewNode.setValue(pageViewFactory.createViewNode(newValue))

      // todo: add title to PageViewModelBase
      currentPageTitle.setValue(newValue.getSimpleName)

      // todo: make sessionService.currentSession observable instead of doing this everytime
      sessionService.currentSession match {
        case Some(session) =>
          isLoggedIn.setValue(true)
          sessionFullName.setValue(session.fullName)
          sessionEmail.setValue(session.email)
        case None =>
          isLoggedIn.setValue(false)
      }
    }
  })

  navigationService.navigateTo(classOf[LoginPageView])
  //navigationService.navigateTo(classOf[PersonListPageView])

  private def canNavigateBack = {
    navigationService.currentPageType.isNotEqualTo(classOf[LoginPageView])
      .and(navigationService.currentPageType.isNotEqualTo(classOf[UserProfilePageView]))
  }

  private def navigateBack() = async {
    navigationService.navigateBack()
  }
}
