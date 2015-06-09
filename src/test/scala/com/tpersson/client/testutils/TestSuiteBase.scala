package com.tpersson.client.testutils

import org.scalamock.scalatest.MockFactory
import org.scalatest.FunSuite
import org.scalatest.prop.TableDrivenPropertyChecks

abstract class TestSuiteBase extends FunSuite
   with MockFactory
   with TableDrivenPropertyChecks {
 }
