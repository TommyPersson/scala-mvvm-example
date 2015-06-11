package com.tpersson.client.core.views.pages.examples

import javafx.beans.property.{ObjectProperty, SimpleObjectProperty}

import com.google.inject.Inject
import com.tpersson.client.common.services.navigation.{NavigationService, PageViewType}
import com.tpersson.client.common.utils.{AsyncCommand, ExecutionContextProvider, ViewModelBase}
import com.tpersson.client.examples.pages.listview.ListViewExamplePageView

import scala.async.Async.async

class ExamplesPageViewModel @Inject() (
    navigationService: NavigationService,
    executionContextProvider: ExecutionContextProvider)
  extends ViewModelBase(executionContextProvider) {

  val exampleToShow: ObjectProperty[PageViewType] = new SimpleObjectProperty[PageViewType]

  val examplePages: Seq[PageViewType] = Seq(
    classOf[ListViewExamplePageView]
  )

  val showExampleCommand = new AsyncCommand(showExample)

  private def showExample() = async {
    navigationService.navigateTo(exampleToShow.get)
  }
}
