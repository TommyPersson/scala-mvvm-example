package com.tpersson.client.examples.services.peopleservice

import scala.concurrent.Future

import com.tpersson.client.common.utils.ExecutionContexts.Implicits.Pool

class PeopleServiceImpl extends PeopleService {
  override def fetchPeople(): Future[Either[String, List[Person]]] = {
    Future {
      Right(
        List(
          Person("Person1", "person1@example.com"),
          Person("Person2", "person2@example.com"),
          Person("Person3", "person3@example.com"),
          Person("Person4", "person4@example.com")))
    }
  }
}
