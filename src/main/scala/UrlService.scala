import akka.actor.Actor
import model.request.UrlRequest
import model.response.Result




class UrlService extends Actor {
  override def receive: Receive = {
    case UrlRequest(urls) =>
   sender() ! urls.map(url => Result(url, "...."))
  }
}
