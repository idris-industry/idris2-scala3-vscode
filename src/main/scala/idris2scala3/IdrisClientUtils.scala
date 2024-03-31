package idris2scala3

import scala.scalajs.js

import typings.node.childProcessMod.*
import typings.idrisIdeClient.*
import typings.idrisIdeClient.mod.IdrisClient
import typings.vscode.mod.extensions

import concurrent.ExecutionContext.Implicits.global
import typings.idrisIdeClient.buildReplyMod.FinalReply.TypeOf
import typings.node.BufferEncoding
import typings.node.global.console

object IdrisClientUtils {

  /** create IdrisClient
    * https://github.com/meraymond2/idris-vscode/blob/318bc0f40e06a84fc66a1a061173f6a11a19c1c6/src/state.ts#L98
    * https://www.npmjs.com/package/idris-ide-client
    *
    * protocol:
    * https://idris2.readthedocs.io/en/latest/implementation/ide-protocol.html
    */
  def newIdrisClient(msgF: String => Unit) = {
    val idrisProc = spawn("idris2", js.Array("--ide-mode")) // []

    // msgF("newIdrisClient")
    // idrisProc.stdout.on("data", { x => msgF(x.toString()) })
    // idrisProc.stdin.write(":version".asInstanceOf[Any])
    console.log("newIdrisClient")
    val client: IdrisClient = new IdrisClient(idrisProc.stdin, idrisProc.stdout)
    client.version().`then` { x =>
      console.log("clientThen:" + x.toString())
      msgF("clientThen:" + x.toString())
    }

    // should print "n : Nat"

    // client.caseSplit()
    // client
  }

//   extension (client: IdrisClient) {}
}

/* export const loadFile = async (client: IdrisClient, document: vscode.TextDocument): Promise<void> => {
  if (state.statusMessage) state.statusMessage.dispose()

  if (isExtLanguage(document.languageId) && supportedLanguages(state).includes(document.languageId)) {
    const reply = await client.loadFile(document.fileName)
    if (reply.ok) {
      state.currentFile = document.fileName
    } else if (state.idris2Mode) {
      state.statusMessage = vscode.window.setStatusBarMessage(
        "File failed to typecheck — commands will work incorrectly until it does."
      )
    } else {
      status("Failed to load file.")
    }
  }
} */
