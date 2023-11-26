package ubinquitous.todolist.hooks

import js.core.Void
import js.core.jso
import js.promise.Promise
import tanstack.query.core.QueryKey
import tanstack.react.query.useMutation
import tanstack.react.query.useQueryClient
import ubinquitous.todolist.entities.Todo
import web.http.fetchAsync

typealias UpdateTodo = (Todo) -> Unit

fun useUpdateTodo(): UpdateTodo {
    val client = useQueryClient()
    return useMutation<Todo, Error, Todo, QueryKey>(
        mutationFn = { todo -> updateTodo(todo) },
        options = jso {
            onSuccess = { _, _, _ -> client.invalidateQueries<Void>(ubinquitous.todolist.TODOLIST_QUERY_KEY) }
        }
    ).mutate.unsafeCast<UpdateTodo>()
}

private fun updateTodo(todo: Todo): Promise<Todo> =
    fetchAsync(
        input = "http://localhost:8080/todo/${todo.id}",
        init = jso {
            method = "PUT"
            body = JSON.stringify(todo)
        }
    ).then { it.json() }.then { it.unsafeCast<Todo>() }
