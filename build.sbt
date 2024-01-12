lazy val scala213 = "2.13.12"
lazy val scala3 = "3.3.1"

lazy val root = (project in file("."))
  .enablePlugins(PlayJava)
  .settings(
    name := "play-java-rest-api-example",
    version := "1.0-SNAPSHOT",
    scalaVersion := scala213,
    crossScalaVersions := Seq(scala213, scala3),
    libraryDependencies ++= Seq(
      guice,
      javaJpa,
      "com.h2database" % "h2" % "2.2.224",
      javaJpa.exclude("org.hibernate.javax.persistence", "hibernate-jpa-2.0-api"),
      "org.hibernate" % "hibernate-core" % "6.3.2.Final",
      "com.mysql" % "mysql-connector-j" % "8.0.33",
      "io.dropwizard.metrics" % "metrics-core" % "4.2.22",
      "com.palominolabs.http" % "url-builder" % "1.1.5",
      "net.jodah" % "failsafe" % "2.4.4",
    ),
    libraryDependencies += filters,
    PlayKeys.externalizeResources := false,
    (Test / testOptions) := Seq(Tests.Argument(TestFrameworks.JUnit, "-a", "-v")),
    javacOptions ++= Seq(
      "-Xlint:unchecked",
      "-Xlint:deprecation",
      "-Werror"
    )
  )

val gatlingVersion = "3.9.5"
lazy val gatling = (project in file("gatling"))
  .enablePlugins(GatlingPlugin)
  .settings(
    scalaVersion := scala213,
    crossScalaVersions := Seq(scala213, scala3),
    libraryDependencies ++= Seq(
      "io.gatling.highcharts" % "gatling-charts-highcharts" % gatlingVersion % Test,
      "io.gatling" % "gatling-test-framework" % gatlingVersion % Test
    )
  )
