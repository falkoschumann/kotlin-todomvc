/*
 * TodoMVC
 * Copyright (c) 2020 Falko Schumann <falko.schumann@muspellheim.de>
 */

package de.muspellheim.todomvc.frontend

import de.muspellheim.todomvc.contract.data.Todo
import de.muspellheim.todomvc.contract.messages.ClearCompletedCommand
import de.muspellheim.todomvc.contract.messages.DestroyCommand
import de.muspellheim.todomvc.contract.messages.EditCommand
import de.muspellheim.todomvc.contract.messages.NewTodoCommand
import de.muspellheim.todomvc.contract.messages.TodoListQuery
import de.muspellheim.todomvc.contract.messages.TodoListQueryResult
import de.muspellheim.todomvc.contract.messages.ToggleAllCommand
import de.muspellheim.todomvc.contract.messages.ToggleCommand
import javafx.scene.control.Button
import javafx.scene.control.CheckBox
import javafx.scene.control.ListView
import javafx.scene.control.TextField
import javafx.scene.control.ToggleButton
import javafx.scene.control.ToggleGroup
import javafx.scene.layout.Pane
import javafx.scene.text.Text
import javafx.scene.text.TextFlow

class TodoAppViewController {
    lateinit var todoapp: Pane

    lateinit var onNewTodoCommand: (command: NewTodoCommand) -> Unit
    lateinit var onToggleAllCommand: (command: ToggleAllCommand) -> Unit
    lateinit var onToggleCommand: (command: ToggleCommand) -> Unit
    lateinit var onEditCommand: (command: EditCommand) -> Unit
    lateinit var onDestroyCommand: (command: DestroyCommand) -> Unit
    lateinit var onClearCompletedCommand: (command: ClearCompletedCommand) -> Unit
    lateinit var onTodoListQuery: (query: TodoListQuery) -> Unit

    lateinit var header: Pane
    lateinit var toggleAll: CheckBox
    lateinit var newTodo: TextField

    lateinit var main: ListView<TodoModel>

    lateinit var footer: Pane
    lateinit var todoCount: TextFlow
    lateinit var filters: ToggleGroup
    lateinit var all: ToggleButton
    lateinit var active: ToggleButton
    lateinit var completed: ToggleButton
    lateinit var clearCompleted: Button

    lateinit var info: Pane

    private var todoList = listOf<Todo>()

    // TODO edit inn List Cell
    // TODO destroy inn List Cell

    fun display(result: TodoListQueryResult) {
        todoList = result.todoList
        updateTodoList()

        val completedCount = result.todoList.filter { it.completed }.count()
        val activeTodoCount = result.todoList.size - completedCount

        val hasTodos = result.todoList.isNotEmpty()
        toggleAll.isVisible = hasTodos
        main.isVisible = hasTodos
        main.isManaged = hasTodos
        footer.isVisible = hasTodos
        footer.isManaged = hasTodos

        toggleAll.isSelected = result.todoList.size == completedCount

        val text = Text(activeTodoCount.toString())
        text.style = "-fx-font-weight: bold"
        todoCount.children.setAll(
            text,
            Text(" ${if (completedCount == 1) "item" else "items"} left")
        )

        clearCompleted.isVisible = completedCount > 0
    }

    private fun updateTodoList() {
        val todos = when (filters.selectedToggle) {
            active -> todoList.filter { !it.completed }
            completed -> todoList.filter { it.completed }
            else -> todoList
        }.map { TodoModel(it, onToggleCommand, onEditCommand, onDestroyCommand) }
        main.items.setAll(todos)
    }

    fun newTodo() {
        if (newTodo.text.isBlank()) {
            return
        }
        onNewTodoCommand(NewTodoCommand(newTodo.text.trim()))
        newTodo.text = ""
    }

    fun toggleAll() {
        val checked = toggleAll.isSelected
        onToggleAllCommand(ToggleAllCommand(checked))
    }

    fun filterAll() {
        updateTodoList()
    }

    fun filterActive() {
        updateTodoList()
    }

    fun filterCompleted() {
        updateTodoList()
    }

    fun clearCompleted() {
        onClearCompletedCommand(ClearCompletedCommand)
    }
}
