package de.muspellheim.todomvc.adpater

import de.muspellheim.todomvc.domain.Todo
import de.muspellheim.todomvc.domain.TodoList
import de.muspellheim.todomvc.domain.Todos
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardOpenOption
import org.apache.commons.csv.CSVFormat

class CsvTodosRepository(private val file: Path) : Todos {

    private val csvFormat = CSVFormat.RFC4180.withHeader("Id, Text, Is completed")

    override fun load(): TodoList {
        Files.newBufferedReader(file, StandardCharsets.UTF_8).use { reader ->
            val parser = csvFormat.parse(reader)
            val list = parser
                .map { Todo(it["Text"], it["Is completed"]!!.toBoolean(), it["Id"]) }
                .toList()
            return TodoList(list)
        }
    }

    override fun store(todoList: TodoList) {
        Files.newBufferedWriter(file, StandardCharsets.UTF_8, StandardOpenOption.CREATE).use { writer ->
            csvFormat.print(writer).use { printer ->
                todoList.allItems().forEach {
                    printer.printRecord(it.id, it.text, it.isCompleted)
                }
            }
        }
    }
}
