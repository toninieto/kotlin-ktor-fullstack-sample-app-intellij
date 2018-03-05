package sample.kotlin.intellij.backend

import sample.kotlin.intellij.backend.*

fun main(args : Array<String>) {

    // We start the server
    val server = Server(8888, "INFO: Netty server started")
    server.start()

    // We create the database
    val db = Database()
    db.create()

}