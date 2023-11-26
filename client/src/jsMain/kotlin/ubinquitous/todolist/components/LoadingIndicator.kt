package ubinquitous.todolist.components

import react.FC
import ubinquitous.todolist.hooks.useTodoList

val LoadingIndicator = FC {
    val todoList = useTodoList()

    if (todoList.isEmpty())
        +"Loading..."
}
