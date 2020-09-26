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
    lateinit var todoapp: Pane

    lateinit var onNewTodoCommand: (command: NewTodoCommand) -> Unit
    lateinit var onToggleAllCommand: (command: ToggleAllCommand) -> Unit
    lateinit var onToggleCommand: (command: ToggleCommand) -> Unit
    lateinit var onDestroyCommand: (command: DestroyCommand) -> Unit
    lateinit var onEditCommand: (command: EditCommand) -> Unit
    lateinit var onClearCompletedCommand: (command: ClearCompletedCommand) -> Unit
    lateinit var onAllQuery: (query: AllQuery) -> Unit
    lateinit var onActiveQuery: (query: ActiveQuery) -> Unit
    lateinit var onCompletedQuery: (query: CompletedQuery) -> Unit

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
        val checked = toggleAll.isSelected
        onToggleAllCommand(ToggleAllCommand(checked))
    }

    fun newTodo() {
        if (newTodo.text.isBlank()) {
            return
        }
        onNewTodoCommand(NewTodoCommand(newTodo.text.trim()))
        newTodo.text = ""
    }

    fun filterAll() {
        onAllQuery(AllQuery)
    }

    fun display(result: AllQueryResult) {
        main.items.setAll(result.todos)

        val hasTodos = result.todos.isNotEmpty()
        toggleAll.isVisible = hasTodos
        main.isVisible = hasTodos
        main.isManaged = hasTodos
        footer.isVisible = hasTodos
        footer.isManaged = hasTodos
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
