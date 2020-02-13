/*
 * TodoMVC
 * Copyright (c) 2020 Falko Schumann
 */

package de.muspellheim.todomvc.portal

import javafx.beans.property.BooleanProperty
import javafx.beans.property.IntegerProperty
import javafx.beans.property.ObjectProperty
import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import javafx.beans.property.StringProperty
import javafx.collections.FXCollections

class TodoListViewModel {
    val allCompletedProperty: BooleanProperty = SimpleBooleanProperty()
    val items = FXCollections.observableArrayList<TodoViewModel>()

    val itemsLeftProperty: IntegerProperty = SimpleIntegerProperty()
    val filterProperty: ObjectProperty<Filter> = SimpleObjectProperty()

    fun addTodo(todo: String) {
        TODO()
    }

    fun clearCompleted() {
        TODO()
    }

    enum class Filter { All, Active, Completed }
}

class TodoViewModel {
    val completedProperty: BooleanProperty = SimpleBooleanProperty()
    val textProperty: StringProperty = SimpleStringProperty()

    fun remove() {
        TODO()
    }
}
