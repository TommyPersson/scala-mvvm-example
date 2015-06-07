package com.tpersson.client.common.utils

import scala.concurrent.ExecutionContext

object ExecutionContexts {
  object Implicits {
    implicit val Ui : ExecutionContext = new UiExecutionContext
    implicit val ForkJoin : ExecutionContext = scala.concurrent.ExecutionContext.Implicits.global
  }
}
