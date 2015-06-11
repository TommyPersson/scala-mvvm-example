package com.tpersson.client.core.views.mainwindow

import java.net.URL
import java.util.ResourceBundle
import javafx.beans.value.{ChangeListener, ObservableValue}
import javafx.fxml.{FXML, Initializable}
import javafx.scene.Node
import javafx.scene.control.{Button, Label}
import javafx.scene.layout.{Pane, AnchorPane}

import de.saxsys.mvvmfx.{FxmlView, InjectViewModel}

class MainWindowView extends FxmlView[MainWindowViewModel] with Initializable {
  @InjectViewModel
  private var viewModel: MainWindowViewModel = _

  @FXML var pageNodeContainerPane: AnchorPane = _

  @FXML var applicationBarPane: Pane = _

  @FXML var navigateBackButton: Button = _
  @FXML var pageTitleLabel: Label = _

  @FXML var nameLabel: Label = _
  @FXML var emailLabel: Label = _

  override def initialize(location: URL, resources: ResourceBundle): Unit = {
    nameLabel.textProperty().bind(viewModel.sessionFullName)
    emailLabel.textProperty().bind(viewModel.sessionEmail)

    navigateBackButton.disableProperty().bind(viewModel.navigateBackCommand.executableProperty().not())
    pageTitleLabel.textProperty().bind(viewModel.currentPageTitle)

    applicationBarPane.visibleProperty().bind(viewModel.showApplicationBar)

    viewModel.currentPageViewNode.addListener(new ChangeListener[Option[Node]] {
      override def changed(observable: ObservableValue[_ <: Option[Node]], oldValue: Option[Node], newValue: Option[Node]): Unit = {
        resetCurrentViewNode()
      }
    })

    resetCurrentViewNode()
  }

  def resetCurrentViewNode(): Unit = {
    val pageNode = viewModel.currentPageViewNode.getValue.getOrElse(new Label("dun goofed"))

    pageNodeContainerPane.getChildren.clear()
    pageNodeContainerPane.getChildren.add(pageNode)

    AnchorPane.setLeftAnchor(pageNode, 0.0)
    AnchorPane.setTopAnchor(pageNode, 0.0)
    AnchorPane.setRightAnchor(pageNode, 0.0)
    AnchorPane.setBottomAnchor(pageNode, 0.0)
  }

  @FXML
  def navigateBackAction() = {
    viewModel.navigateBackCommand.execute()
  }
}
