package com.pokedexPractice.sunpokedexproject.utils

//fun readPokemonCsvFile(): List<Pokemon> {
//    val pokemons = mutableListOf<Pokemon>()
//    val inputStream = ::readPokemonCsvFile.javaClass.classLoader.getResourceAsStream("/pokedex.csv")
//    val bufferedReader = BufferedReader(InputStreamReader(inputStream))
//    bufferedReader.use { reader ->
//        reader.readLines().forEach {
//            val values = it.split(",").map { it.trim() }
//            val id = values[0].toInt()
//            val name = values[1]
//            val types = values[2].removeSurrounding("[", "]").replace(" ", "").split(",").toTypedArray()
//            val height = values[3].toInt()
//            val weight = values[4].toInt()
//            val abilities = values[5]
//            val eggGroups = values[6]
//            val stats = values[7]
//            val genus = values[8]
//            val description = values[9]
//            val pokemon = Pokemon(id, name, types, height, weight, abilities, eggGroups, stats, genus, description)
//            pokemons.add(pokemon)
//        }
//    }
//
//    return pokemons
//}

//.removeSurrounding("[", "]").replace(" ", "").split(",").toTypedArray()