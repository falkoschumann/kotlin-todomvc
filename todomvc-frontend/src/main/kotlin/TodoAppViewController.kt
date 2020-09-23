/*
 * TodoMVC
 * Copyright (c) 2020 Falko Schumann <falko.schumann@muspellheim.de>
 */

package de.muspellheim.todomvc.frontend

import de.muspellheim.todomvc.contract.data.*
import de.muspellheim.todomvc.contract.messages.*
import javafx.scene.control.*
import javafx.scene.layout.*

class TodoAppViewController {
    lateinit var onNewTodoCommand: (command: NewTodoCommand) -> CommandStatus
    lateinit var onToggleAllCommand: (command: ToggleAllCommand) -> CommandStatus
    lateinit var onToggleCommand: (command: ToggleCommand) -> CommandStatus
    lateinit var onDestroyCommand: (command: DestroyCommand) -> CommandStatus
    lateinit var onEditCommand: (command: EditCommand) -> CommandStatus
    lateinit var onClearCompletedCommand: (command: ClearCompletedCommand) -> CommandStatus
    lateinit var onAllQuery: (query: AllQuery) -> AllQueryResult
    lateinit var onActiveQuery: (query: ActiveQuery) -> ActiveQueryResult
    lateinit var onCompletedQuery: (query: CompletedQuery) -> CompletedQueryResult

    lateinit var header: Pane
    lateinit var toggleAll: CheckBox
    lateinit var newTodo: TextField

    lateinit var main: ListView<Todo>

    lateinit var footer: Pane
    lateinit var todoCount: Label
    lateinit var filters: ToggleGroup
    lateinit var all: ToggleButton
    lateinit var active: ToggleButton
    lateinit var completed: ToggleButton

    lateinit var info: Pane

    fun toggleAll() {
        onToggleAllCommand(ToggleAllCommand)
    }

    fun newTodo() {
        if (newTodo.text.isBlank()) {
            return
        }
        onNewTodoCommand(NewTodoCommand(newTodo.text))
    }

    fun filterAll() {
        onAllQuery(AllQuery)
    }

    fun display(result: AllQueryResult) {
        main.items.setAll(result.todos)
    }

    fun filterActive() {
        onActiveQuery(ActiveQuery)
    }

    fun display(result: ActiveQueryResult) {
        main.items.setAll(result.todos)
    }

    fun filterCompleted() {
        onCompletedQuery(CompletedQuery)
    }

    fun display(result: CompletedQueryResult) {
        main.items.setAll(result.todos)
    }

    fun clearCompleted() {
        onClearCompletedCommand(ClearCompletedCommand)
    }
}
