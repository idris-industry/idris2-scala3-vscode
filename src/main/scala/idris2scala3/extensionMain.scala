package idris2scala3

import concurrent.ExecutionContext.Implicits.global
import scala.collection.immutable
import scala.scalajs.js
import scala.scalajs.js.annotation.JSExportTopLevel

import typings.vscode.mod as vscode
import typings.vscode.anon.Dispose
import typings.vscode.mod.ExtensionContext
import typings.idrisIdeClient.buildReplyMod.FinalReply.LoadFile

/** This method is called when your extension is actived. The extension is
  * activated the first time one of its features is used (here we only have
  * commands).
  *
  * Exports the function to javascript so that VSCode can load it
  */
@JSExportTopLevel("activate")
def activate(context: vscode.ExtensionContext): Unit = {
  vscode.window.showInformationMessage(
    """Congrats!  "vscode-scalajs-hello" is now active!"""
  )

  
  def showHello(arg: Any): Unit = {
    vscode.window.showInformationMessage(s"Hello World from scala 3 !!!")
  }

  val client = IdrisClientUtils.newIdrisClient
  val cvF = client
    .version()
    .toFuture

  val commands: Seq[(String, Any => Any)] = Seq(
    ("extension.helloWorld", showHello),
    (
      "extension.idrisLoad", // exec when run cmd idrisLoad in vscode
      { _ =>
        client
          .version()
          .toFuture
          .foreach(v =>
            vscode.window.showInformationMessage("ver:" + v.toString())
          )

        vscode.window.showInformationMessage(
          "idrisLoad! version" + client.version().toFuture.value
        )

        /* vscode.window.visibleTextEditors.foreach { editor =>
          val lfF = client.loadFile(editor.document.fileName).toFuture

          vscode.window.showInformationMessage(
            "idrisLoad stat:" + lfF.toString()
          )
          lfF.onComplete { r =>
            vscode.window.showInformationMessage(r.toString())
          }

        } */

      }
    )
  )

  js.timers.setTimeout(500) {
    vscode.window.showInformationMessage(cvF.value.toString())
  }
  // context. //
  vscode.window.showInformationMessage("idris ext load finished")
  registerCommandList(context, commands)

}

/** Register the commands in VSCode */
def registerCommandList(
    context: ExtensionContext,
    commands: Seq[(String, Any => Any)]
) = {
  commands foreach { (name, fun) =>
    context.subscriptions.push(
      vscode.commands
        .registerCommand(name, fun)
        .asInstanceOf[Dispose] // to make the typechecker happy (VSCode has typescript facades nowadays)
    )
  }
}
