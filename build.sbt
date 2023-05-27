import scala.sys.process.*
import org.scalajs.linker.interface.{
  ModuleKind,
  ModuleInitializer,
  ModuleSplitStyle
}

val scala3Version = "3.2.2"

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
    scalaJSLinkerConfig ~= {
      _.withModuleKind(ModuleKind.CommonJSModule)
    },
    Compile / fastOptJS / artifactPath := baseDirectory.value / "out" / "extension.js",
    Compile / fullOptJS / artifactPath := baseDirectory.value / "out" / "extension.js",
    Compile / npmDependencies ++= Seq(
      "@types/vscode" -> "1.78.1",
      "idris-ide-client" -> "0.1.6",
      "@types/node" -> "14.17.2"
    ),
    open := openVSCodeTask.dependsOn(Compile / fastOptJS).value
  )

/*   "dependencies": {
    "idris-ide-client": "0.1.6"
  },
  "devDependencies": {
    "@types/chai": "4.2.21",
    "@types/mocha": "8.2.3",
    "@types/node": "14.17.2",
    "@types/vscode": "1.44.0",
    "@typescript-eslint/eslint-plugin": "4.28.2",
    "@typescript-eslint/parser": "4.28.2",
    "chai": "4.3.4",
    "eslint": "7.30.0",
    "mocha": "9.2.2",
    "prettier": "2.3.2",
    "ts-node": "10.1.0",
    "typescript": "4.3.5",
    "vsce": "1.100.1"
  }
 */
