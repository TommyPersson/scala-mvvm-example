package com.tpersson.client.examples.pages.common

import javafx.beans.property.{ReadOnlyStringProperty, SimpleStringProperty}

import com.tpersson.client.examples.services.peopleservice.Person

import scala.beans.BeanProperty

class PersonViewModel(_name: String, _email: String) {
  @BeanProperty val name: ReadOnlyStringProperty = new SimpleStringProperty(_name)
  @BeanProperty val email: ReadOnlyStringProperty = new SimpleStringProperty(_email)

  def this(person: Person) = this(person.name, person.email)
}
