/*
 * TodoMVC
 * Copyright (c) 2020 Falko Schumann
 */

package de.muspellheim.todomvc.portal

import javafx.scene.control.*
import javafx.scene.layout.*

class HeaderView : VBox(
    Label("todos"),
    HBox(
        CheckBox(),
        TextField().apply {
            promptText = "What needs to be done?"
        }
    )
)
