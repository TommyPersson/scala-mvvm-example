package com.tpersson.client.common.utils

import de.saxsys.mvvmfx.ViewModel

import scala.concurrent.ExecutionContext

class ViewModelBase(executionContextProvider: ExecutionContextProvider) extends ViewModel {
  implicit val uiExecutionContext: ExecutionContext = executionContextProvider.ui
}
