package com.pokedexPractice.sunpokedexproject.models

import jakarta.persistence.*
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size
import org.hibernate.annotations.ColumnDefault
import java.util.*

@Entity
@Table(name="trainer")
data class Trainer (
    @Id
    @Column(name = "id")
    @GeneratedValue
    @ColumnDefault("random_uuid()")
    val id: UUID? = null,

    @Pattern(regexp = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})", message = "Email is not valid")
    @Column(name="email")
    val email: String,

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@\$!%*#?&])[A-Za-z\\d@\$!%*#?&]{8,}\$", message = "Password must container at least one upper case letter, one number, and one special character(!%*#?&)")
    @field:Size(min = 8, message = "Password must be at least 8 characters")
    @Column(name="password")
    val password: String,

    @Column(name="capturedPokemon")
    val capturedPokemon: ArrayList<String> = arrayListOf()
) {
    constructor(): this(null,"", "", arrayListOf())
}

