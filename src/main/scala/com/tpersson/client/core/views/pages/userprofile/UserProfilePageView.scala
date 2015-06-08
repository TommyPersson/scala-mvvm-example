package com.tpersson.client.core.views.pages.userprofile

import java.net.URL
import java.util.ResourceBundle
import javafx.fxml.{FXML, Initializable}
import javafx.scene.control.{Button, Label}

import de.saxsys.mvvmfx.{FxmlView, InjectViewModel}

class UserProfilePageView extends FxmlView[UserProfilePageViewModel] with Initializable {
  @InjectViewModel
  private var viewModel: UserProfilePageViewModel = _

  @FXML
  private var fullNameLabel: Label = _

  @FXML
  private var usernameLabel: Label = _

  @FXML
  private var emailLabel: Label = _

  @FXML
  private var messageLabel: Label = _

  @FXML
  private var signOutButton: Button = _

  override def initialize(location: URL, resources: ResourceBundle): Unit = {
    fullNameLabel.textProperty().bind(viewModel.fullName)
    usernameLabel.textProperty().bind(viewModel.username)
    emailLabel.textProperty().bind(viewModel.email)
    messageLabel.textProperty().bind(viewModel.message)

    signOutButton.disableProperty().bind(viewModel.signOutCommand.executableProperty().not())
  }

  @FXML
  private def signOutAction(): Unit = {
    viewModel.signOutCommand.execute()
  }
}