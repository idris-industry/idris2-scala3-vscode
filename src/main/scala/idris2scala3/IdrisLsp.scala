package idris2scala3
import typings.vscode
import typings.vscodeLanguageclient.mod.BaseLanguageClient
import typings.node.httpsMod.ServerOptions
import typings.vscodeLanguageclient.libCommonClientMod.LanguageClientOptions
import typings.vscodeLanguageclient.mod

// blocked:   unable to find LanguageClient, very messy node modules
object IdrisLsp {
  /* const client = new LanguageClient(
    'idris2-lsp',
    baseName + ' Client',
    serverOptions,
    clientOptions
  ); */
  ServerOptions
  LanguageClientOptions

  new BaseLanguageClient("idris2-lsp", "idris2-lsp", null)
}

/* import { spawn } from 'child_process';
import {
  workspace,
  ExtensionContext,
  window,
  OutputChannel,
  commands,
  TextEditorEdit,
  TextEditor,
  MarkdownString,
  DecorationRangeBehavior,
} from 'vscode';

import {
  LanguageClient,
  LanguageClientOptions,
  ServerOptions,
  StreamInfo,
} from 'vscode-languageclient/node';

import { Readable } from 'stream';
import * as process from 'process'

const baseName = 'Idris 2 LSP';

export function activate(context: ExtensionContext) {
  const extensionConfig = workspace.getConfiguration("idris2-lsp");
  const command: string = extensionConfig.get("path") || "";
  const debugChannel = window.createOutputChannel(baseName + ' Server');
  const serverOptions: ServerOptions = () => new Promise<StreamInfo>((resolve, reject) => {
    const serverProcess = spawn(command, [], { cwd: rootPath(), shell: process.platform === 'win32' });
    if (!serverProcess || !serverProcess.pid) {
      return reject(`Launching server using command ${command} failed.`);
    }

    context.subscriptions.push({
      dispose: () => {
        sendExitCommandTo(serverProcess.stdin);
      }
    });

    const stderr = serverProcess.stderr;
    stderr.setEncoding('utf-8');
    stderr.on('data', data => debugChannel.append(data));

    resolve({
      writer: serverProcess.stdin,
      reader: sanitized(serverProcess.stdout, debugChannel),
      detached: true // let us handle the disposal of the server
    });
  });
  let initializationOptions = {
    logSeverity: extensionConfig.get("logSeverity") || "debug",
    logFile: extensionConfig.get("logFile") || "stderr",
    longActionTimeout: extensionConfig.get("longActionTimeout") || 5000,
    maxCodeActionResults: extensionConfig.get("maxCodeActionResults") || 5,
    showImplicits: extensionConfig.get("showImplicits") || false,
    showMachineNames: extensionConfig.get("showMachineNames") || false,
    fullNamespace: extensionConfig.get("fullNamespace") || false,
  };
  const clientOptions: LanguageClientOptions = {
    documentSelector: [
      { scheme: 'file', language: 'idris' },
      { scheme: 'file', language: 'lidr' }
    ],
    initializationOptions: initializationOptions,
  };
  const client = new LanguageClient(
    'idris2-lsp',
    baseName + ' Client',
    serverOptions,
    clientOptions
  );
  client.start();
  context.subscriptions.push({
    dispose: () => {
      client.stop();
    }
  });
  registerCommandHandlersFor(client, context);
} */
