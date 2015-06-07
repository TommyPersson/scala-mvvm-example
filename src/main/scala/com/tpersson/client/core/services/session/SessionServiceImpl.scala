package com.tpersson.client.core.services.session

import scala.concurrent.Future
import com.tpersson.client.common.utils.ExecutionContexts.Implicits.ForkJoin

class SessionServiceImpl extends SessionService {
  override def logIn(username: String, password: String): Future[Either[String, Session]] = {
    Future {
      Thread.sleep(2000)

      if (username == "tommy" && password == "secret") {
        Right(new Session("tommy", "tommy.persson@senionlab.com","Tommy Persson"))
      } else {
        Left("Invalid username or password")
      }
    }
  }
}
