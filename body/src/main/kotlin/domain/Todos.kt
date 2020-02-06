package de.muspellheim.todomvc.domain

interface Todos {
    fun create(todo: Todo)

    fun all(): List<Todo>
    fun active(): List<Todo>
    fun completed(): List<Todo>

    fun update(todo: Todo)
    fun updateCompleted(completed: Boolean)

    fun delete(id: String)
    fun deleteCompleted()
}
