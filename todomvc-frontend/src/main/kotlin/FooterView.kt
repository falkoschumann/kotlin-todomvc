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

class FooterView : HBox() {
    init {
        styleClass += "footer"

        val filterGroup = ToggleGroup()
        children.addAll(
            Label("0 item left"),
            HBox(
                ToggleButton("All").apply {
                    toggleGroup = filterGroup
                    isSelected = true
                }, ToggleButton("Active").apply {
                    toggleGroup = filterGroup
                }, ToggleButton("Completed").apply {
                    toggleGroup = filterGroup
                }
            ),
            Button("Clear completed")
        )
    }
}
