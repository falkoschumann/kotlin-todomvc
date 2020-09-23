/*
 * TodoMVC
 * Copyright (c) 2020 Falko Schumann
 */

package de.muspellheim.todomvc.domain

class TodoList(private val todos: Todos) {

    fun setAllCompleted() {
        val completed = todos.all()
            .filter { it.isCompleted.not() }
            .map { it.apply { isCompleted = true } }
        todos.update(completed)
    }

    fun setAllActive() {
        val activated = todos.all()
            .filter { it.isActive.not() }
            .map { it.apply { isActive = true } }
        todos.update(activated)
    }

    fun clearCompleted() {
        val completed = todos.completed()
        todos.remove(completed)
    }
}
