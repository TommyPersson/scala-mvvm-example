package com.tpersson.client.core

import com.google.inject.{AbstractModule, Scopes}
import com.tpersson.client.common.services.logging.{Logger, LoggerImpl}
import com.tpersson.client.common.services.navigation.{NavigationService, NavigationServiceImpl}
import com.tpersson.client.common.services.session.{SessionService, SessionServiceImpl}
import com.tpersson.client.common.utils.{ExecutionContextProviderImpl, ExecutionContextProvider}
import com.tpersson.client.core.services.viewfactory.{PageViewFactory, PageViewFactoryImpl}
import com.tpersson.client.examples.services.peopleservice.{PeopleServiceImpl, PeopleService}

class CoreModule extends AbstractModule {
  override def configure(): Unit = {
    bind(classOf[Logger]).to(classOf[LoggerImpl]).in(Scopes.SINGLETON)
    bind(classOf[SessionService]).to(classOf[SessionServiceImpl]).in(Scopes.SINGLETON)
    bind(classOf[NavigationService]).to(classOf[NavigationServiceImpl]).in(Scopes.SINGLETON)
    bind(classOf[PageViewFactory]).to(classOf[PageViewFactoryImpl]).in(Scopes.SINGLETON)
    bind(classOf[ExecutionContextProvider]).to(classOf[ExecutionContextProviderImpl])
    bind(classOf[PeopleService]).to(classOf[PeopleServiceImpl])
  }
}
