package de.muspellheim.todomvc.contract.messages

sealed class CommandStatus

object Success : CommandStatus()

class Failure(val errorMessage: String) : CommandStatus()

