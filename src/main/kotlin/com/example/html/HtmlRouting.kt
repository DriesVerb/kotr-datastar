package com.example.html

import com.example.pokemon.PokemonClient
import com.example.pokemon.pokemonRoute
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.html.respondHtml
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import kotlinx.html.*

fun Application.configureHtmlRouting() {
    val pokemonClient = PokemonClient()

    routing {
        pokemonRoute(pokemonClient)
        get("/") {
            call.respondHtml {
                defaultHeader("MY DATA STAR") {
                }
                body {
                    classes = setOf("bg-slate-900", "text-white")
                    navbar("pokemon")
                    div("p-8") {
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
                                setOf(
                                    "bg-blue-600",
                                    "hover:bg-blue-700",
                                    "px-4",
                                    "py-2",
                                    "rounded-lg",
                                    "transition-colors",
                                )
                            attributes["data-on:click"] = "alert('hello')"
                            +"see an alert"
                        }
                    }
                }
            }
        }
    }
}
