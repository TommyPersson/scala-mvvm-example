package com.tpersson.client.common.utils

import javafx.beans.binding.BooleanBinding
import javafx.beans.property.{ReadOnlyDoubleProperty, SimpleBooleanProperty, SimpleDoubleProperty}

import com.tpersson.client.common.utils.ExecutionContexts.Implicits.Ui
import de.saxsys.mvvmfx.utils.commands.CommandBase

import scala.concurrent.Future

object BooleanBindings {
  def True = {
    new SimpleBooleanProperty(true).and(new SimpleBooleanProperty(true))
  }
}

class AsyncCommand(
    executeFunction: () => Future[Unit],
    canExecuteBinding: BooleanBinding = BooleanBindings.True)
  extends CommandBase {

  private val _progressProperty: SimpleDoubleProperty = new SimpleDoubleProperty(0.0)

  executable.bind(running.not().and(canExecuteBinding))

  override def execute(): Unit = {
    running.set(true)
    _progressProperty.set(0.0)

    val f = executeFunction()

    f onComplete {
      case _ =>
        running.set(false)
        _progressProperty.set(1.0)
    }
  }

  override def getProgress: Double = _progressProperty.get()

  override def progressProperty(): ReadOnlyDoubleProperty = _progressProperty
}
