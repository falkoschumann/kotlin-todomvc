/*
 * TodoMVC
 * Copyright (c) 2020 Falko Schumann <falko.schumann@muspellheim.de>
 */

package de.muspellheim.todomvc.frontend

import de.muspellheim.todomvc.contract.data.Todo
import de.muspellheim.todomvc.contract.messages.ActiveQuery
import de.muspellheim.todomvc.contract.messages.ActiveQueryResult
import de.muspellheim.todomvc.contract.messages.AllQuery
import de.muspellheim.todomvc.contract.messages.AllQueryResult
import de.muspellheim.todomvc.contract.messages.ClearCompletedCommand
import de.muspellheim.todomvc.contract.messages.CompletedQuery
import de.muspellheim.todomvc.contract.messages.CompletedQueryResult
import de.muspellheim.todomvc.contract.messages.DestroyCommand
import de.muspellheim.todomvc.contract.messages.EditCommand
import de.muspellheim.todomvc.contract.messages.NewTodoCommand
import de.muspellheim.todomvc.contract.messages.ToggleAllCommand
import de.muspellheim.todomvc.contract.messages.ToggleCommand
import javafx.scene.control.CheckBox
import javafx.scene.control.Label
import javafx.scene.control.ListView
import javafx.scene.control.TextField
import javafx.scene.control.ToggleButton
import javafx.scene.control.ToggleGroup
import javafx.scene.layout.Pane

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
        main.items.setAll(result.todoList)

        val hasTodos = result.todoList.isNotEmpty()
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
        main.items.setAll(result.todoList)
    }

    fun filterCompleted() {
        onCompletedQuery(CompletedQuery)
    }

    fun display(result: CompletedQueryResult) {
        main.items.setAll(result.todoList)
    }

    fun clearCompleted() {
        onClearCompletedCommand(ClearCompletedCommand)
    }
}
