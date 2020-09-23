/*
 * TodoMVC
 * Copyright (c) 2020 Falko Schumann
 */

package de.muspellheim.todomvc.portal

import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.layout.BorderPane
import javafx.stage.Stage

class MainView : BorderPane() {
    init {
        top = HeaderView()
        center = TodoListView()
        bottom = FooterView()
        stylesheets += "style.css"
    }
}

fun main() {
    Application.launch(TestingMainViewApp::class.java)
}

class TestingMainViewApp : Application() {
    override fun start(stage: Stage) {
        stage.scene = Scene(MainView())
        stage.show()
    }
}
