lazy val root = project
  .in(file("."))
  .settings(
    scalaVersion := "2.13.4",
    name := "akkagorn",
    libraryDependencies ++= Dependencies.core.value
  )
