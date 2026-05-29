package com.example.pokemon

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.*
import kotlinx.serialization.json.*

@Serializable
data class PokemonResponse(
    val name: String,
    val height: Int,
    val weight: Int,
    val types: List<TypeSlot>
)

@Serializable
data class TypeSlot(
    val slot: Int,
    val type: TypeInfo
)

@Serializable
data class TypeInfo(
    val name: String,
    val url: String
)

class PokemonClient {
    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                prettyPrint = true
                isLenient = true
            })
        }
    }

    suspend fun getPokemon(name: String): PokemonResponse {
        return client.get("https://pokeapi.co/api/v2/pokemon/${name.lowercase()}").body()
    }

    fun close() {
        client.close()
    }
}
