/*
 * TodoMVC
 * Copyright (c) 2020 Falko Schumann
 */

package de.muspellheim.todomvc.adpater

import de.muspellheim.todomvc.domain.Todo
import de.muspellheim.todomvc.domain.TodoList
import de.muspellheim.todomvc.domain.Todos
import org.apache.commons.csv.CSVFormat
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardOpenOption

class CsvTodosRepository(private val file: Path) : Todos {

    private val csvFormat = CSVFormat.RFC4180.withHeader("Id", "Text", "Is completed")

    override fun all(): List<Todo> {
        Files.newBufferedReader(file, StandardCharsets.UTF_8).use { reader ->
            val parser = csvFormat.withFirstRecordAsHeader().parse(reader)
            return parser
                .map { Todo(it["Id"], it["Text"], it["Is completed"]!!.toBoolean()) }
                .toList()
        }
    }

    override fun active(): List<Todo> {
        return all().filter { it.isCompleted.not() }
    }

    override fun completed(): List<Todo> {
        return all().filter { it.isCompleted }
    }

    override fun add(todo: Todo) {
        TODO()
    }

    override fun update(todo: Todo) {
        TODO()
    }

    override fun update(todos: List<Todo>) {
        TODO()
    }

    override fun remove(todo: Todo) {
        TODO()
    }

    override fun remove(todos: List<Todo>) {
        TODO()
    }

    override fun load(): TodoList {
        Files.newBufferedReader(file, StandardCharsets.UTF_8).use { reader ->
            val parser = csvFormat.withFirstRecordAsHeader().parse(reader)
            val list = parser
                .map { Todo(it["Text"], it["Is completed"]!!.toBoolean()) }
                .toList()
            return TodoList(list)
        }
    }

    override fun store(todoList: TodoList) {
        Files.newBufferedWriter(
            file,
            StandardCharsets.UTF_8,
            StandardOpenOption.CREATE,
            StandardOpenOption.TRUNCATE_EXISTING
        ).use { writer ->
            csvFormat.print(writer).use { printer ->
                todoList.allItems().forEach {
                    printer.printRecord(it.text, it.isCompleted)
                }
            }
        }
    }
}
