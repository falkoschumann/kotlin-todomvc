/*
 * TodoMVC
 * Copyright (c) 2020 Falko Schumann
 */

package de.muspellheim.todomvc.backend

import de.muspellheim.todomvc.contract.data.Todo
import de.muspellheim.todomvc.contract.messages.ActiveQuery
import de.muspellheim.todomvc.contract.messages.ActiveQueryResult
import de.muspellheim.todomvc.contract.messages.AllQuery
import de.muspellheim.todomvc.contract.messages.AllQueryResult
import de.muspellheim.todomvc.contract.messages.ClearCompletedCommand
import de.muspellheim.todomvc.contract.messages.CommandStatus
import de.muspellheim.todomvc.contract.messages.CompletedQuery
import de.muspellheim.todomvc.contract.messages.CompletedQueryResult
import de.muspellheim.todomvc.contract.messages.DestroyCommand
import de.muspellheim.todomvc.contract.messages.EditCommand
import de.muspellheim.todomvc.contract.messages.NewTodoCommand
import de.muspellheim.todomvc.contract.messages.Success
import de.muspellheim.todomvc.contract.messages.ToggleAllCommand
import de.muspellheim.todomvc.contract.messages.ToggleCommand

class MessageHandler(private val repository: TodoRepository) {
    fun handle(command: NewTodoCommand): CommandStatus {
        val todoList = repository.load() + Todo(command.title)
        repository.store(todoList)
        return Success
    }

    fun handle(command: ToggleAllCommand): CommandStatus {
        var todoList = repository.load()
        todoList = todoList.map { it.copy(completed = command.completed) }
        repository.store(todoList)
        return Success
    }

    fun handle(command: ToggleCommand): CommandStatus {
        var todoList = repository.load()
        todoList = todoList.map {
            if (it.id == command.id) {
                it.copy(completed = !it.completed)
            } else {
                it
            }
        }
        repository.store(todoList)
        return Success
    }

    fun handle(command: DestroyCommand): CommandStatus {
        var todoList = repository.load()
        todoList = todoList.filter { it.id != command.id }
        repository.store(todoList)
        return Success
    }

    fun handle(command: EditCommand): CommandStatus {
        var todoList = repository.load()
        todoList = todoList.map {
            if (it.id == command.id) {
                it.copy(title = command.title)
            } else {
                it
            }
        }
        repository.store(todoList)
        return Success
    }

    fun handle(command: ClearCompletedCommand): CommandStatus {
        var todoList = repository.load()
        todoList = todoList.filter { !it.completed }
        repository.store(todoList)
        return Success
    }

    fun handle(query: AllQuery): AllQueryResult {
        val todoList = repository.load()
        val todoCount = todoList.filter { !it.completed }.count()
        return AllQueryResult(todoList, todoCount)
    }

    fun handle(query: ActiveQuery): ActiveQueryResult {
        var todoList = repository.load()
        todoList = todoList.filter { !it.completed }
        val todoCount = todoList.count()
        return ActiveQueryResult(todoList, todoCount)
    }

    fun handle(query: CompletedQuery): CompletedQueryResult {
        var todoList = repository.load()
        val todoCount = todoList.filter { !it.completed }.count()
        todoList = todoList.filter { it.completed }
        return CompletedQueryResult(todoList, todoCount)
    }
}
