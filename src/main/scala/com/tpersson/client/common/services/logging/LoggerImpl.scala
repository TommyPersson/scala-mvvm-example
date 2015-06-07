package com.tpersson.client.common.services.logging

class LoggerImpl extends Logger {
  override def log(message: String): Unit = {
    println(message);
  }
}
