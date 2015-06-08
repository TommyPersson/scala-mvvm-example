package com.tpersson.client.core.views.mainwindow

import java.net.URL
import java.util.ResourceBundle
import javafx.beans.value.{ChangeListener, ObservableValue}
import javafx.fxml.{FXML, Initializable}
import javafx.scene.Node
import javafx.scene.layout.Pane

import de.saxsys.mvvmfx.{FxmlView, InjectViewModel}

class MainWindowView extends FxmlView[MainWindowViewModel] with Initializable {
  @InjectViewModel
  private var viewModel: MainWindowViewModel = _

  @FXML
  private var pageNodeContainerPane: Pane = _

  override def initialize(location: URL, resources: ResourceBundle): Unit = {
    viewModel.currentPageViewNode.addListener(new ChangeListener[Node] {
      override def changed(observable: ObservableValue[_ <: Node], oldValue: Node, newValue: Node): Unit = {
        resetCurrentViewNode()
      }
    })

    resetCurrentViewNode()
  }

  def resetCurrentViewNode(): Unit = {
    pageNodeContainerPane.getChildren.clear()
    pageNodeContainerPane.getChildren.add(viewModel.currentPageViewNode.getValue)
  }
}
