/*
 * TodoMVC
 * Copyright (c) 2020 Falko Schumann
 */

package de.muspellheim.todomvc.domain

import java.util.UUID

/**
 * Entity and aggregate to-do.
 */
class Todo(
    val id: String,
    var text: String,
    var isCompleted: Boolean
) {
    var isActive: Boolean
        get() = !isCompleted
        set(value) {
            isCompleted = !value
        }

    override fun toString() = "$text ($id)"
}

/**
 * Factory for to-do.
 */
fun todoOf(text: String): Todo {
    val s = text.trim()
    if (s.isEmpty()) {
        throw IllegalArgumentException("text must not be empty")
    }
    return Todo(UUID.randomUUID().toString(), s, false)
}

/**
 * Repository for to-do aggregate.
 */
interface Todos {
    fun all(): List<Todo>
    fun active(): List<Todo>
    fun completed(): List<Todo>

    fun add(todo: Todo)

    fun update(todo: Todo)
    fun update(todos: List<Todo>)

    fun remove(todo: Todo)
    fun remove(todos: List<Todo>)
}
