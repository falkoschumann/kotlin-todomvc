/*
 * TodoMVC
 * Copyright (c) 2020 Falko Schumann
 */

package de.muspellheim.todomvc.portal

import javafx.scene.control.CheckBox
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox

class HeaderView : VBox(
    Label("todos"),
    HBox(
        CheckBox(),
        TextField().apply {
            promptText = "What needs to be done?"
        }
    )
)
