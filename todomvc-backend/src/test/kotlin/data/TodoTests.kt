/*
 * TodoMVC
 * Copyright (c) 2020 Falko Schumann
 */

package de.muspellheim.todomvc.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows

class TodoTests {

    @Test
    fun `create active todo`() {
        val todo = todoOf("Foo")

        assertAll(
            { assertTrue(todo.id.isNotEmpty(), "id created") },
            { assertEquals("Foo", todo.text, "text set") },
            { assertTrue(todo.isActive, "is active") },
            { assertFalse(todo.isCompleted, "is not completed") }
        )
    }

    @Test
    fun `create completed todo`() {
        val todo = todoOf("Foo")
        todo.isCompleted = true

        assertAll(
            { assertTrue(todo.id.isNotEmpty(), "id created") },
            { assertEquals("Foo", todo.text, "text set") },
            { assertFalse(todo.isActive, "is not active") },
            { assertTrue(todo.isCompleted, "is completed") }
        )
    }

    @Test
    fun `text will be trimmed`() {
        val todo = todoOf("  Foo ")

        assertEquals("Foo", todo.text, "text trimmed")
    }

    @Test
    fun `text must not be empty`() {
        assertThrows<IllegalArgumentException>("text must not be empty") { todoOf("") }
    }

    @Test
    fun `trimmed text must not be empty`() {
        assertThrows<IllegalArgumentException>("text must not be empty") { todoOf("  ") }
    }
}
