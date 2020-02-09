/*
 * TodoMVC
 * Copyright (c) 2020 Falko Schumann
 */

package de.muspellheim.todomvc.portal

import javafx.scene.layout.BorderPane

class MainView : BorderPane() {
    init {
        top = HeaderView()
        center = TodoListView()
        bottom = FooterView()
    }
}
