/*
 * TodoMVC
 * Copyright (c) 2020 Falko Schumann
 */

package de.muspellheim.todomvc.adpater

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import java.nio.file.Paths

class CsvTodosRepositoryTests {

    @Nested
    inner class Query {

        val todos = CsvTodosRepository(Paths.get("src/test/resources/todos.csv"))

        @Test
        fun `all todos`() {
            val all = todos.all()

            assertAll(
                { assertEquals(2, all.size, "2 todos found") },
                {
                    assertAll(
                        "first todo",
                        { assertEquals(all[0].id, "1", "id") },
                        { assertEquals(all[0].text, "Foo", "text") },
                        { assertTrue(all[0].isActive, "is active") }
                    )
                },
                {
                    assertAll(
                        "second todo",
                        { assertEquals(all[1].id, "2", "id") },
                        { assertEquals(all[1].text, "Bar", "text") },
                        { assertTrue(all[1].isCompleted, "is completed") }
                    )
                }
            )
        }

        @Test
        fun `active todos`() {
            val active = todos.active()

            assertAll(
                { assertEquals(1, active.size, "1 todo found") },
                {
                    assertAll(
                        "first todo",
                        { assertEquals(active[0].id, "1", "id") },
                        { assertEquals(active[0].text, "Foo", "text") },
                        { assertTrue(active[0].isActive, "is active") }
                    )
                }
            )
        }

        @Test
        fun `completed todos`() {
            val completed = todos.completed()

            assertAll(
                { assertEquals(1, completed.size, "1 todos found") },
                {
                    assertAll(
                        "second todo",
                        { assertEquals(completed[0].id, "2", "id") },
                        { assertEquals(completed[0].text, "Bar", "text") },
                        { assertTrue(completed[0].isCompleted, "is completed") }
                    )
                }
            )
        }
    }
}
