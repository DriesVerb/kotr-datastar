package com.example.home

import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.routing

fun Application.configureHomeRouting() {
    routing {
        get("/") {
            call.respondText("Hello, World!")
        }
    }
}
