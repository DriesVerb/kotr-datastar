package com.example.html

import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.html.respondHtml
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import kotlinx.html.*

fun Application.configureHtmlRouting() {
    routing {
        get("/html-dsl") {
            call.respondHtml {
                head {
                    script {
                        src = "https://cdn.jsdelivr.net/npm/@tailwindcss/browser@4"
                    }
                    script {
                        type = "module"
                        src = "https://cdn.jsdelivr.net/gh/starfederation/datastar@v1.0.1/bundles/datastar.js"
                    }
                    title {
                        +"MY DATA STAR"
                    }
                }
                body {
                    classes = setOf("bg-slate-900", "text-white", "p-8")
                    h1 {
                        classes = setOf("text-4xl", "font-bold", "mb-4")
                        +"HTMLLLL"
                    }
                    h3 {
                        classes = setOf("text-xl", "text-slate-400", "mb-6")
                        +"test test test"
                    }
                    ul {
                        classes = setOf("list-disc", "ml-5", "mb-8")
                        for (n in 1..10) {
                            li { +"$n" }
                        }
                    }
                    button {
                        classes =
                            setOf("bg-blue-600", "hover:bg-blue-700", "px-4", "py-2", "rounded-lg", "transition-colors")
                        attributes["data-on:click"] = "alert('hello')"
                        +"see an alert"
                    }
                }
            }
        }
    }
}
