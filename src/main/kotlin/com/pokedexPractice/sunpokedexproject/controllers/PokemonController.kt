package com.pokedexPractice.sunpokedexproject.controllers

import com.pokedexPractice.sunpokedexproject.models.Pokemon
import com.pokedexPractice.sunpokedexproject.services.PokedexService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/pokemon")
class PokemonController(private val pokedexService: PokedexService) {

    @GetMapping
    fun getAllPokemon(
        @RequestParam(value = "page", required = false) pageNumber: Int = 1
    ): List<Pokemon> {
        val pageSize = 10
        return pokedexService.getAllPokemon(pageNumber, pageSize)
    }

    @GetMapping("/{id}")
    fun getPokemonById(
        @PathVariable id: Int
    ): ResponseEntity<Pokemon> {
        return pokedexService.getPokemonById(id)?.let {
            ResponseEntity.ok(it)
        } ?: ResponseEntity.notFound().build()
    }
}
