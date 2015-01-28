import org.scalastyle.sbt.PluginKeys.{config => styleConfig}

name := "scalafunc-lists"

version := "1.0.0"

scalaVersion := "2.10.1"

scalacOptions ++= Seq("-deprecation", "-feature")

org.scalastyle.sbt.ScalastylePlugin.Settings

styleConfig := file("project/scalastyle_config.xml")

libraryDependencies += "org.scalacheck" %% "scalacheck" % "1.10.1"

libraryDependencies += "org.scalatest" %% "scalatest" % "1.9.1"

libraryDependencies += "junit" % "junit" % "4.11" % "test"

// Good solution will be available only in SBT 0.13 - https://github.com/sbt/sbt/issues/709
// Not expect anybody will add dependencies, so disable warning completely 
conflictWarning in ThisBuild := ConflictWarning.disable
