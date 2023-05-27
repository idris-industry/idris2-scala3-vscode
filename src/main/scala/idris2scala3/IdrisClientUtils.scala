package idris2scala3

import scala.scalajs.js

import typings.node.childProcessMod.*
import typings.idrisIdeClient.*
import typings.idrisIdeClient.mod.IdrisClient
import typings.vscode.mod.extensions

import concurrent.ExecutionContext.Implicits.global
import typings.idrisIdeClient.buildReplyMod.FinalReply.TypeOf

object IdrisClientUtils {

  /** create IdrisClient
    * https://github.com/meraymond2/idris-vscode/blob/318bc0f40e06a84fc66a1a061173f6a11a19c1c6/src/state.ts#L98
    * https://www.npmjs.com/package/idris-ide-client
    */
  def newIdrisClient = {
    val idrisProc = spawn("idris", js.Array("--ide-mode")) // []

    val client: IdrisClient = new IdrisClient(idrisProc.stdin, idrisProc.stdout)

    // should print "n : Nat"
    client.typeOf("n").toFuture foreach { (r: TypeOf) => r }
    // client.caseSplit()
    client
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
