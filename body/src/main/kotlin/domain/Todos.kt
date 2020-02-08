/*
 * TodoMVC
 * Copyright (c) 2020 Falko Schumann
 */

package de.muspellheim.todomvc.domain

interface Todos {
    fun load(): TodoList
    fun store(todoList: TodoList)
}
