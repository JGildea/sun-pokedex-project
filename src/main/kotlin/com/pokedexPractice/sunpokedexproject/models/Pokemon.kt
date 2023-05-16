package com.pokedexPractice.sunpokedexproject.models

data class Pokemon (
    val id: Int = 0,
    val name: String = "",
    val types: List<Array<String>> = emptyList(),
    val height: Int = 0,
    val weight: Int = 0,
    val abilities: List<Array<String>> = emptyList(),
    val eggGroups: List<Array<String>> = emptyList(),
    val stats: PokemonStat = PokemonStat(),
    val genus: String = "",
    val description: String = ""

)

