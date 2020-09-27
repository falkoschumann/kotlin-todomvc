/*
 * TodoMVC
 * Copyright (c) 2020 Falko Schumann <falko.schumann@muspellheim.de>
 */

package de.muspellheim.todomvc.contract.messages

import de.muspellheim.todomvc.contract.data.Todo

object AllQuery
data class AllQueryResult(val todoList: List<Todo>, val todoCount: Int)

object ActiveQuery
data class ActiveQueryResult(val todoList: List<Todo>, val todoCount: Int)

object CompletedQuery
data class CompletedQueryResult(val todoList: List<Todo>, val todoCount: Int)
