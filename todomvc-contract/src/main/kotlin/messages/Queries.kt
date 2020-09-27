/*
 * TodoMVC
 * Copyright (c) 2020 Falko Schumann <falko.schumann@muspellheim.de>
 */

package de.muspellheim.todomvc.contract.messages

import de.muspellheim.todomvc.contract.data.Todo

object TodoListQuery
data class TodoListQueryResult(val todoList: List<Todo>)
