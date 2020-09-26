/*
 * TodoMVC
 * Copyright (c) 2020 Falko Schumann <falko.schumann@muspellheim.de>
 */

package de.muspellheim.todomvc.contract.messages

import de.muspellheim.todomvc.contract.data.Todo

object AllQuery
data class AllQueryResult(val todos: List<Todo>, val itemsLeft: Int)

object ActiveQuery
data class ActiveQueryResult(val todos: List<Todo>, val itemsLeft: Int)

object CompletedQuery
data class CompletedQueryResult(val todos: List<Todo>, val itemsLeft: Int)
