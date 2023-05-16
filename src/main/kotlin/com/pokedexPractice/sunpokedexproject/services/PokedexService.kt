package com.pokedexPractice.sunpokedexproject.services

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import com.pokedexPractice.sunpokedexproject.models.Pokemon
import com.pokedexPractice.sunpokedexproject.models.PokemonStat
import com.pokedexPractice.sunpokedexproject.utils.PokemonDeserializer
import com.pokedexPractice.sunpokedexproject.utils.PokemonStatDeserializer
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Service

@Service
class PokedexService {
    private val pokemons = mutableListOf<Pokemon>()
    private val mapper = ObjectMapper().apply {
        val module = SimpleModule()
        module.addDeserializer(Pokemon::class.java, PokemonDeserializer())
        module.addDeserializer(PokemonStat::class.java, PokemonStatDeserializer())
        registerModule(module)
        configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
}
    init {
        val jsonData = ClassPathResource("assets/pokedex.json").file
        val javaType = mapper.typeFactory.constructParametricType(List::class.java, Pokemon::class.java)
        val pokemonList: List<Pokemon> = mapper.readValue(jsonData, javaType)

        pokemons.addAll(pokemonList)
    }

    fun getAllPokemon(pageNumber: Int, pageSize: Int = 10): List<Pokemon> {
        val startIndex = (pageNumber - 1) * pageSize
        val endIndex = minOf(startIndex + pageSize, pokemons.size)
        return pokemons.subList(startIndex, endIndex)

    }

    fun getPokemonById(id: Int): Pokemon? {
        return pokemons.find { it.id == id }
    }

    fun getPokemonByName(name: String): Pokemon? {
        return pokemons.find {it.name == name}
    }

}