package ubinquitous.todolist.hooks

import js.core.Void
import js.core.jso
import js.promise.Promise
import tanstack.query.core.QueryKey
import tanstack.react.query.useMutation
import tanstack.react.query.useQueryClient
import ubinquitous.todolist.entities.Todo
import web.http.fetchAsync

typealias CreateTodo = (Todo) -> Unit

fun useCreateTodo(): CreateTodo {
    val client = useQueryClient()
    return useMutation<Todo, Error, Todo, QueryKey>(
        mutationFn = { todo -> createTodo(todo) },
        options = jso {
            onSuccess = { _, _, _ -> client.invalidateQueries<Void>(ubinquitous.todolist.TODOLIST_QUERY_KEY) }
        }
    ).mutate.unsafeCast<CreateTodo>()
}

private fun createTodo(todo: Todo): Promise<Todo> =
    fetchAsync(
        input = "http://localhost:8080/todo/create",
        init = jso {
            method = "POST"
            body = JSON.stringify(todo)
        }
    ).then { it.json() }.then { it.unsafeCast<Todo>() }
