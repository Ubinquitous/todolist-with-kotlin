package ubinquitous.todolist.hooks

import js.core.Void
import js.core.jso
import js.promise.Promise
import tanstack.query.core.QueryKey
import tanstack.react.query.useMutation
import tanstack.react.query.useQueryClient
import ubinquitous.todolist.entities.Todo
import web.http.fetchAsync

typealias DeleteTodo = (Todo) -> Unit

fun useDeleteTodo(): DeleteTodo {
    val client = useQueryClient()
    return useMutation<Unit, Error, Todo, QueryKey>(
        mutationFn = { todo -> deleteTodo(todo) },
        options = jso {
            onSuccess = { _, _, _ -> client.invalidateQueries<Void>(ubinquitous.todolist.TODOLIST_QUERY_KEY) }
        }
    ).mutate.unsafeCast<DeleteTodo>()
}

private fun deleteTodo(todo: Todo): Promise<Unit> =
    fetchAsync(
        input = "http://localhost:8080/todo/${todo.id}",
        init = jso { method = "DELETE" }
    ).then {}
