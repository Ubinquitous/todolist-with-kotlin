package ubinquitous.todolist.entities

external interface Todo {
    var id: Key
    var content: String
}

typealias TodoList = Array<out Todo>
