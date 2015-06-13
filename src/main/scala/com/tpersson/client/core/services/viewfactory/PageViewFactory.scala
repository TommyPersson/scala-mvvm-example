package com.tpersson.client.core.services.viewfactory

import javafx.scene.Node

import com.tpersson.client.common.services.navigation.PageViewType

trait PageViewFactory {
  def createViewNode(viewType: PageViewType): Option[Node]
}
