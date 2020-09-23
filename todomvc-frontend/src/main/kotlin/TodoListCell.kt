/*
 * TodoMVC
 * Copyright (c) 2020 Falko Schumann
 */

package de.muspellheim.todomvc.portal

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView
import de.muspellheim.todomvc.domain.Todo
import javafx.application.Platform
import javafx.beans.binding.Bindings
import javafx.beans.value.ObservableValue
import javafx.css.Styleable
import javafx.scene.Node
import javafx.scene.control.Button
import javafx.scene.control.CheckBox
import javafx.scene.control.Label
import javafx.scene.control.ListCell
import javafx.scene.control.TextField
import javafx.scene.layout.HBox
import javafx.scene.layout.Priority
import java.util.concurrent.Callable

class TodoListCell : ListCell<Todo>() {

    override fun updateItem(item: Todo?, empty: Boolean) {
        super.updateItem(item, empty)

        if (empty || item == null) {
            text = null
            graphic = null
        } else {
            graphic = HBox(
                CheckBox().apply {
                    isSelected = item.isCompleted
                },
                Label().apply {
                    id = "contentLabel"
                    maxWidth = Double.MAX_VALUE
                    maxHeight = Double.MAX_VALUE
                    text = item.text
                    removeWhen { editingProperty() }
                    toggleClass("strikethrough", item.isCompleted)
                    HBox.setHgrow(this, Priority.ALWAYS)
                },
                TextField().apply {
                    text = item.text
                    removeWhen { editingProperty().not() }
                    whenVisible { requestFocus() }
                    HBox.setHgrow(this, Priority.ALWAYS)
                },
                Button().apply {
                    graphic = FontAwesomeIconView(FontAwesomeIcon.CLOSE).apply {
                        glyphSize = 22
                        styleClass.add("closeIcon")
                    }
                    // TODO parent existiert noch nicht im Konstruktor
                    removeWhen { parent.hoverProperty().not().or(editingProperty()) }
                }
            ).apply {
                styleClass += "itemRoot"
            }
        }
    }
}

fun <T : Node> T.removeWhen(expr: () -> ObservableValue<Boolean>): T = removeWhen(expr())
fun <T : Node> T.removeWhen(predicate: ObservableValue<Boolean>) = apply {
    val remove = Bindings.createBooleanBinding(Callable { predicate.value.not() })
    visibleProperty().bind(remove)
    managedProperty().bind(remove)
}

fun Node.whenVisible(runLater: Boolean = true, op: () -> Unit) {
    visibleProperty().addListener { _, _, newValue ->
        if (newValue) {
            if (runLater) Platform.runLater(op) else op()
        }
    }
}

fun <T : Styleable> T.toggleClass(className: String, predicate: Boolean) = apply {
    if (predicate) {
        if (!styleClass.contains(className)) styleClass.add(className)
    } else {
        styleClass.remove(className)
    }
}
