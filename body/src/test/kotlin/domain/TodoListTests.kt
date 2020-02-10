/*
 * TodoMVC
 * Copyright (c) 2020 Falko Schumann
 */

package de.muspellheim.todomvc.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class TodoListTests {

    private lateinit var fooTask: Todo
    private lateinit var barTask: Todo

    private lateinit var todos: TestingTodos
    private lateinit var todoList: TodoList

    @BeforeEach
    fun setUp() {
        fooTask = Todo("f", "Foo", false)
        barTask = Todo("b", "Bar", true)

        todos = TestingTodos()
        todos.all = listOf(fooTask, barTask)
        todos.active = listOf(fooTask)
        todos.completed = listOf(barTask)
        todoList = TodoList(todos)
    }

    @Test
    fun `set all completed`() {
        todoList.setAllCompleted()

        assertAll(
            { assertEquals(listOf(fooTask), todos.updated, "updated todos") },
            { assertTrue(fooTask.isCompleted, "foo is completed") },
            { assertTrue(barTask.isCompleted, "bar is completed") }
        )
    }

    @Test
    fun `set all active`() {
        todoList.setAllActive()

        assertAll(
            { assertEquals(listOf(barTask), todos.updated, "updated todos") },
            { assertTrue(fooTask.isActive, "foo is active") },
            { assertTrue(barTask.isActive, "bar is active") }
        )
    }

    @Test
    fun `completed cleared`() {
        todoList.clearCompleted()

        assertEquals(listOf(barTask), todos.removed, "removed todos")
    }
}
