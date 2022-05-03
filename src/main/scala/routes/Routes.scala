package routes

import akka.actor.{ActorRef, ActorSystem}
import akka.event.Logging
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.directives.RouteDirectives.complete
import akka.pattern.ask
import akka.util.Timeout
import api.JsonSupport
import model.request.UrlRequest
import model.response.{Result, UrlResponse}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.concurrent.duration._

trait Routes extends JsonSupport {

  implicit def system: ActorSystem

  implicit lazy val timeout = Timeout(10.seconds)

  lazy val log = Logging(system, classOf[Routes])

  def modelRegistryActor: ActorRef

  lazy val modelRoutes: Route =
    pathPrefix("api") {
    post {
      concat(
        path("crawl") {
          entity(as[UrlRequest]) { tran =>
            val aggResult: Future[List[Result]] = {
              (modelRegistryActor ? tran).mapTo[List[Result]]
            }
            onSuccess(aggResult)
            { performed =>
              complete((StatusCodes.OK, UrlResponse(performed,"")))
            }
          }
        }
      )
    }
    }
}
