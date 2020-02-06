package de.muspellheim.todomvc.domain

class TodosService(private val todos: Todos) {

    fun getTodos(filter: Filter): List<Todo> {
        return when (filter) {
            Filter.All -> todos.all()
            Filter.Active -> todos.active()
            Filter.Completed -> todos.completed()
        }
    }

    fun addTodo(text: String) {
        todos.create(Todo(text))
    }

    fun changeTodo(todo: Todo, newText: String) {
        val newTodo = todo.copy(text = newText)
        todos.update(newTodo)
    }

    fun toggleCompleted(todo: Todo) {
        todos.update(todo)
    }

    fun removeTodo(todo: Todo) {
        todos.delete(todo.id)
    }

    fun toggleAllCompleted(completed: Boolean) {
        todos.updateCompleted(completed)
    }

    fun clearCompleted() {
        todos.deleteCompleted()
    }

    enum class Filter { All, Active, Completed }
}
