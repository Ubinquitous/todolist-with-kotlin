package ubinquitous.todolist.hooks

import js.promise.Promise
import tanstack.query.core.QueryKey
import tanstack.react.query.useQuery
import ubinquitous.todolist.TODOLIST_QUERY_KEY
import ubinquitous.todolist.entities.TodoList
import web.http.fetchAsync

fun useTodoList(): TodoList {
    val result = useQuery<TodoList, Error, TodoList, QueryKey>(
        queryKey = TODOLIST_QUERY_KEY,
        queryFn = { getUsers() }
    )
    return result.data ?: emptyArray()
}

private fun getUsers(): Promise<TodoList> =
    fetchAsync("http://localhost:8080/todo")
        .then { it.json() }
        .then { it.unsafeCast<TodoList>() }
