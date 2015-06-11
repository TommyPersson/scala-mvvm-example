package com.tpersson.client.core.services.viewfactory

import javafx.scene.Node

import com.tpersson.client.core.views.pages.examples.{ExamplesPageView, ExamplesPageViewModel}
import com.tpersson.client.core.views.pages.loginpage.{LoginPageView, LoginPageViewModel}
import com.tpersson.client.core.views.pages.userprofile.{UserProfilePageView, UserProfilePageViewModel}
import com.tpersson.client.examples.pages.listview.{ListViewExamplePageViewModel, ListViewExamplePageView}
import de.saxsys.mvvmfx.FluentViewLoader

// TODO: make more dynamic (register View|ViewModel class pairs?)
//       this is getting out of hand
class PageViewFactoryImpl extends PageViewFactory {
  // Core
  private val UserProfilePageViewT = classOf[UserProfilePageView]
  private val LoginPageViewT = classOf[LoginPageView]
  private val ExamplesPageViewT = classOf[ExamplesPageView]

  // Examples
  private val ListViewExamplePageView = classOf[ListViewExamplePageView]

  def createViewNode(viewType: Class[_]): Option[Node] = {
    viewType match {
      // Core
      case LoginPageViewT => Some(FluentViewLoader.fxmlView[LoginPageView, LoginPageViewModel](classOf[LoginPageView]).load().getView)
      case UserProfilePageViewT => Some(FluentViewLoader.fxmlView[UserProfilePageView, UserProfilePageViewModel](classOf[UserProfilePageView]).load().getView)
      case ExamplesPageViewT => Some(FluentViewLoader.fxmlView[ExamplesPageView, ExamplesPageViewModel](classOf[ExamplesPageView]).load().getView)

      // Examples
      case ListViewExamplePageView => Some(FluentViewLoader.fxmlView[ListViewExamplePageView, ListViewExamplePageViewModel](classOf[ListViewExamplePageView]).load().getView)

      case _ => None
    }
  }
}
