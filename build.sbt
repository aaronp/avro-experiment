
ThisBuild / version := "0.0.1"
ThisBuild / scalaVersion := "2.13.4"

lazy val api = project
  .in(file("./api"))
  .settings(name := "api")
  .settings(
		avroStringType := "String",
//  	sourceGenerators in Compile += (avroScalaGenerate in Compile).taskValue,
	  libraryDependencies ++= List(
		"org.scalatest" %% "scalatest" % "3.2.3" % "test",
		"org.apache.avro" % "avro" % "1.10.0",
		"com.julianpeeters" %% "avrohugger-core" % "1.0.0-RC22"))


lazy val app = project
  .in(file("./app"))
  .settings(name := "app")
  .dependsOn(api)
  .settings(libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.3" % "test")
