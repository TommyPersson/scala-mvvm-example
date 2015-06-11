package com.tpersson.client.core.views.pages.userprofile

import java.net.URL
import java.util.ResourceBundle
import javafx.fxml.{FXML, Initializable}
import javafx.scene.control.{ProgressIndicator, Button, Label}

import de.saxsys.mvvmfx.{FxmlView, InjectViewModel}

class UserProfilePageView extends FxmlView[UserProfilePageViewModel] with Initializable {
  @InjectViewModel
  private var viewModel: UserProfilePageViewModel = _

  @FXML private var fullNameLabel: Label = _
  @FXML private var usernameLabel: Label = _
  @FXML private var emailLabel: Label = _

  @FXML private var signOutButton: Button = _

  @FXML private var messageLabel: Label = _
  @FXML private var signOutProgressIndicator: ProgressIndicator = _

  override def initialize(location: URL, resources: ResourceBundle): Unit = {
    fullNameLabel.textProperty().bind(viewModel.fullName)
    usernameLabel.textProperty().bind(viewModel.username)
    emailLabel.textProperty().bind(viewModel.email)

    signOutButton.disableProperty().bind(viewModel.signOutCommand.executableProperty().not())

    signOutProgressIndicator.visibleProperty().bind(viewModel.signOutCommand.runningProperty())
    messageLabel.textProperty().bind(viewModel.message)
  }

  @FXML
  private def showExamplesAction(): Unit = {
    viewModel.navigateToExamplesPageCommand.execute()
  }

  @FXML
  private def signOutAction(): Unit = {
    viewModel.signOutCommand.execute()
  }
}
