package com.pokedexPractice.sunpokedexproject.controllers

import com.pokedexPractice.sunpokedexproject.models.Pokemon
import com.pokedexPractice.sunpokedexproject.services.PokedexService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

// testing controller
//@RestController
//class PokedexController {
//    @RequestMapping("/")
//    fun helloSpringBoot() = "Hello Spring Boot"
//}

@RestController
@RequestMapping("/")
class PokedexController(private val pokedexService: PokedexService) {

    @GetMapping("/{pageNumber}")
    fun getAllPokemon(@PathVariable pageNumber: Int): List<Pokemon> {
        val pageSize = 10
        return pokedexService.getAllPokemon(pageNumber, pageSize)
    }

    @GetMapping("/pokemon/{id}")
    fun getPokemonById(@PathVariable id: Int): ResponseEntity<Pokemon> {
        val pokemon = pokedexService.getPokemonById(id)

        return if (pokemon != null) {
            ResponseEntity.ok(pokemon)
        } else {
            ResponseEntity.notFound().build()
        }
    }

//    @GetMapping("/pokemon/{name}")
//    fun getPokemonByName(@PathVariable name: String): ResponseEntity<String> {
//        val pokemon = pokedexService.getPokemonByName(name)
//        return if (pokemon != null) {
//            ResponseEntity.ok(pokemon.name)
//        } else {
//            ResponseEntity.notFound().build()
//        }
//    }

}
