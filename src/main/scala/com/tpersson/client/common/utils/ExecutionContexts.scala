package com.tpersson.client.common.utils

import scala.concurrent.ExecutionContext

object ExecutionContexts {
  object Implicits {
    implicit var Ui : ExecutionContext = new UiExecutionContext
    implicit var Pool : ExecutionContext = scala.concurrent.ExecutionContext.Implicits.global
  }
}
