package ubinquitous.todolist

import react.FC
import react.Props
import react.create
import react.dom.client.createRoot
import tanstack.query.core.QueryClient
import tanstack.react.query.QueryClientProvider
import ubinquitous.todolist.components.Header
import ubinquitous.todolist.components.LoadingIndicator
import ubinquitous.todolist.components.TodoInfo
import web.dom.document
import web.html.HTML.div

fun main() {
    val root = document.createElement(div)
    document.body.appendChild(root)

    createRoot(root)
        .render(App.create())
}

private val QueryClient = QueryClient()

private val App = FC<Props> {
    QueryClientProvider {
        client = QueryClient

        Header()
        LoadingIndicator()
        TodoInfo()
    }
}
