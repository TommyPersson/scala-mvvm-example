package com.tpersson.client.common.utils

import javafx.application.Platform

import scala.concurrent.ExecutionContext

class UiExecutionContext extends ExecutionContext {
  override def execute(runnable: Runnable): Unit = {
    Platform.runLater(runnable)
  }

  override def reportFailure(cause: Throwable): Unit = {
  }
}
