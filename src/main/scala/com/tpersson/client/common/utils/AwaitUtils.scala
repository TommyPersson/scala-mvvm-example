package com.tpersson.client.common.utils

import scala.concurrent.Future

import com.tpersson.client.common.utils.ExecutionContexts.Implicits.ForkJoin

object AwaitUtils {
  def delay(millis: Long): Future[Unit] = {
    Future { Thread.sleep(millis) }
  }
}
