package com.tpersson.client.examples.services.peopleservice

import scala.concurrent.Future

trait PeopleService {
  def fetchPeople(): Future[Either[String, List[Person]]]
}
