lazy val root = project
  .in(file("."))
  .settings(
    scalaVersion := "2.13.4",
    name := "akkagorn",
    libraryDependencies ++= Dependencies.root.value,
    semanticdbEnabled := true, // enable SemanticDB
    semanticdbVersion := scalafixSemanticdb.revision, // use Scalafix compatible version
    scalacOptions ++= List(
      "-Yrangepos", // required by SemanticDB compiler plugin
      "-Wunused:imports" // required by `RemoveUnused` rule
    ) // required for remove unused imports
  )
