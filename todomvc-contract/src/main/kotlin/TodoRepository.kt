package de.muspellheim.todomvc.contract

import de.muspellheim.todomvc.contract.data.Todo

/*
 * TodoMVC
 * Copyright (c) 2020 Falko Schumann <falko.schumann@muspellheim.de>
 */

interface TodoRepository {
    fun load(): List<Todo>
    fun store(todos: List<Todo>)
}
