/*
 * TodoMVC
 * Copyright (c) 2020 Falko Schumann <falko.schumann@muspellheim.de>
 */

package de.muspellheim.todomvc

import de.muspellheim.todomvc.contract.data.*
import de.muspellheim.todomvc.contract.messages.*
import de.muspellheim.todomvc.frontend.*
import javafx.application.*
import javafx.fxml.*
import javafx.scene.*
import javafx.scene.layout.*
import javafx.stage.*

class App : Application() {
    override fun start(primaryStage: Stage) {
        // Build
        val url = TodoAppViewController::class.java.getResource("/TodoAppView.fxml")
        val loader = FXMLLoader(url)
        val root = loader.load<Pane>()
        val controller = loader.getController<TodoAppViewController>()
        primaryStage.title = "Todos"
        primaryStage.scene = Scene(root)

        // Bind
        controller.onToggleAllCommand = { println(it) }
        controller.onNewTodoCommand = { println(it) }
        //controller.display(AllQueryResult(listOf(), itemsLeft = 0))
        controller.display(AllQueryResult(
            todos = listOf(
                Todo(id = "1", title = "Taste JavaScript", completed = true),
                Todo(id = "2", title = "Buy a unicorn", completed = false)
            ),
            itemsLeft = 1)
        )

        // Rund
        primaryStage.show()
    }
}

fun main() {
    Application.launch(App::class.java)
}
