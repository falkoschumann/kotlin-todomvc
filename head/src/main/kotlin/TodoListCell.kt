/*
 * TodoMVC
 * Copyright (c) 2020 Falko Schumann
 */

package de.muspellheim.todomvc.portal

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView
import de.muspellheim.todomvc.domain.Todo
import javafx.application.Platform
import javafx.beans.InvalidationListener
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
                Label(item.text).apply {
                    id = "contentLabel"
                    maxWidth = Double.MAX_VALUE
                    maxHeight = Double.MAX_VALUE
                    removeWhen { editingProperty() }
                    toggleClass("strikethrough", item.isCompleted)
                }.also {
                    HBox.setHgrow(it, Priority.ALWAYS)
                },
                TextField(item.text).apply {
                    removeWhen { editingProperty().not() }
                    whenVisible { requestFocus() }
                }.also {
                    HBox.setHgrow(it, Priority.ALWAYS)
                },
                Button().apply {
                    graphic = FontAwesomeIconView(FontAwesomeIcon.CLOSE).apply {
                        glyphSize = 22
                        styleClass.add("closeIcon")
                    }
                    // TODO parent existiert noch nicht im Konstruktor
                    //removeWhen { parent.hoverProperty().not().or(editingProperty()) }
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
    visibleProperty().onChange {
        if (it) {
            if (runLater) Platform.runLater(op) else op()
        }
    }
}

fun <T> ObservableValue<T>.onChange(handler: (T) -> Unit) {
    addListener(InvalidationListener { handler(value) })
}

fun <T : Styleable> T.toggleClass(className: String, predicate: Boolean) = apply {
    if (predicate) {
        if (!styleClass.contains(className)) styleClass.add(className)
    } else {
        styleClass.remove(className)
    }
}
