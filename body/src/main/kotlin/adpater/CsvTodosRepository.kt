/*
 * TodoMVC
 * Copyright (c) 2020 Falko Schumann
 */

package de.muspellheim.todomvc.adpater

import de.muspellheim.todomvc.domain.Todo
import de.muspellheim.todomvc.domain.Todos
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardOpenOption
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVPrinter

class CsvTodosRepository(private val file: Path) : Todos {

    private val csvFormat = CSVFormat.RFC4180.withHeader("Id", "Text", "Status")

    override fun all(): List<Todo> {
        return load()
    }

    private fun load(): List<Todo> {
        Files.newBufferedReader(file, StandardCharsets.UTF_8).use { reader ->
            val parser = csvFormat.withFirstRecordAsHeader().parse(reader)
            return parser
                .map { Todo(it["Id"], it["Text"], it["Status"] == "completed") }
                .toList()
        }
    }

    override fun active(): List<Todo> {
        return all().filter { it.isActive }
    }

    override fun completed(): List<Todo> {
        return all().filter { it.isCompleted }
    }

    override fun add(todo: Todo) {
        Files.newBufferedWriter(file, StandardCharsets.UTF_8, StandardOpenOption.CREATE).use { writer ->
            csvFormat.print(writer).use { printer -> printTodo(printer, todo) }
        }
    }

    override fun update(todo: Todo) {
        update(listOf(todo))
    }

    override fun update(todos: List<Todo>) {
        store(load().map { loaded -> todos.find { updated -> updated.id == loaded.id } ?: loaded })
    }

    override fun remove(todo: Todo) {
        remove(listOf(todo))
    }

    override fun remove(todos: List<Todo>) {
        store(load().filter { loaded -> todos.find { removed -> removed.id == loaded.id } == null })
    }

    private fun store(todos: List<Todo>) {
        Files.newBufferedWriter(
            file,
            StandardCharsets.UTF_8,
            StandardOpenOption.CREATE,
            StandardOpenOption.TRUNCATE_EXISTING
        ).use { writer ->
            csvFormat.print(writer).use { printer ->
                todos.forEach { printTodo(printer, it) }
            }
        }
    }

    private fun printTodo(printer: CSVPrinter, todo: Todo) {
        printer.printRecord(todo.id, todo.text, if (todo.isCompleted) "completed" else "active")
    }
}
