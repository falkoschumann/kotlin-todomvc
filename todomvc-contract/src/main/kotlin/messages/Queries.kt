package de.muspellheim.todomvc.contract.messages

import de.muspellheim.todomvc.contract.data.Todo

object AllTodosQuery
data class AllTodosQueryResult(val todos: List<Todo>, val itemsLeft: Int)

object ActiveTodosQuery
data class ActiveTodosQueryResult(val todos: List<Todo>, val itemsLeft: Int)

object CompletedTodosQuery
data class CompletedTodosQueryResult(val todos: List<Todo>, val itemsLeft: Int)
