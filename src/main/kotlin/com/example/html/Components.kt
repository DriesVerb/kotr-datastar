package com.example.html

import com.example.pokemon.PokemonResponse
import kotlinx.html.*

data class NavItem(
    val label: String,
    val link: String,
)

fun FlowContent.navbar(
    title: String,
    mainLink: NavItem = NavItem("Link", "/link"),
    dropdownTitle: String = "Parent",
    dropdownItems: List<NavItem> = listOf(NavItem("Link 1", "/link1"), NavItem("Link 2", "/link2")),
) {
    div("navbar bg-base-100 shadow-sm") {
        div("flex-1") {
            a(classes = "btn btn-ghost text-xl") {
                +title
            }
        }
        div("flex-none") {
            ul("menu menu-horizontal px-1") {
                linkedAnchor(link = mainLink.link, label = mainLink.label)
                li {
                    details {
                        summary { +dropdownTitle }
                        ul("bg-base-100 rounded-t-none p-2") {
                            dropdownItems.forEach { item ->
                                linkedAnchor(link = item.link, label = item.label)
                            }
                        }
                    }
                }
            }
        }
    }
}

fun HTML.defaultHeader(
    pageTitle: String,
    block: HEAD.() -> Unit = {},
) {
    attributes["data-theme"] = "dark"
    head {
        defaultResources()
        title {
            +pageTitle
        }
        block()
    }
}

fun HEAD.defaultResources() {
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
}

fun FlowContent.pokemonDetail(pokemon: com.example.pokemon.PokemonResponse?) {
    div("p-8") {
        if (pokemon != null) {
            h1("text-5xl font-bold capitalize mb-4") { +pokemon.name }
            div("hover-3d mb-8") {
                figure("w-64 rounded-2xl shadow-2xl") {
                    swapImage(
                        image1 = pokemon.sprites.other.officialArtwork.frontDefault,
                        image2 = pokemon.sprites.other.officialArtwork.frontShiny,
                    )
                }
                for (n in 1..8) {
                    div {}
                }
            }
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

fun FlowContent.swapImage(
    image1: String,
    image2: String,
) {
    label("swap swap-rotate") {
        input {
            type = InputType.checkBox
        }
        img(
            src = image1,
            alt = "Normal",
            classes = "swap-off w-full h-auto",
        )
        img(
            src = image2,
            alt = "Shiny",
            classes = "swap-on w-full h-auto",
        )
    }
}

fun UL.linkedAnchor(
    link: String,
    label: String,
) {
    li {
        a {
            href = link
            +label
        }
    }
}
