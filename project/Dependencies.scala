import sbt._

object Dependencies {
  private val akkaVersion = "2.6.12"
  private val akkaHttpVersion = "10.2.3"
  private val catsVersion = "2.3.0"
  private val tapirVersion = "0.17.9"
  private val circeVersion = "0.13.0"

  val core = Def.setting {
    Seq(
      "com.softwaremill.sttp.tapir" %% "tapir-core" % tapirVersion,
      "com.softwaremill.sttp.tapir" %% "tapir-akka-http-server" % tapirVersion,
      "com.softwaremill.sttp.tapir" %% "tapir-json-circe" % tapirVersion,
      "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
      "com.typesafe.akka" %% "akka-stream" % akkaVersion,
      "com.typesafe.akka" %% "akka-actor-typed" % akkaVersion,
      "io.circe" %% "circe-parser" % circeVersion,
      "io.circe" %% "circe-generic" % circeVersion,
      "org.typelevel" %% "cats-core" % catsVersion
    )
  }
}
