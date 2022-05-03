package model.response

case class UrlResponse(result: List[Result], error:String)
case class Result(url:String,data:String)
