package com.pokedexPractice.sunpokedexproject.utils

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.pokedexPractice.sunpokedexproject.models.Pokemon
import com.pokedexPractice.sunpokedexproject.models.PokemonStat

class PokemonDeserializer : JsonDeserializer<Pokemon>() {
    override fun deserialize(jp: JsonParser, ctxt: DeserializationContext): Pokemon {
        val node = jp.codec.readTree<JsonNode>(jp)
        val id = node.get("id").asInt()
        val name = node.get("name").asText()
        val types = node.get("types").map { arrayOf(it.asText()) }
        val height = node.get("height").asInt()
        val weight = node.get("weight").asInt()
        val abilities = node.get("abilities").map { arrayOf(it.asText()) }
        val eggGroups = node.get("eggGroups").map { arrayOf(it.asText()) }
        val genus = node.get("genus").asText()
        val description = node.get("description").asText()
        val statsNode = node.get("stats")
        val statsJson = ObjectMapper().writeValueAsString(statsNode)
        val stats = ObjectMapper().readValue(statsJson, PokemonStat::class.java)
        return Pokemon(id, name, types, height, weight, abilities, eggGroups, stats, genus, description)
    }
}