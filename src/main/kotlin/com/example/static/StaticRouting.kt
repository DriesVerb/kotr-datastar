package com.example.static

import io.ktor.server.application.Application
import io.ktor.server.http.content.staticResources
import io.ktor.server.routing.routing

fun Application.configureStaticRouting() {
    routing {
        staticResources("/static", "static")
    }
}
