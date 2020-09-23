package de.muspellheim.todomvc.contract.messages

data class AddTodoCommand(val title: String)

data class ChangeTodoCommand(val id: String, val title: String)

data class CompleteTodoCommand(val id: String)

object ClearCompletedTodosCommand
