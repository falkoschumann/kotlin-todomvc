package de.muspellheim.todomvc.adpater

import de.muspellheim.todomvc.domain.Todo
import de.muspellheim.todomvc.domain.Todos
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Path
import org.apache.commons.csv.CSVFormat

class CsvTodosRepository(private val file: Path) : Todos {

    private val csvFormat = CSVFormat.RFC4180.withHeader("Id, Text, Is completed")

    override fun create(todo: Todo) {
        csvFormat.print(file, StandardCharsets.UTF_8).use { printer ->
            printer.printRecord(todo.id, todo.text, todo.isCompleted)
        }
    }

    override fun all(): List<Todo> {
        Files.newBufferedReader(file).use { reader ->
            val parser = csvFormat.parse(reader)
            return parser
                .map { Todo(it["Text"], it["Is completed"]!!.toBoolean(), it["Id"]) }
                .toList()
        }
    }

    override fun active(): List<Todo> {
        return all()
            .filter { it.isCompleted.not() }
    }

    override fun completed(): List<Todo> {
        return all()
            .filter { it.isCompleted }
    }

    override fun update(todo: Todo) {
        TODO("not implemented")
    }

    override fun updateCompleted(completed: Boolean) {
        TODO("not implemented")
    }

    override fun delete(id: String) {
        TODO("not implemented")
    }

    override fun deleteCompleted() {
        TODO("not implemented")
    }
}
