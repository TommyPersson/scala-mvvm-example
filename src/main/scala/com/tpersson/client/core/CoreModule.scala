package com.tpersson.client.core

import com.google.inject.AbstractModule
import com.tpersson.client.common.services.logging.{LoggerImpl, Logger}
import com.tpersson.client.core.services.session.{SessionService, SessionServiceImpl}

class CoreModule extends AbstractModule {
  override def configure(): Unit = {
    bind(classOf[Logger]).to(classOf[LoggerImpl])
    bind(classOf[SessionService]).to(classOf[SessionServiceImpl])
  }
}
