/*
 * TodoMVC
 * Copyright (c) 2020 Falko Schumann <falko.schumann@muspellheim.de>
 */

package de.muspellheim.todomvc.backend

import de.muspellheim.todomvc.contract.data.Todo

interface TodoRepository {
    fun load(): List<Todo>
    fun store(todoList: List<Todo>)
}
