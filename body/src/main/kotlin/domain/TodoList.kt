package de.muspellheim.todomvc.domain

import java.lang.IllegalArgumentException

class TodoList(private var todos: List<Todo>) {

    var filter = Filter.All
    val activeCount = todos.filter { it.isCompleted.not() }.size

    fun allItems(): List<Todo> {
        return todos
    }

    fun filteredItems(): List<Todo> {
        return when (filter) {
            Filter.All -> todos
            Filter.Active -> todos.filter { it.isCompleted.not() }
            Filter.Completed -> todos.filter { it.isCompleted }
        }
    }

    fun addTodo(text: String) {
        val t = text.trim()
        if (t.isEmpty()) {
            throw IllegalArgumentException("text must not be empty")
        }
        todos += Todo(t)
    }

    fun changeTodo(todo: Todo, newText: String) {
        val t = newText.trim()
        if (t.isEmpty()) {
            throw IllegalArgumentException("text must not be empty")
        }
        val newTodo = todo.copy(text = t)
        todos = todos.map { if (it.id == todo.id) newTodo else it }
    }

    fun toggleCompleted(todo: Todo) {
        val newTodo = todo.copy(isCompleted = !todo.isCompleted)
        todos = todos.map { if (it.id == todo.id) newTodo else it }
    }

    fun removeTodo(todo: Todo) {
        todos = todos.filter { it.id != todo.id }
    }

    fun toggleAllCompleted(completed: Boolean) {
        todos = todos.map { it.copy(isCompleted = completed) }
    }

    fun clearCompleted() {
        todos = todos.filter { it.isCompleted.not() }
    }

    enum class Filter { All, Active, Completed }
}
