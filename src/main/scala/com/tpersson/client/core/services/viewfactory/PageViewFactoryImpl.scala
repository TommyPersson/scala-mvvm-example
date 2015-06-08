package com.tpersson.client.core.services.viewfactory

import javafx.scene.Node

import com.tpersson.client.core.views.pages.loginpage.{LoginPageViewModel, LoginPageView}
import com.tpersson.client.core.views.pages.userprofile.{UserProfilePageViewModel, UserProfilePageView}
import de.saxsys.mvvmfx.FluentViewLoader

// TODO: make more dynamic (register View|ViewModel class pairs?)
class PageViewFactoryImpl extends PageViewFactory {
  private val UserProfilePageViewT = classOf[UserProfilePageView]
  private val LoginPageViewT = classOf[LoginPageView]

  def createViewNode(viewType: Class[_]): Option[Node] = {
    viewType match {
      case LoginPageViewT => Some(FluentViewLoader.fxmlView[LoginPageView, LoginPageViewModel](classOf[LoginPageView]).load().getView)
      case UserProfilePageViewT => Some(FluentViewLoader.fxmlView[UserProfilePageView, UserProfilePageViewModel](classOf[UserProfilePageView]).load().getView)
      case _ => None
    }
  }
}
