package com.tpersson.client.core.views.pages.loginpage

import java.net.URL
import java.util.ResourceBundle
import javafx.fxml.{FXML, Initializable}
import javafx.scene.control._

import de.saxsys.mvvmfx.{FxmlView, InjectViewModel}

class LoginPageView extends FxmlView[LoginPageViewModel] with Initializable {
  @InjectViewModel
  private var viewModel: LoginPageViewModel = _

  @FXML
  private var usernameTextField: TextField = _

  @FXML
  private var passwordField: PasswordField = _

  @FXML
  private var messageLabel: Label = _

  @FXML
  private var signInButton: Button = _

  @FXML
  private var registerHyperlink: Hyperlink = _

  override def initialize(location: URL, resources: ResourceBundle): Unit = {
    usernameTextField.textProperty().bindBidirectional(viewModel.username)
    passwordField.textProperty().bindBidirectional(viewModel.password)
    messageLabel.textProperty().bindBidirectional(viewModel.message)

    usernameTextField.disableProperty().bind(viewModel.signInCommand.runningProperty())
    passwordField.disableProperty().bind(viewModel.signInCommand.runningProperty())
    signInButton.disableProperty().bind(viewModel.signInCommand.executableProperty().not())
    registerHyperlink.disableProperty().bind(viewModel.registerCommand.executableProperty().not())
  }

  @FXML
  private def signInButtonAction(): Unit = {
    viewModel.signInCommand.execute()
  }

  @FXML
  private def registerHyperlinkAction(): Unit = {
    viewModel.registerCommand.execute()
  }
}
