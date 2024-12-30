package api

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.utils.io.charsets.Charsets
import kotlinx.serialization.json.Json
import model.Todo

private val json = Json {
    encodeDefaults = true
    ignoreUnknownKeys = true
}

class MainApi {
    private val client = HttpClient()

    suspend fun getTodos(): List<Todo> {
        val response = client.get("https://jsonplaceholder.typicode.com/todos/")
        val todoList = json.decodeFromString<List<Todo>>(response.bodyAsText(Charsets.UTF_8))
        return todoList
    }
}