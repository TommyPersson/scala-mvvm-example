package com.tpersson.client.common.utils

import scala.concurrent.ExecutionContext

trait ExecutionContextProvider {
  val ui: ExecutionContext
  val background: ExecutionContext
}
