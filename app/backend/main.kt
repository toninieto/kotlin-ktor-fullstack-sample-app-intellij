package sample.kotlin.intellij.backend

import sample.kotlin.intellij.backend.Server

fun main(args : Array<String>) {

    // We start the server
    val server = Server(8888, "INFO: Netty server started")
    server.start()

}