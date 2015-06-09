package com.tpersson.client.testutils

import java.util.concurrent.Executors

import com.tpersson.client.common.utils.{ExecutionContextProvider, ExecutionContexts}

import scala.concurrent.ExecutionContext

class TestExecutionContextProvider extends ExecutionContextProvider {
  override val ui: ExecutionContext = TestExecutionContextProvider.pool
  override val pool: ExecutionContext = TestExecutionContextProvider.pool

  // Update the implicits used in different parts of the application so that they work in tests as well
  ExecutionContexts.Implicits.Ui = ui
  ExecutionContexts.Implicits.Pool = pool
}

object TestExecutionContextProvider {
  private val _executionContext = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(16))

  implicit val pool: ExecutionContext = TestExecutionContextProvider._executionContext
}
