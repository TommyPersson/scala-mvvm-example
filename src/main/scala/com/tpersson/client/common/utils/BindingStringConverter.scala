package com.tpersson.client.common.utils

import javafx.util.StringConverter

class BindingStringConverter[T](getter: T => String) extends StringConverter[T] {
  override def fromString(string: String): T = ???
  override def toString(obj: T): String = getter(obj)
}
