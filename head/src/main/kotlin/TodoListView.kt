/*
 * TodoMVC
 * Copyright (c) 2020 Falko Schumann
 */

package de.muspellheim.todomvc.portal

import de.muspellheim.todomvc.domain.Todo
import javafx.scene.control.ListView
import javafx.util.Callback

class TodoListView : ListView<Todo>() {
    init {
        isEditable = true
        cellFactory = Callback { TodoListCell() }
        items.addAll(
            Todo("1", "Taste JavaScript", true),
            Todo("2", "Buy a unicorn", false)
        )
    }
}
