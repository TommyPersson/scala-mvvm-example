package com.tpersson.client.core.services.viewfactory

import javafx.scene.Node

import com.tpersson.client.common.services.navigation.PageViewType
import de.saxsys.mvvmfx.{FluentViewLoader, FxmlView, ViewModel}

class PageViewFactoryImpl extends PageViewFactory {

  def createViewNode(viewType: PageViewType): Option[Node] = {
    Some(FluentViewLoader.fxmlView[FxmlView[_ <: ViewModel], ViewModel](viewType).load().getView)
  }
}
