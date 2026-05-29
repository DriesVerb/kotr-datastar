package com.example.html

import com.example.pokemon.PokemonClient
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.html.respondHtml
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import kotlinx.html.*

fun Application.configureHtmlRouting() {
    val pokemonClient = PokemonClient()

    routing {
        get("/pokemon/{name}") {
            val name = call.parameters["name"] ?: "pikachu"
            val pokemon =
                try {
                    pokemonClient.getPokemon(name)
                } catch (e: Exception) {
                    null
                }

            call.respondHtml {
                attributes["data-theme"] = "dark"
                head {
                    link {
                        href = "https://cdn.jsdelivr.net/npm/daisyui@5"
                        rel = "stylesheet"
                        type = "text/css"
                    }
                    script {
                        src = "https://cdn.jsdelivr.net/npm/@tailwindcss/browser@4"
                    }
                    title {
                        +"Pokemon: ${pokemon?.name ?: "Not Found"}"
                    }
                }
                body {
                    classes = setOf("bg-slate-900", "text-white")
                    navbar(
                        title = "Pokedex",
                        mainLink = NavItem("Home", "/html-dsl"),
                        dropdownTitle = "Quick Search",
                        dropdownItems =
                            listOf(
                                NavItem("Pikachu", "/pokemon/pikachu"),
                                NavItem("Charizard", "/pokemon/charizard"),
                                NavItem("Bulbasaur", "/pokemon/bulbasaur"),
                            ),
                    )
                    div("p-8") {
                        if (pokemon != null) {
                            h1("text-5xl font-bold capitalize mb-4") { +pokemon.name }
                            div("grid grid-cols-2 gap-4 max-w-md") {
                                div("stats shadow bg-slate-800") {
                                    div("stat") {
                                        div("stat-title text-slate-400") { +"Height" }
                                        div("stat-value text-blue-400") { +"${pokemon.height}" }
                                    }
                                }
                                div("stats shadow bg-slate-800") {
                                    div("stat") {
                                        div("stat-title text-slate-400") { +"Weight" }
                                        div("stat-value text-green-400") { +"${pokemon.weight}" }
                                    }
                                }
                            }
                            div("mt-6") {
                                h2("text-2xl font-semibold mb-2") { +"Types" }
                                div("flex gap-2") {
                                    pokemon.types.forEach { typeSlot ->
                                        span("badge badge-primary badge-lg capitalize") {
                                            +typeSlot.type.name
                                        }
                                    }
                                }
                            }
                        } else {
                            h1("text-4xl text-red-500") { +"Pokemon not found!" }
                            a(href = "/pokemon/pikachu", classes = "btn btn-outline mt-4") {
                                +"Try Pikachu"
                            }
                        }
                    }
                }
            }
        }

        get("/") {
            call.respondHtml {
                attributes["data-theme"] = "dark"
                head {
                    link {
                        href = "https://cdn.jsdelivr.net/npm/daisyui@5"
                        rel = "stylesheet"
                        type = "text/css"
                    }
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
