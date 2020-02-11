/*
 * TodoMVC
 * Copyright (c) 2020 Falko Schumann
 */

package de.muspellheim.todomvc.portal

import de.muspellheim.todomvc.domain.Todo
import javafx.beans.InvalidationListener
import javafx.scene.control.Button
import javafx.scene.control.CheckBox
import javafx.scene.control.Label
import javafx.scene.control.ListCell
import javafx.scene.control.TextField
import javafx.scene.layout.HBox
import javafx.scene.layout.Priority

class TodoListCell : ListCell<Todo>() {

    override fun updateItem(item: Todo?, empty: Boolean) {
        super.updateItem(item, empty)

        if (empty || item == null) {
            text = null
            graphic = null
        } else {
            graphic = HBox(
                CheckBox(),
                Label().apply {
                    val remove = editingProperty().not()
                    visibleProperty().bind(remove)
                    managedProperty().bind(remove)
                }.also {
                    HBox.setHgrow(it, Priority.ALWAYS)
                },
                TextField().apply {
                    visibleProperty().bind(editingProperty())
                    managedProperty().bind(editingProperty())
                    visibleProperty().addListener(InvalidationListener { requestFocus() })
                }.also {
                    HBox.setHgrow(it, Priority.ALWAYS)
                },
                Button().apply {
                    val remove = parent.hoverProperty().not().or(editingProperty())
                    visibleProperty().bind(remove)
                    managedProperty().bind(remove)
                }
            )
        }
    }
}
