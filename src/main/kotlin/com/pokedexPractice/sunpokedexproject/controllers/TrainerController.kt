@file:Suppress("UNREACHABLE_CODE")

package com.pokedexPractice.sunpokedexproject.controllers

import com.pokedexPractice.sunpokedexproject.models.Trainer
import com.pokedexPractice.sunpokedexproject.services.PokedexService
import com.pokedexPractice.sunpokedexproject.services.TrainerService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.util.*

@Validated
@RestController
@RequestMapping("/trainer")
class TrainerController(private val trainerService: TrainerService, private val pokedexService: PokedexService) {

    @GetMapping
    fun getTrainers(): ResponseEntity<List<Trainer>> {
        val trainers = trainerService.getTrainers()
        return ResponseEntity.ok(trainers)
    }

    @GetMapping("/{trainerId}")
    fun getTrainerById(@PathVariable trainerId: UUID): ResponseEntity<Trainer> {
        return trainerService.getTrainerById(trainerId)?.let {
            ResponseEntity.ok(it)
        } ?: ResponseEntity.notFound().build()
    }

    @GetMapping("/{trainerId}/captured")
    fun getCapturedPokemons(@PathVariable trainerId: UUID): ResponseEntity<List<String>> {
        val pokemons = trainerService.getTrainerById(trainerId)?.capturedPokemon ?: listOf()
        return ResponseEntity.ok(pokemons)
    }

    @PostMapping("/register")
    fun register(@Valid @RequestBody trainer: Trainer): ResponseEntity<Any> {
        return trainerService.getTrainerByEmail(trainer.email)?.let {
            ResponseEntity.badRequest().body("Email already exist")
        } ?: run {
            trainerService.register(trainer)
            ResponseEntity.ok("Trainer registered successfully")
        }
    }

    @PutMapping("/{trainerId}/pokemon/{pokemonId}/capture")
    fun capturePokemon(@PathVariable trainerId: UUID, @PathVariable pokemonId: Int): ResponseEntity<String> {
        val trainer = trainerService.getTrainerById(trainerId) ?: return ResponseEntity.notFound().build()
        val pokemon = pokedexService.getPokemonById(pokemonId) ?: return ResponseEntity.notFound().build()

        val pokemonName = pokemon.name
        return if (trainer.capturedPokemon.contains(pokemonName)) {
            ResponseEntity.badRequest().body("Pokemon is already captured")
        } else {
            trainerService.updateTrainer(trainerId, pokemonName)
            ResponseEntity.ok("Pokemon captured successfully!")
        }
    }

}