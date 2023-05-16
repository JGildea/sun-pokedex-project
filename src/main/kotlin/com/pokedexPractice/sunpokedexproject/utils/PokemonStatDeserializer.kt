package com.pokedexPractice.sunpokedexproject.utils

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.pokedexPractice.sunpokedexproject.models.Pokemon
import com.pokedexPractice.sunpokedexproject.models.PokemonStat

class PokemonStatDeserializer : JsonDeserializer<PokemonStat>() {
    override fun deserialize(parser: JsonParser, context: DeserializationContext): PokemonStat {
        val node: JsonNode = parser.codec.readTree(parser)
        val hp = node.get("hp").asInt()
        val speed = node.get("speed").asInt()
        val attack = node.get("attack").asInt()
        val defense = node.get("defense").asInt()
        val specialAttack = node.get("specialAttack").asInt()
        val specialDefense = node.get("specialDefense").asInt()
        return PokemonStat(hp, speed, attack, defense, specialAttack, specialDefense)
    }
}
