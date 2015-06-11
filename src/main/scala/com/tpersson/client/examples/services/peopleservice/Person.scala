package com.tpersson.client.examples.services.peopleservice

class Person(val name: String, val email: String) {
}

object Person {
  def apply(name: String, email: String): Person = {
    new Person(name, email)
  }
}
