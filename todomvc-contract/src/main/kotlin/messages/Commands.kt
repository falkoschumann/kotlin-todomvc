/*
 * TodoMVC
 * Copyright (c) 2020 Falko Schumann <falko.schumann@muspellheim.de>
 */

package de.muspellheim.todomvc.contract.messages

data class NewTodoCommand(val title: String)

data class ToggleAllCommand(val completed: Boolean)

data class ToggleCommand(val id: String)

data class DestroyCommand(val id: String)

data class EditCommand(val id: String, val title: String)

object ClearCompletedCommand
