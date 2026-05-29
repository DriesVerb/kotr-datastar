package com.example.pokemon

import com.example.html.*
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.routing.*
import kotlinx.html.*

fun Route.pokemonRoute(pokemonClient: PokemonClient) {
    get("/pokemon/{name}") {
        val name = call.parameters["name"] ?: "pikachu"
        val pokemon =
            try {
                pokemonClient.getPokemon(name)
            } catch (e: Exception) {
                null
            }

        call.respondHtml {
            defaultHeader("Pokemon: ${pokemon?.name ?: "Not Found"}")
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
                pokemonDetail(pokemon)
            }
        }
    }
}
