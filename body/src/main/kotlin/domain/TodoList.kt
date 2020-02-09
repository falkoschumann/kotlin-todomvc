/*
 * TodoMVC
 * Copyright (c) 2020 Falko Schumann
 */

package de.muspellheim.todomvc.domain

class TodoList(private val todos: Todos) {

    fun setCompleted(isCompleted: Boolean) {
        val all = todos.all()
        all.forEach { it.isCompleted = isCompleted }
        todos.update(all)
    }

    fun clearCompleted() {
        val completed = todos.completed()
        todos.remove(completed)
    }
}
