package com.tpersson.client.common.utils

import scala.concurrent.ExecutionContext

class ExecutionContextProviderImpl extends ExecutionContextProvider{
  override val ui: ExecutionContext = new UiExecutionContext
  override val pool: ExecutionContext = scala.concurrent.ExecutionContext.Implicits.global
}
