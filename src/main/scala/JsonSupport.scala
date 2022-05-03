package api

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import model.request.UrlRequest
import model.response.{Result, UrlResponse}
import spray.json.{DefaultJsonProtocol, RootJsonFormat}

trait JsonSupport extends SprayJsonSupport {
  import DefaultJsonProtocol._
  implicit val urlRequestJsonFormat: RootJsonFormat[UrlRequest] = jsonFormat1(UrlRequest)
  implicit val resultJsonFormat: RootJsonFormat[Result] = jsonFormat2(Result)
  implicit val urlResponseJsonFormat: RootJsonFormat[UrlResponse] = jsonFormat2(UrlResponse)

}