/*
 * TodoMVC
 * Copyright (c) 2020 Falko Schumann <falko.schumann@muspellheim.de>
 */

package de.muspellheim.todomvc

import de.muspellheim.todomvc.contract.data.Todo
import de.muspellheim.todomvc.contract.messages.AllQueryResult
import de.muspellheim.todomvc.frontend.TodoAppViewController
import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.layout.Pane
import javafx.stage.Stage

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
        // controller.display(AllQueryResult(listOf(), itemsLeft = 0))
        controller.display(
            AllQueryResult(
                todos = listOf(
                    Todo(id = "1", title = "Taste JavaScript", completed = true),
                    Todo(id = "2", title = "Buy a unicorn", completed = false)
                ),
                itemsLeft = 1
            )
        )

        // Rund
        primaryStage.show()
    }
}

fun main() {
    Application.launch(App::class.java)
}
