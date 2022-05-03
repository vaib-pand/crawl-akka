import akka.actor.{ActorRef, ActorSystem, Props}
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer
import routes.Routes

import scala.concurrent.Await
import scala.concurrent.duration.Duration

object Main extends App with Routes {

  import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
 import api.JsonSupport
  implicit val system: ActorSystem = ActorSystem("url-service")

  implicit val materializer: ActorMaterializer = ActorMaterializer()

  implicit val ec: scala.concurrent.ExecutionContext = scala.concurrent.ExecutionContext.global

  val modelRegistryActor: ActorRef = system.actorOf(Props[UrlService], "urlServiceActor")

  lazy val routes: Route = modelRoutes

  Http().bindAndHandle(routes, "localhost", 8115)

  println(s"Server online at http://localhost:8115/")

  Await.result(system.whenTerminated, Duration.Inf)

}