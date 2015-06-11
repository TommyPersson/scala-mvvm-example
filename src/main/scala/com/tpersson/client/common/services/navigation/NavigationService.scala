package com.tpersson.client.common.services.navigation

import javafx.beans.property.ReadOnlyObjectProperty


trait NavigationService {
  val currentPageType: ReadOnlyObjectProperty[PageViewType]

  def navigateTo(viewType: PageViewType): Unit

  def navigateBack(): Unit
}
