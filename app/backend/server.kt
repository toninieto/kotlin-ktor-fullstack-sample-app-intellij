package sample.kotlin.intellij.backend


import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*


open class Server(val port: Int,
                  val startMessage: String) {

    fun start() {
        embeddedServer(Netty, port) {
            routing {
                get("/") {
                    call.respondText("We say hello world on the backend!!!", ContentType.Text.Html)
                }
            }
        }.start(wait = false)
        println("\n\n")
        println(startMessage)
    }

}


