package com.tpersson.client.common.services.session

import scala.concurrent.Future
import com.tpersson.client.common.utils.ExecutionContexts.Implicits.ForkJoin

class SessionServiceImpl extends SessionService {
  var _currentSession: Option[Session] = None

  override def currentSession: Option[Session] = _currentSession

  override def signIn(username: String, password: String): Future[Either[String, Session]] = {
    Future {
      Thread.sleep(2000)

      if (username == "tommy" && password == "secret") {
        val session = new Session("tommy", "tommy.persson@senionlab.com", "Tommy Persson")
        _currentSession = Some(session)

        Right(session)
      } else {
        Left("Invalid username or password")
      }
    }
  }

  override def signOut(): Future[Unit] = {
    Future {
      Thread.sleep(2000)

      _currentSession = None
    }
  }
}
