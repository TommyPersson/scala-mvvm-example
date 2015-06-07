package com.tpersson.client.core.views.mainwindow

import java.net.URL
import java.util.ResourceBundle
import javafx.fxml.{FXML, Initializable}
import javafx.scene.control.{Button, Label}

import de.saxsys.mvvmfx.{FxmlView, InjectViewModel}

class MainWindowView extends FxmlView[MainWindowViewModel] with Initializable {

  @InjectViewModel
  private var viewModel: MainWindowViewModel = _

  @FXML
  private var messageLabel: Label = _

  @FXML
  private var doThingButton: Button = _

  override def initialize(location: URL, resources: ResourceBundle): Unit = {
    messageLabel.textProperty().bind(viewModel.message)

    doThingButton.disableProperty().bind(
      viewModel.doThingCommand.notExecutableProperty())
  }

  @FXML
  def doThingAction(): Unit = {
    viewModel.doThingCommand.execute()
  }
}
