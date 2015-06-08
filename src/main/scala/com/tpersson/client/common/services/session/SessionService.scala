package com.tpersson.client.common.services.session

import scala.concurrent.Future

trait SessionService {
  def currentSession: Option[Session]

  def signIn(username: String, password: String): Future[Either[String, Session]]

  def signOut(): Future[Unit]
}
