/*
 * TodoMVC
 * Copyright (c) 2020 Falko Schumann
 */

package de.muspellheim.todomvc.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class TodoListTests {

    @Test
    fun `active todo items`() {
        val foo = Todo("Foo")
        val bar = Todo("Bar", true)
        val todoList = TodoList(listOf(foo, bar))

        todoList.filter = TodoList.Filter.Active

        assertEquals(listOf(Todo("Foo")), todoList.filteredItems(), "filtered items")
    }

    @Test
    fun `completed todo items`() {
        val foo = Todo("Foo")
        val bar = Todo("Bar", true)
        val todoList = TodoList(listOf(foo, bar))

        todoList.filter = TodoList.Filter.Completed

        assertEquals(listOf(Todo("Bar", true)), todoList.filteredItems(), "filtered items")
    }

    @Test
    fun `todo added`() {
        val foo = Todo("Foo")
        val todoList = TodoList(listOf(foo))

        todoList.addTodo("Bar")

        assertEquals(listOf(Todo("Foo"), Todo("Bar")), todoList.allItems(), "all items")
        assertEquals(listOf(Todo("Foo"), Todo("Bar")), todoList.filteredItems(), "filtered items")
        assertEquals(2, todoList.activeCount, "active count")
    }

    @Test
    fun `new todo text must not be empty`() {
        val todoList = TodoList()

        assertThrows<IllegalArgumentException>("text must not be empty") { todoList.addTodo("") }
    }

    @Test
    fun `todo changed`() {
        val foo = Todo("Foo")
        val bar = Todo("Bar")
        val todoList = TodoList(listOf(foo, bar))

        todoList.changeTodo(foo, "Test")

        assertEquals(listOf(Todo("Test"), Todo("Bar")), todoList.allItems(), "all items")
        assertEquals(listOf(Todo("Test"), Todo("Bar")), todoList.filteredItems(), "filtered items")
        assertEquals(2, todoList.activeCount, "active count")
    }

    @Test
    fun `changed todo text must not be empty`() {
        val foo = Todo("Foo")
        val todoList = TodoList(listOf(foo))

        assertThrows<IllegalArgumentException>("text must not be empty") { todoList.changeTodo(foo, "") }
    }

    @Test
    fun `todo removed`() {
        val foo = Todo("Foo")
        val bar = Todo("Bar")
        val todoList = TodoList(listOf(foo, bar))

        todoList.removeTodo(foo)

        assertEquals(listOf(Todo("Bar")), todoList.allItems(), "all items")
        assertEquals(listOf(Todo("Bar")), todoList.filteredItems(), "filtered items")
        assertEquals(1, todoList.activeCount, "active count")
    }

    @Test
    fun `completed toggled`() {
        val foo = Todo("Foo")
        val bar = Todo("Bar", true)
        val todoList = TodoList(listOf(foo, bar))

        todoList.toggleCompleted(foo)

        assertEquals(listOf(Todo("Foo", true), Todo("Bar", true)), todoList.allItems(), "all items")
        assertEquals(listOf(Todo("Foo", true), Todo("Bar", true)), todoList.filteredItems(), "filtered items")
        assertEquals(0, todoList.activeCount, "active count")
    }

    @Test
    fun `all completed toggled`() {
        val foo = Todo("Foo")
        val bar = Todo("Bar", true)
        val todoList = TodoList(listOf(foo, bar))

        todoList.toggleAllCompleted(true)

        assertEquals(listOf(Todo("Foo", true), Todo("Bar", true)), todoList.allItems(), "all items")
        assertEquals(listOf(Todo("Foo", true), Todo("Bar", true)), todoList.filteredItems(), "filtered items")
        assertEquals(0, todoList.activeCount, "active count")
    }

    @Test
    fun `completed cleared`() {
        val foo = Todo("Foo")
        val bar = Todo("Bar", true)
        val todoList = TodoList(listOf(foo, bar))

        todoList.clearCompleted()

        assertEquals(listOf(Todo("Foo")), todoList.allItems(), "all items")
        assertEquals(listOf(Todo("Foo")), todoList.filteredItems(), "filtered items")
        assertEquals(1, todoList.activeCount, "active count")
    }
}
