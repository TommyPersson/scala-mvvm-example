package com.tpersson.client.common.services.navigation

import javafx.beans.property.ReadOnlyObjectProperty

import de.saxsys.mvvmfx.FxmlView


trait NavigationService {
  val currentPageType: ReadOnlyObjectProperty[Class[_ <: FxmlView[_]]]

  def navigateTo(viewType: Class[_ <: FxmlView[_]]): Unit
}
