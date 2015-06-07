package com.tpersson.client.common.services.logging

trait Logger {
  def log(message: String): Unit
}
