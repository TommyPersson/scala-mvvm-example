package com.tpersson.client.examples.pages.listview

import java.net.URL
import java.util.ResourceBundle
import javafx.beans.binding.{Bindings, StringExpression}
import javafx.fxml.{FXML, Initializable}
import javafx.scene.control.cell.TextFieldListCell
import javafx.scene.control.{Label, ListView}
import javafx.scene.layout.Pane

import com.tpersson.client.common.utils.BindingStringConverter
import com.tpersson.client.examples.pages.common.PersonViewModel
import de.saxsys.mvvmfx.{FxmlView, InjectViewModel}

class ListViewExamplePageView extends FxmlView[ListViewExamplePageViewModel] with Initializable {
  @InjectViewModel
  var viewModel: ListViewExamplePageViewModel = _

  @FXML var selectAPersonLabel: Label = _
  @FXML var personDetailsPane: Pane = _

  @FXML var personNameLabel: Label = _
  @FXML var personEmailLabel: Label = _

  @FXML var personListView: ListView[PersonViewModel] = _

  override def initialize(location: URL, resources: ResourceBundle): Unit = {
    personListView.setItems(viewModel.people)
    personListView.setCellFactory(createStringCellFactory[PersonViewModel](p => p.name))

    viewModel.selectedPerson.bind(personListView.getSelectionModel.selectedItemProperty)

    selectAPersonLabel.visibleProperty().bind(viewModel.selectedPerson.isNull)
    personDetailsPane.visibleProperty().bind(viewModel.selectedPerson.isNotNull)

    personNameLabel.textProperty().bind(Bindings.selectString(viewModel.selectedPerson, "name", "value"))
    personEmailLabel.textProperty().bind(Bindings.selectString(viewModel.selectedPerson, "email", "value"))
  }

  def createStringCellFactory[T](propertyAccessor: T => StringExpression) = {
    TextFieldListCell.forListView[T](new BindingStringConverter[T](t => propertyAccessor(t).getValue))
  }
}


