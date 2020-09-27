/*
 * TodoMVC
 * Copyright (c) 2020 Falko Schumann <falko.schumann@muspellheim.de>
 */

package de.muspellheim.todomvc.frontend

import de.muspellheim.todomvc.contract.messages.DestroyCommand
import de.muspellheim.todomvc.contract.messages.EditCommand
import de.muspellheim.todomvc.contract.messages.ToggleCommand
import javafx.event.EventHandler
import javafx.scene.control.Button
import javafx.scene.control.CheckBox
import javafx.scene.control.Label
import javafx.scene.control.ListCell
import javafx.scene.control.ListView
import javafx.scene.control.TextField
import javafx.scene.input.MouseButton
import javafx.scene.layout.HBox
import javafx.scene.layout.Priority
import javafx.util.Callback

class TodoListCell : ListCell<TodoModel>() {

    private val container = HBox()
    private val completed = CheckBox()
    private val title = Label()
    private val destroy = Button("X")
    private val edit = TextField()

    init {
        title.maxWidth = Double.MAX_VALUE
        title.maxHeight = Double.MAX_VALUE
        HBox.setHgrow(title, Priority.ALWAYS)

        HBox.setHgrow(edit, Priority.ALWAYS)

        destroy.isVisible = false
        container.hoverProperty().addListener { _, _, newValue ->
            destroy.isVisible = newValue && container.children.contains(title)
        }

        container.children.setAll(completed, title, destroy)
    }

    override fun updateItem(item: TodoModel?, empty: Boolean) {
        super.updateItem(item, empty)

        if (empty || item == null) {
            text = null
            graphic = null
        } else {
            graphic = container

            completed.isSelected = item.todo.completed
            completed.onAction = EventHandler { item.onToggleCommand(ToggleCommand(item.todo.id)) }

            title.text = item.todo.title
            title.onMouseClicked = EventHandler {
                if (it.button == MouseButton.PRIMARY && it.clickCount == 2) {
                    container.children[1] = edit
                    destroy.isVisible = false
                }
            }

            edit.text = item.todo.title
            edit.onAction = EventHandler {
                item.onEditCommand(EditCommand(item.todo.id, edit.text))
                container.children[1] = title
                destroy.isVisible = container.isHover
            }

            destroy.onAction = EventHandler { item.onDestroyCommand(DestroyCommand(item.todo.id)) }
        }
    }
}

class TodoListCellFactory : Callback<ListView<TodoModel>, ListCell<TodoModel>> {
    override fun call(param: ListView<TodoModel>): ListCell<TodoModel> = TodoListCell()
}
