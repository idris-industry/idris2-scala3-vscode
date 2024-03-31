# idris2-scala3-vscode
idris2 vscode extension written in Scala 3

# reference
forked from https://github.com/TheElectronWill/vscode-scalajs-hello (scala 3)

https://github.com/bamboo/idris2-lsp-vscode

https://github.com/meraymond2/idris-vscode

https://github.com/pme123/vscode-scalajs-hello



## VSCode Extension in Scala.js

This Project is a port of the [helloworld-minimal-sample] to [ScalaJS]. It's an update of [vscode-scalajs-hello](https://github.com/pme123/vscode-scalajs-hello).

Please check the VSCode documentation for more general information.

## Your first Extension
A step-by-step tutorial for **Scala.js** using this example project.

Here is the original: [visualstudio.com/api/get-started](https://code.visualstudio.com/api/get-started/your-first-extension)

### Setup

* Install the extensions to develop: **Metals**, **Scala syntax**

* Clone this project

* Open the project in VSCode, run the `import build` task with Metals (it should display a popup automatically).

* Open the terminal, run

```
sbt
```
You're now in the SBT console. Great! Let's compile to see if everything works:

```
compile
```

The first time it may take a few minutes, because Scala will use "ScalablyTyped" to analyze the types of the VSCode extension API. Thanks to this, it can typecheck your code and catch mistakes before you run the extension!

* In the SBT console, transpile the extension to Javascript and open a new VSCode window with the extension loaded:

```
open
```

This will run `fastOptJS` and then open the Extension Host of VSCode.

* Run the Hello World command from the Command Palette (`⇧⌘P`) in the new VSCode window.
* Type `hello` and select `Hello World`.
  * You should see a Notification _Hello World!_.

## Project structure

The project uses the following:
* [SBT] build tool for building the project
* [Scala.js] for general coding
* [Scalably Typed] for JavaScript facades
* [scalajs-bundler] for bundling the JavaScript dependencies

SBT is configured with the `build.sbt` file. Scala.js, ScalablyTyped and the bundler are SBT plugins. With these, SBT manages your JavaScript `npm` dependencies. You should never have to run `npm` directly, simply edit the `npmDependencies` settings in `build.sbt`.

[accessible-scala]: https://marketplace.visualstudio.com/items?itemName=scala-center.accessible-scala
[helloworld-minimal-sample]: https://github.com/Microsoft/vscode-extension-samples/tree/master/helloworld-minimal-sample
[Scalably Typed]: https://github.com/ScalablyTyped/Converter
[SBT]: https://www.scala-sbt.org
[ScalaJS]: http://www.scala-js.org
[scalajs-bundler]: https://github.com/scalacenter/scalajs-bundler

## How do I code in Scala.js?

In general, javascript functions and classes can be used in the same way as in JS/TS!
If the typechecker disagrees, you can insert casts with `.asInstanceOf[Type]`.

The JS types (like `js.Array`) are available with
```scala
import scala.scalajs.js
```

The VSCode classes and functions are available with
```scala
import typings.vscode.mod as vscode
// to use:
vscode.function(arg)
```

Some additional types are available in the `anon` subpackage, for example:
```scala
import typings.vscode.anon.Dispose
vscode.commands.registerCommand(name, fun).asInstanceOf[Dispose]
```

You can find more information and tutorials on the [Scala.js website](https://www.scala-js.org/).

