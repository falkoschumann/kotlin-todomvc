/*
 * TodoMVC
 * Copyright (c) 2020 Falko Schumann
 */

package de.muspellheim.todomvc.portal

import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.ToggleButton
import javafx.scene.control.ToggleGroup
import javafx.scene.layout.HBox

class FooterView : HBox(
    Label("0 item left"),
    HBox(
        *toggleGroup(
            ToggleButton("All"),
            ToggleButton("Active"),
            ToggleButton("Completed")
        )
    ),
    Button("Clear completed")
)

fun toggleGroup(vararg buttons: ToggleButton): Array<out ToggleButton> {
    val g = ToggleGroup()
    buttons.forEach { it.toggleGroup = g }
    return buttons
}
