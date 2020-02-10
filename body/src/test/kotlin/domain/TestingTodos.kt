/*
 * TodoMVC
 * Copyright (c) 2020 Falko Schumann
 */

package de.muspellheim.todomvc.domain

class TestingTodos : Todos {

    var all = emptyList<Todo>()
    var active = emptyList<Todo>()
    var completed = emptyList<Todo>()

    var added = mutableListOf<Todo>()
    var updated = mutableListOf<Todo>()
    var removed = mutableListOf<Todo>()

    override fun all(): List<Todo> = all

    override fun active(): List<Todo> = active

    override fun completed(): List<Todo> = completed

    override fun add(todo: Todo) {
        added.add(todo)
    }

    override fun update(todo: Todo) {
        updated.add(todo)
    }

    override fun update(todos: List<Todo>) {
        updated.addAll(todos)
    }

    override fun remove(todo: Todo) {
        removed.add(todo)
    }

    override fun remove(todos: List<Todo>) {
        removed.addAll(todos)
    }
}
