package com.tpersson.client.common.services.navigation

import javafx.beans.property.{ReadOnlyObjectProperty, SimpleObjectProperty}

import com.google.inject.Inject
import com.tpersson.client.common.services.logging.Logger
import de.saxsys.mvvmfx.FxmlView

class NavigationServiceImpl @Inject() (logger: Logger) extends NavigationService {
  private val _currentPageType = new SimpleObjectProperty[Class[_ <: FxmlView[_]]]

  override val currentPageType: ReadOnlyObjectProperty[Class[_ <: FxmlView[_]]] = _currentPageType

  override def navigateTo(viewType: Class[_ <: FxmlView[_]]): Unit = {
    val currentPageTypeName = if (_currentPageType.getValue != null) _currentPageType.getValue.getSimpleName else "None"
    
    logger.log(s"Navigating to <${viewType.getSimpleName}> from <$currentPageTypeName>")
    _currentPageType.setValue(viewType)
  }
}
