package com.pokedexPractice.sunpokedexproject.services

import com.pokedexPractice.sunpokedexproject.TrainerRepository
import com.pokedexPractice.sunpokedexproject.models.Trainer
import org.mindrot.jbcrypt.BCrypt
import org.springframework.stereotype.Service
import java.util.*

@Service
class TrainerService(private val trainerRepository: TrainerRepository) {

    fun getTrainers(): List<Trainer>{
        return trainerRepository.findAll()
    }

    fun getTrainerById(id: UUID): Trainer? {
        return trainerRepository.findById(id).orElse(null)
    }

    fun getTrainerByEmail(email: String): Trainer? {
        return trainerRepository.findByEmail(email)
    }

    fun register(trainer: Trainer) {
        val id = UUID.randomUUID()
        val hashedPassword = BCrypt.hashpw(trainer.password, BCrypt.gensalt())
        val newTrainer = Trainer(id, trainer.email, hashedPassword)
        trainerRepository.save(newTrainer)
    }

    fun updateTrainer(trainerId: UUID, pokemonName: String, pokemonService: PokedexService) {
        val trainer = trainerRepository.findById(trainerId).orElse(null)
        val pokemon = pokemonService.getPokemonByName(pokemonName)
        val name = pokemon?.name
        if (name != null) {
            trainer?.capturedPokemon?.add(name)
            trainerRepository.save(trainer)
        }

    }
}