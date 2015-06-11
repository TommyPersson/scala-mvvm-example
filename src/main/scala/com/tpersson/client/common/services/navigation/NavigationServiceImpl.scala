package com.tpersson.client.common.services.navigation

import javafx.beans.property.{ReadOnlyObjectProperty, SimpleObjectProperty}

import com.google.inject.Inject
import com.tpersson.client.common.services.logging.Logger

import scala.collection.mutable

class NavigationServiceImpl @Inject() (logger: Logger) extends NavigationService {

  private val backStack = new mutable.Stack[PageViewType]

  private val _currentPageType = new SimpleObjectProperty[PageViewType]

  override val currentPageType: ReadOnlyObjectProperty[PageViewType] = _currentPageType

  override def navigateTo(viewType: PageViewType): Unit = {
    val pageType = Option(_currentPageType.getValue)

    if (pageType.isDefined) {
      backStack.push(pageType.get)
    }

    doNavigateTo(viewType, pageType)
  }

  override def navigateBack(): Unit = {
    if (backStack.nonEmpty) {
      val lastPageType = backStack.pop()

      doNavigateTo(lastPageType, None)
    }
  }

  def doNavigateTo(viewType: PageViewType, previousPageType: Option[PageViewType]): Unit = {
    val currentPageTypeName = previousPageType.getOrElse(classOf[Null]).getSimpleName
    logger.log(s"Navigating to <${viewType.getSimpleName}> from <$currentPageTypeName>")

    _currentPageType.setValue(viewType)
  }
}
