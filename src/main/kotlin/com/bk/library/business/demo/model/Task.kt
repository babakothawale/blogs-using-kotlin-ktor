package com.bk.library.business.demo.model

import kotlinx.serialization.Serializable


enum class Priority { Low, Medium, High, Vital }

@Serializable
data class Task(val name: String, val description: String, val priority: Priority)


//extensions

fun Task.taskAsRow(): String {
    return """
    <tr>
        <td>$name</td><td>$description</td><td>$priority</td>
    </tr>
    """.trimIndent()
}

fun List<Task>.tasksAsTable(): String {
    return joinToString(
        prefix = "<table rules=\"all\">",
        postfix = "</table>",
        separator = "\n",
        transform = {
            it.taskAsRow()
        }
    )
}