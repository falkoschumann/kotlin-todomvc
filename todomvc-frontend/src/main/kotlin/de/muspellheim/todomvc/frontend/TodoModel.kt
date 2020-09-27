/*
 * TodoMVC
 * Copyright (c) 2020 Falko Schumann <falko.schumann@muspellheim.de>
 */

package de.muspellheim.todomvc.frontend

import de.muspellheim.todomvc.contract.data.Todo
import de.muspellheim.todomvc.contract.messages.DestroyCommand
import de.muspellheim.todomvc.contract.messages.EditCommand
import de.muspellheim.todomvc.contract.messages.ToggleCommand

data class TodoModel(
    val todo: Todo,
    val onToggleCommand: (command: ToggleCommand) -> Unit,
    val onEditCommand: (command: EditCommand) -> Unit,
    val onDestroyCommand: (command: DestroyCommand) -> Unit,
)
