import sbt._
import Keys._
import GatlingPlugin._

object GatlingSbtExampleBuild extends Build {

  lazy val mainProj = Project(
    id = "main-proj",
    base = file("."),
    settings = Defaults.defaultSettings ++ Seq(
      scalaVersion := "2.10.1",
      resolvers += "Sonatype Releases"  at "http://oss.sonatype.org/content/repositories/releases",
      libraryDependencies += "org.specs2" %% "specs2" % "1.14" % "test"
    )
  )

  lazy val gatlingTestProj = Project(
    id = "loadtest-proj",
    base = file("loadtest"),
    settings = Defaults.defaultSettings ++ Seq(
      scalaVersion := "2.9.2",
      //Ensure we get the 2.9.2 version of the test framework.
      libraryDependencies += "net.tbennett" % "gatling-sbt-test-framework_2.9.2" % "0.0.1-SNAPSHOT" % "lt"
    )
  ).configs(LoadTest)
   .settings(inConfig(LoadTest)(GatlingPlugin.gatlingSettings): _*) .settings(gatlingConfFile := file("blah"))

}