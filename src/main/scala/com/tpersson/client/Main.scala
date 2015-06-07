package com.tpersson.client

import javafx.application.Application

import com.tpersson.client.core.ClientApplication

object Main {
  def main (args: Array[String]): Unit = {
    Application.launch(classOf[ClientApplication], args:_*)
  }
}
