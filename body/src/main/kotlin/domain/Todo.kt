/*
 * TodoMVC
 * Copyright (c) 2020 Falko Schumann
 */

package de.muspellheim.todomvc.domain

data class Todo(
    val text: String,
    val isCompleted: Boolean = false
)
