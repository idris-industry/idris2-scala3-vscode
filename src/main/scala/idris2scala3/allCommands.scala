package idris2scala3

import typings.idrisIdeClient.mod.IdrisClient

/** Store all the commands here */
object allCommands {

  /** createCommands given IdrisClient
    *
    * (cmd name,cmd msg,cmd func)
    */
  def loadFile(client: IdrisClient, path: String) = {

    client.loadFile(path)
  }
}
