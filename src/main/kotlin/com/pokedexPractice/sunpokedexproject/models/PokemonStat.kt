package com.pokedexPractice.sunpokedexproject.models

data class PokemonStat (
    val id: Int = 0,
    val hp: Int = 0,
    val speed: Int = 0,
    val attack: Int = 0,
    val defense: Int = 0,
    val specialAttack: Int = 0,
    val specialDefense: Int = 0
) {
    constructor() : this(0,0,0,0,0,0, 0)
}