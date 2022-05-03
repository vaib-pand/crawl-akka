ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.8"

lazy val akkaHttpVersion = "10.2.0"
lazy val akkaVersion = "2.6.10"

val akkaActor ="com.typesafe.akka" %% "akka-actor" % akkaVersion
val akkaHttp  ="com.typesafe.akka" %% "akka-http" % akkaHttpVersion
val sprayJson ="com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion
val akkaStream ="com.typesafe.akka" %% "akka-stream" % akkaVersion
val akksl4j = "com.typesafe.akka" %% "akka-slf4j" % akkaVersion

lazy val root = (project in file("."))
  .settings(
    name := "teamextn_project",
    libraryDependencies ++= Seq(akkaActor,akkaHttp,sprayJson,akkaStream,akksl4j)
  )
