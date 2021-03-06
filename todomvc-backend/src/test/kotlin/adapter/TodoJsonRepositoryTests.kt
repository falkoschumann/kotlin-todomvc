/*
 * TodoMVC
 * Copyright (c) 2020 Falko Schumann
 */

package de.muspellheim.todomvc.backend.adapter

import de.muspellheim.todomvc.contract.data.Todo
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.nio.file.Files
import java.nio.file.Paths

class TodoJsonRepositoryTests {
    private val readTestFile = Paths.get("src/test/resources/todos.json")
    private val writtenTestFile = Paths.get("build/test/todos.json")

    @Test
    fun `load todos`() {
        val repository = TodoJsonRepository(readTestFile)

        val todoList = repository.load()

        assertEquals(createTestData(), todoList)
    }

    @Test
    fun `store todos`() {
        Files.createDirectories(writtenTestFile.parent)
        val repository = TodoJsonRepository(writtenTestFile)
        val todoList = createTestData()

        repository.store(todoList)

        val actualTodoList = repository.load()
        assertEquals(createTestData(), actualTodoList)
    }

    private fun createTestData() = listOf(
        Todo(id = "119e6785-8ffc-42e0-8df6-dbc64881f2b7", title = "Taste JavaScript", completed = true),
        Todo(id = "d2f7760d-8f03-4cb3-9176-06311cb89993", title = "Buy a unicorn", completed = false)
    )
}
