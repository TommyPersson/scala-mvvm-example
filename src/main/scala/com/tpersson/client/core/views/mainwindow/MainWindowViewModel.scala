package com.tpersson.client.core.views.mainwindow

import javafx.beans.property._
import javafx.beans.value.{ObservableValue, ChangeListener}
import javafx.scene.Node

import com.google.inject.Inject
import com.tpersson.client.common.services.logging.Logger
import com.tpersson.client.common.services.navigation.NavigationService
import com.tpersson.client.core.services.viewfactory.PageViewFactory
import com.tpersson.client.core.views.pages.loginpage.LoginPageView
import de.saxsys.mvvmfx.ViewModel

class MainWindowViewModel @Inject() (
    logger: Logger,
    navigationService: NavigationService,
    pageViewFactory: PageViewFactory)
  extends ViewModel {

  private val _currentPageViewNode = new SimpleObjectProperty[Node]()

  val currentPageViewNode: ReadOnlyObjectProperty[Node] = _currentPageViewNode

  navigationService.currentPageType.addListener(new ChangeListener[Class[_]] {
    override def changed(observable: ObservableValue[_ <: Class[_]], oldValue: Class[_], newValue: Class[_]): Unit = {
      val viewType = newValue

      pageViewFactory.createViewNode(viewType) match {
        case Some(node) => _currentPageViewNode.setValue(node)
        case None => logger.log(s"Unable to change view to <$viewType>")
      }
    }
  })

  navigationService.navigateTo(classOf[LoginPageView])
}
