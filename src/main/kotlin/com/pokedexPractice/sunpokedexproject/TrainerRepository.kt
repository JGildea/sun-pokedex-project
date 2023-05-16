package com.pokedexPractice.sunpokedexproject

import com.pokedexPractice.sunpokedexproject.models.Trainer
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface TrainerRepository: JpaRepository<Trainer, UUID> {
    fun findByEmail(email: String): Trainer?
}