import scala.sys.process.*
import org.scalajs.linker.interface.{ModuleKind, ModuleInitializer, ModuleSplitStyle}

val scala3Version = "3.3.3"

lazy val installDependencies = Def.task[Unit] {
  val base = baseDirectory.value
  val log = streams.value.log
  if (!(base / "node_module").exists) {
    val pb =
      new java.lang.ProcessBuilder("npm", "install")
        .directory(base)
        .redirectErrorStream(true)

    pb ! log
  }
}

lazy val open = taskKey[Unit]("open vscode")
def openVSCodeTask: Def.Initialize[Task[Unit]] =
  Def
    .task[Unit] {
      val base = baseDirectory.value
      val log = streams.value.log

      val path = base.getCanonicalPath
      s"code --extensionDevelopmentPath=$path" ! log
      ()
    }
    .dependsOn(installDependencies)

lazy val root = project
  .in(file("."))
  .enablePlugins(
    ScalaJSPlugin,
    ScalablyTypedConverterPlugin,
    ScalaJSBundlerPlugin
  )
  .settings(
    scalaVersion := scala3Version,
    moduleName := "vscode-scalajs-hello",
    libraryDependencies ++= Seq(
      "org.scala-js" %%% "scala-js-macrotask-executor" % "1.1.1"
    ),
    scalaJSLinkerConfig ~= {
      _.withModuleKind(ModuleKind.CommonJSModule)
    },
    Compile / fastOptJS / artifactPath := baseDirectory.value / "out" / "extension.js",
    Compile / fullOptJS / artifactPath := baseDirectory.value / "out" / "extension.js",
    Compile / npmDependencies ++= Seq(
      "@types/vscode" -> "1.84.1",
      "vscode-languageclient" -> "7.0.0",
      "idris-ide-client" -> "0.1.6",
      "@types/node" -> "14.17.2"
    ),
    open := openVSCodeTask.dependsOn(Compile / fastOptJS).value
  )
