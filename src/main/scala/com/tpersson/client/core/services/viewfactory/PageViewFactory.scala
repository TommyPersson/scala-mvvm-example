package com.tpersson.client.core.services.viewfactory

import javafx.scene.Node

trait PageViewFactory {
  def createViewNode(viewType: Class[_]): Option[Node]
}
