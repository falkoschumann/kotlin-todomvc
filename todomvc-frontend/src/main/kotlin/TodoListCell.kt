/*
 * TodoMVC
 * Copyright (c) 2020 Falko Schumann
 */

package de.muspellheim.todomvc.portal

import de.jensd.fx.glyphs.fontawesome.*
import de.muspellheim.todomvc.contract.data.*
import javafx.scene.control.*
import javafx.scene.layout.*
import javafx.util.*

class TodoListCell : ListCell<Todo>() {

    private val container = HBox()
    private val completed = CheckBox()
    private val title = Label()
    private val destroy = Button()
    private val edit = TextField()

    init {
        title.maxWidth = Double.MAX_VALUE
        title.maxHeight = Double.MAX_VALUE
        HBox.setHgrow(title, Priority.ALWAYS)

        val icon = FontAwesomeIconView(FontAwesomeIcon.CLOSE)
        icon.glyphSize = 22
        destroy.graphic = icon

        container.children.setAll(completed, title, destroy)
    }

    override fun updateItem(item: Todo?, empty: Boolean) {
        super.updateItem(item, empty)

        if (empty || item == null) {
            text = null
            graphic = null
        } else {
            graphic = container
            completed.isSelected = item.completed
            title.text = item.title
            edit.text = item.title
        }
    }
}

class TodoListCellFactory : Callback<ListView<Todo>, ListCell<Todo>> {
    override fun call(param: ListView<Todo>): ListCell<Todo> = TodoListCell()
}
