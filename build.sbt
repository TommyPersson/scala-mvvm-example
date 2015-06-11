name := "Client"

version := "1.0"

scalaVersion := "2.11.6"

scalacOptions += "-Xexperimental"

libraryDependencies += "de.saxsys" % "mvvmfx" % "1.2.1"
libraryDependencies += "de.saxsys" % "mvvmfx-guice" % "1.2.1"

libraryDependencies += "io.reactivex" % "rxjava" % "1.0.11"

libraryDependencies += "org.scala-lang.modules" %% "scala-async" % "0.9.2"

libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.4" % "test"
libraryDependencies += "org.scalamock" %% "scalamock-scalatest-support" % "3.2" % "test"