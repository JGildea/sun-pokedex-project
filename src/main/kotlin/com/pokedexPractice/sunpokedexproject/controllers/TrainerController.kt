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

    @GetMapping("/trainers")
    fun getTrainers(): ResponseEntity<List<Trainer>> {
        val trainers = trainerService.getTrainers()
        return ResponseEntity.ok(trainers)
    }

    @GetMapping("/{trainerId}")
    fun getTrainerById(@PathVariable trainerId: UUID): ResponseEntity<Trainer> {
        val trainer = trainerService.getTrainerById(trainerId)

        return if (trainer != null) {
            ResponseEntity.ok(trainer)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @GetMapping("/{trainerId}/captured")
    fun getCapturedPokemons(@PathVariable trainerId: UUID): ResponseEntity<List<String>> {
        val pokemons = trainerService.getTrainerById(trainerId)?.capturedPokemon
        return ResponseEntity.ok(pokemons)
    }

    @PostMapping("/register")
    fun register(@Valid @RequestBody trainer: Trainer): ResponseEntity<Any> {
        val trainers = trainerService.getTrainerByEmail(trainer.email)
        return if (trainers != null) {
            ResponseEntity.badRequest().body("Email already exist")
        } else {
            trainerService.register(trainer)
            ResponseEntity.ok("Trainer registered successfully")
        }
    }

    @PutMapping("/{trainerId}/pokemon/{pokemonId}/capture")
    fun capturePokemon(@PathVariable trainerId: UUID, @PathVariable pokemonId: Int): ResponseEntity<String> {
        val trainer = trainerService.getTrainerById(trainerId)
        val pokemon = pokedexService.getPokemonById(pokemonId)
        if (trainer == null || pokemon == null) {
            return ResponseEntity.notFound().build()
        }

        val pokemonName = pokemon.name
        if (trainer.capturedPokemon.contains(pokemonName)) {
            return ResponseEntity.badRequest().body("Pokemon is already captured")
        } else {
            trainerService.updateTrainer(trainerId, pokemonName, pokedexService)
            return ResponseEntity.ok("Pokemon captured successfully!")
        }
    }

}