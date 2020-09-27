/*
 * TodoMVC
 * Copyright (c) 2020 Falko Schumann <falko.schumann@muspellheim.de>
 */

package de.muspellheim.todomvc.backend.adapter

import com.google.gson.Gson
import de.muspellheim.todomvc.backend.TodoRepository
import de.muspellheim.todomvc.contract.data.Todo
import java.nio.file.Files
import java.nio.file.Path

class TodoJsonRepository(private val file: Path) : TodoRepository {
    override fun load(): List<Todo> {
        val gson = Gson()
        Files.newBufferedReader(file).use { reader ->
            return gson.fromJson(reader, Array<Todo>::class.java).toList()
        }
    }

    override fun store(todoList: List<Todo>) {
        val gson = Gson()
        Files.newBufferedWriter(file).use { writer ->
            gson.toJson(todoList, writer)
        }
    }
}
