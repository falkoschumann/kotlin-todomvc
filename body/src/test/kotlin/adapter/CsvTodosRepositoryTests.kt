/*
 * TodoMVC
 * Copyright (c) 2020 Falko Schumann
 */

package de.muspellheim.todomvc.adpater

import de.muspellheim.todomvc.domain.Todo
import de.muspellheim.todomvc.domain.TodoList
import java.nio.file.Paths
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CsvTodosRepositoryTests {

    @Test
    fun `todo list stored and loaded`() {
        val foo = Todo("Foo")
        val bar = Todo("Bar", true)
        val todoList = TodoList(listOf(foo, bar))
        val todos = CsvTodosRepository(Paths.get("build", "todos.csv"))

        todos.store(todoList)
        val storedAndLoaded = todos.load()

        assertEquals(todoList.allItems(), storedAndLoaded.allItems(), "all items")
    }
}
