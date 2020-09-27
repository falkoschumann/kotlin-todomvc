package de.muspellheim.todomvc.backend

import de.muspellheim.todomvc.contract.data.Todo
import de.muspellheim.todomvc.contract.messages.ActiveQuery
import de.muspellheim.todomvc.contract.messages.ActiveQueryResult
import de.muspellheim.todomvc.contract.messages.AllQuery
import de.muspellheim.todomvc.contract.messages.AllQueryResult
import de.muspellheim.todomvc.contract.messages.ClearCompletedCommand
import de.muspellheim.todomvc.contract.messages.CompletedQuery
import de.muspellheim.todomvc.contract.messages.CompletedQueryResult
import de.muspellheim.todomvc.contract.messages.DestroyCommand
import de.muspellheim.todomvc.contract.messages.EditCommand
import de.muspellheim.todomvc.contract.messages.NewTodoCommand
import de.muspellheim.todomvc.contract.messages.Success
import de.muspellheim.todomvc.contract.messages.ToggleAllCommand
import de.muspellheim.todomvc.contract.messages.ToggleCommand
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/*
 * TodoMVC
 * Copyright (c) 2020 Falko Schumann <falko.schumann@muspellheim.de>
 */

class MessageHandlerTests {
    private lateinit var repository: TestingTodoRepository
    private lateinit var messageHandler: MessageHandler

    @BeforeEach
    fun setUp() {
        repository = TestingTodoRepository()
        messageHandler = MessageHandler(repository)
    }

    @Test
    fun `new todo command`() {
        val command = NewTodoCommand(title = "Foobar")

        val result = messageHandler.handle(command)

        assertEquals(Success, result, "Command status")
        assertEquals(3, repository.todoList.size, "Todo list size")
        assertNotNull(repository.todoList[2].id, "New todo id")
        assertEquals("Foobar", repository.todoList[2].title, "New todo title")
        assertFalse(repository.todoList[2].completed, "New todo completed")
    }

    @Test
    fun `toggle all Command`() {
        val command = ToggleAllCommand(completed = true)

        val result = messageHandler.handle(command)

        assertEquals(Success, result, "Command status")
        assertEquals(
            listOf(
                Todo(id = "119e6785-8ffc-42e0-8df6-dbc64881f2b7", title = "Taste JavaScript", completed = true),
                Todo(id = "d2f7760d-8f03-4cb3-9176-06311cb89993", title = "Buy a unicorn", completed = true),
            ),
            repository.todoList,
            "Updated todo list"
        )
    }

    @Test
    fun `toggle command`() {
        val command = ToggleCommand(id = "d2f7760d-8f03-4cb3-9176-06311cb89993")

        val result = messageHandler.handle(command)

        assertEquals(Success, result, "Command status")
        assertEquals(
            listOf(
                Todo(id = "119e6785-8ffc-42e0-8df6-dbc64881f2b7", title = "Taste JavaScript", completed = true),
                Todo(id = "d2f7760d-8f03-4cb3-9176-06311cb89993", title = "Buy a unicorn", completed = true),
            ),
            repository.todoList,
            "Updated todo list"
        )
    }

    @Test
    fun `destroy command`() {
        val command = DestroyCommand(id = "119e6785-8ffc-42e0-8df6-dbc64881f2b7")

        val result = messageHandler.handle(command)

        assertEquals(Success, result, "Command status")
        assertEquals(
            listOf(
                Todo(id = "d2f7760d-8f03-4cb3-9176-06311cb89993", title = "Buy a unicorn", completed = false),
            ),
            repository.todoList,
            "Updated todo list"
        )
    }

    @Test
    fun `edit command`() {
        val command = EditCommand(id = "d2f7760d-8f03-4cb3-9176-06311cb89993", title = "Foobar")

        val result = messageHandler.handle(command)

        assertEquals(Success, result, "Command status")
        assertEquals(
            listOf(
                Todo(id = "119e6785-8ffc-42e0-8df6-dbc64881f2b7", title = "Taste JavaScript", completed = true),
                Todo(id = "d2f7760d-8f03-4cb3-9176-06311cb89993", title = "Foobar", completed = false),
            ),
            repository.todoList,
            "Updated todo list"
        )
    }

    @Test
    fun `clear completed command`() {
        val command = ClearCompletedCommand

        val result = messageHandler.handle(command)

        assertEquals(Success, result, "Command status")
        assertEquals(
            listOf(
                Todo(id = "d2f7760d-8f03-4cb3-9176-06311cb89993", title = "Buy a unicorn", completed = false),
            ),
            repository.todoList,
            "Updated todo list"
        )
    }

    @Test
    fun `all query`() {
        val query = AllQuery

        val result = messageHandler.handle(query)

        assertEquals(
            AllQueryResult(
                todoList = listOf(
                    Todo(id = "119e6785-8ffc-42e0-8df6-dbc64881f2b7", title = "Taste JavaScript", completed = true),
                    Todo(id = "d2f7760d-8f03-4cb3-9176-06311cb89993", title = "Buy a unicorn", completed = false)
                ),
                todoCount = 1
            ),
            result,
            "Query result"
        )
    }

    @Test
    fun `active query`() {
        val query = ActiveQuery

        val result = messageHandler.handle(query)

        assertEquals(
            ActiveQueryResult(
                todoList = listOf(
                    Todo(id = "d2f7760d-8f03-4cb3-9176-06311cb89993", title = "Buy a unicorn", completed = false)
                ),
                todoCount = 1
            ),
            result,
            "Query result"
        )
    }

    @Test
    fun `completed query`() {
        val query = CompletedQuery

        val result = messageHandler.handle(query)

        assertEquals(
            CompletedQueryResult(
                todoList = listOf(
                    Todo(id = "119e6785-8ffc-42e0-8df6-dbc64881f2b7", title = "Taste JavaScript", completed = true)
                ),
                todoCount = 1
            ),
            result,
            "Query result"
        )
    }

    private class TestingTodoRepository : TodoRepository {
        var todoList = listOf(
            Todo(id = "119e6785-8ffc-42e0-8df6-dbc64881f2b7", title = "Taste JavaScript", completed = true),
            Todo(id = "d2f7760d-8f03-4cb3-9176-06311cb89993", title = "Buy a unicorn", completed = false)
        )

        override fun load(): List<Todo> {
            return todoList
        }

        override fun store(todoList: List<Todo>) {
            this.todoList = todoList
        }
    }
}
