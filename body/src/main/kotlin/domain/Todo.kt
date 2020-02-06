package de.muspellheim.todomvc.domain

import java.util.UUID

data class Todo(
    val text: String,
    val isCompleted: Boolean = false,
    val id: String = UUID.randomUUID().toString()
)
