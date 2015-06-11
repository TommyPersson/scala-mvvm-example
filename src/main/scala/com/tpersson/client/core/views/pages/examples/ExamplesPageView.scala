package com.tpersson.client.core.views.pages.examples

import java.net.URL
import java.util.ResourceBundle
import javafx.event.EventHandler
import javafx.fxml.{FXML, Initializable}
import javafx.geometry.Insets
import javafx.scene.control.Button
import javafx.scene.input.MouseEvent
import javafx.scene.layout.VBox

import com.tpersson.client.common.services.navigation.PageViewType
import de.saxsys.mvvmfx.{FxmlView, InjectViewModel}

class ExamplesPageView extends FxmlView[ExamplesPageViewModel] with Initializable {
  @InjectViewModel
  private var viewModel: ExamplesPageViewModel = _

  @FXML var examplesPane: VBox = _

  override def initialize(location: URL, resources: ResourceBundle): Unit = {
    viewModel.examplePages.map(createExampleButton).foreach(b => {
      VBox.setMargin(b, new Insets(5,20,5,20))
      examplesPane.getChildren.add(b)
    })
  }

  private def createExampleButton(examplePageType: PageViewType): Button = {
    val button = new Button()
    button.setText(examplePageType.getSimpleName)
    button.setUserData(examplePageType)

    button.setOnMouseClicked(new EventHandler[MouseEvent] {
      override def handle(event: MouseEvent): Unit = {
        val pageViewType = for {
          source <- Option(event.getSource)
          button <- Option(source.asInstanceOf[Button])
          userDate <- Option(button.getUserData)
          pageViewType <- Option(userDate.asInstanceOf[PageViewType])
        } yield pageViewType

        if (pageViewType.isDefined) {
          viewModel.exampleToShow.setValue(pageViewType.get)
          viewModel.showExampleCommand.execute()
        }
      }
    })

    button
  }
}