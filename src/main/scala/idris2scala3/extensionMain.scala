package idris2scala3

import org.scalajs.macrotaskexecutor.MacrotaskExecutor.Implicits._
import scala.collection.immutable
import scala.scalajs.js
import scala.scalajs.js.annotation.JSExportTopLevel

import typings.vscode.mod as vscode
import typings.vscode.anon.Dispose
import typings.vscode.mod.ExtensionContext
import typings.idrisIdeClient.buildReplyMod.FinalReply.LoadFile
import scala.concurrent.Future

object extensionMain {

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

    val commands: Seq[(String, Any => Any)] = Seq(
      ("extension.helloWorld", showHello),
      (
        "extension.idrisLoad", // exec when run cmd idrisLoad in vscode
        { _ =>
          val client =
            IdrisClientUtils.newIdrisClient(x =>
              vscode.window.showInformationMessage(x)
            )

          var i = 0
          /*         while (!cvF.isCompleted) {
          i += 1
          vscode.window.showInformationMessage(s"ver: ${cvF.value} $i")
        } */

          // val verF = client
          //   .version()
          //   .toFuture

          Future { 11 }.onComplete { r =>
            vscode.window.showInformationMessage("f1:" + r.toString())
          }

        /*         val tpF = client.typeOf("n").toFuture
        tpF.onComplete { r =>
          vscode.window.showInformationMessage("ver:" + r.toString())
        }
         */
        /* vscode.window.showInformationMessage(
          "idrisLoad! version" + client.version().toFuture.value
        ) */

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

// not work
    /* js.timers.setTimeout(500) {
    vscode.window.showInformationMessage(cvF.value.toString())
  } */
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

}
