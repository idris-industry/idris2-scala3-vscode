package idris2scala3.facade

import scala.scalajs.js
import scala.scalajs.js.annotation.*
import typings.vscodeLanguageclient.libCommonClientMod.LanguageClientOptions

/* from https://github.com/microsoft/vscode-languageserver-node/blob/e1345cca06095d61d3c5c0ff961472d55cbcf798/client/src/browser/main.ts#L13

Error: Cannot find module 'vscode-languageclient/node'
 */
@js.native
@JSImport("vscode-languageclient/node", "LanguageClient")
class LanguageClient(
    id: String,
    name: String,
    serverOptions: js.Object,
    clientOptions: LanguageClientOptions
) extends js.Object {}

/* const client = new LanguageClient(
    'idris2-lsp',
    baseName + ' Client',
    serverOptions,
    clientOptions
  ); */
