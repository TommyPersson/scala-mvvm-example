package com.tpersson.client.testutils

import java.time.LocalDateTime

import scala.concurrent.duration._

object TestUtils {
  def waitForCondition(predicate: () => Boolean, timeout: Duration = 100.milliseconds): Boolean = {
    val startTime = LocalDateTime.now()
    val timeoutTime: LocalDateTime = startTime.plusNanos(timeout.toNanos)

    val hasNotTimeout = () => LocalDateTime.now().compareTo(timeoutTime) < 0

    while (hasNotTimeout()) {
      if (predicate()) {
        return true
      }

      Thread.sleep(1)
    }

    false
  }
}
