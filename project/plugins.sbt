resolvers ++= Seq(
  "Typesafe Releases" at "https://repo.typesafe.com/typesafe/releases/",
  "Sonatype OSS Snapshots plugin" at "https://oss.sonatype.org/content/repositories/releases",
  "scala-integration" at "https://scala-ci.typesafe.com/artifactory/scala-integration/"
)

// addSbtPlugin("com.lightbend.sbt" % "sbt-proguard" % "0.4.0")

/** scala js */
addSbtPlugin("org.scala-js" % "sbt-scalajs" % "1.13.0") //"1.1.1" "1.7.1"
//cross
addSbtPlugin("org.portable-scala" % "sbt-scalajs-crossproject" % "1.2.0") //"1.0.0"
//bundler
addSbtPlugin("ch.epfl.scala" % "sbt-scalajs-bundler" % "0.21.1") //"0.18.0" "0.20.0"

resolvers += Resolver.bintrayRepo("oyvindberg", "converter")

addSbtPlugin("org.scalablytyped.converter" % "sbt-converter" % "1.0.0-beta41")

/** other */
//  addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "1.9.4") //"1.3.2"
// addSbtPlugin("io.spray" % "sbt-revolver" % "0.9.1") // for Triggered restart
