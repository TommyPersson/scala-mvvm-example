package com.tpersson.client.core.services.session

import scala.concurrent.Future

trait SessionService {
  def logIn(username: String, password: String): Future[Either[String, Session]]
}
