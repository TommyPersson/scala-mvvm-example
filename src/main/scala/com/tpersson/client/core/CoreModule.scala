package com.tpersson.client.core

import com.google.inject.AbstractModule
import com.google.inject.Scopes.SINGLETON
import com.tpersson.client.common.services.logging.{LoggerImpl, Logger}
import com.tpersson.client.common.services.navigation.{NavigationServiceImpl, NavigationService}
import com.tpersson.client.common.services.session.{SessionService, SessionServiceImpl}
import com.tpersson.client.core.services.viewfactory.{PageViewFactoryImpl, PageViewFactory}

class CoreModule extends AbstractModule {
  override def configure(): Unit = {
    bind(classOf[Logger]).to(classOf[LoggerImpl]).in(SINGLETON)
    bind(classOf[SessionService]).to(classOf[SessionServiceImpl]).in(SINGLETON)
    bind(classOf[NavigationService]).to(classOf[NavigationServiceImpl]).in(SINGLETON)
    bind(classOf[PageViewFactory]).to(classOf[PageViewFactoryImpl]).in(SINGLETON)
  }
}
