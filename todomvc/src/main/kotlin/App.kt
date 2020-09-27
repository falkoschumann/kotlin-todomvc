/*
 * TodoMVC
 * Copyright (c) 2020 Falko Schumann <falko.schumann@muspellheim.de>
 */

package de.muspellheim.todomvc

import de.muspellheim.todomvc.backend.MessageHandler
import de.muspellheim.todomvc.backend.adapter.TodoJsonRepository
import de.muspellheim.todomvc.contract.messages.TodoListQuery
import de.muspellheim.todomvc.frontend.TodoAppViewController
import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.layout.Pane
import javafx.stage.Stage
import java.nio.file.Paths

class App : Application() {
    private lateinit var messageHandler: MessageHandler
    private lateinit var controller: TodoAppViewController

    override fun init() {
        // Build
        val repository = TodoJsonRepository(Paths.get("todos.json"))
        messageHandler = MessageHandler(repository)
    }

    override fun start(primaryStage: Stage) {
        // Build
        val url = TodoAppViewController::class.java.getResource("/TodoAppView.fxml")
        val loader = FXMLLoader(url)
        val root = loader.load<Pane>()
        controller = loader.getController<TodoAppViewController>()
        primaryStage.title = "Todos"
        primaryStage.scene = Scene(root)

        // Bind
        controller.onNewTodoCommand = {
            messageHandler.handle(it)
            runQuery()
        }
        controller.onToggleAllCommand = {
            messageHandler.handle(it)
            runQuery()
        }
        controller.onToggleCommand = {
            messageHandler.handle(it)
            runQuery()
        }
        controller.onDestroyCommand = {
            messageHandler.handle(it)
            runQuery()
        }
        controller.onEditCommand = {
            messageHandler.handle(it)
            runQuery()
        }
        controller.onClearCompletedCommand = {
            messageHandler.handle(it)
            runQuery()
        }
        controller.onTodoListQuery = {
            runQuery()
        }

        // Run
        primaryStage.show()
        runQuery()
    }

    private fun runQuery() {
        val result = messageHandler.handle(TodoListQuery)
        controller.display(result)
    }
}

fun main() {
    Application.launch(App::class.java)
}
