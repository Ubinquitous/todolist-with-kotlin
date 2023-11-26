package ubinquitous.todolist.components

import emotion.react.css
import js.core.jso
import react.FC
import react.dom.html.ReactHTML
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.input
import react.dom.html.ReactHTML.table
import react.dom.html.ReactHTML.tbody
import react.dom.html.ReactHTML.td
import react.dom.html.ReactHTML.th
import react.dom.html.ReactHTML.thead
import react.dom.html.ReactHTML.tr
import tanstack.react.table.renderCell
import tanstack.react.table.renderHeader
import tanstack.react.table.useReactTable
import tanstack.table.core.ColumnDef
import tanstack.table.core.StringOrTemplateHeader
import tanstack.table.core.getCoreRowModel
import ubinquitous.todolist.Colors
import ubinquitous.todolist.entities.Todo
import ubinquitous.todolist.hooks.useCreateTodo
import ubinquitous.todolist.hooks.useDeleteTodo
import ubinquitous.todolist.hooks.useTodoList
import ubinquitous.todolist.hooks.useUpdateTodo
import web.cssom.*
import web.cssom.Auto.Companion.auto
import web.cssom.LineStyle.Companion.solid
import web.cssom.None.Companion.none
import kotlin.random.Random.Default.nextInt

val TodoTable = FC {
    val todoList = useTodoList()
    val createTodo = useCreateTodo()
    val deleteTodo = useDeleteTodo()

    val table = useReactTable<Todo>(
        options = jso {
            data = todoList
            columns = arrayOf<ColumnDef<Todo, String>>(
                jso {
                    id = "id"
                    header = StringOrTemplateHeader("투두 아이디")
                    accessorFn = { row, _ -> row.id.toString() }
                },
                jso {
                    id = "content"
                    header = StringOrTemplateHeader("내용 ~")
                    accessorFn = { row, _ -> row.content }
                },
            )
            getCoreRowModel = getCoreRowModel()
        }
    )

    div {
        css {
            display = Display.flex
            justifyContent = JustifyContent.center
            alignItems = AlignItems.center
            flexDirection = FlexDirection.column
            gap = Gap.normal
        }
        div {
            button {
                onClick = { createTodo(jso { id = 1; content = "히히 뭔가 이상해도 투두 리스트 ㅎㅎ" }) }
                css {
                    borderRadius = 4.px
                    border = none
                    backgroundColor = Color("#73AEE3")
                    color = Color("white")
                    padding = 8.px
                }
                +"생성"
            }
        }

        table {
            css {
                width = 400.px
                borderSpacing = 0.px
                borderCollapse = BorderCollapse.collapse
                whiteSpace = WhiteSpace.nowrap
                border = Border(2.px, solid, Colors.Stroke.Gray)
                margin = auto
            }

            thead {
                css {
                    color = Colors.Text.Gray
                    fontSize = 18.px
                    backgroundColor = Colors.Background.Gray
                }

                for (headerGroup in table.getHeaderGroups()) {
                    tr {
                        for (header in headerGroup.headers) {
                            th {
                                css {
                                    fontWeight = FontWeight.normal
                                    padding = Padding(4.px, 12.px)
                                    borderRight = Border(1.px, solid, Colors.Stroke.Gray)
                                    borderBottom = Border(1.px, solid, Colors.Stroke.Gray)

                                    lastChild {
                                        borderRight = none
                                    }
                                }

                                +renderHeader(header)
                            }
                        }
                    }
                }
            }

            tbody {
                css {
                    color = Colors.Text.Black
                    backgroundColor = Colors.Background.White
                    textAlign = TextAlign.start
                }

                for (row in table.getRowModel().rows) {
                    tr {
                        css {
                            fontSize = 16.px
                            cursor = Cursor.pointer
                            borderBottom = Border(1.px, solid, Colors.Stroke.LightGray)
                            hover {
                                backgroundColor = Colors.Background.Gray
                            }
                        }

                        for (cell in row.getVisibleCells()) {
                            td {
                                css {
                                    padding = Padding(10.px, 12.px)
                                }

                                +renderCell(cell)
                            }
                        }

                        button {
                            css {
                                borderRadius = 4.px
                                border = none
                                backgroundColor = Color("#73AEE3")
                                color = Color("white")
                                padding = 8.px
                            }
                            +"삭제"
                            onClick = { deleteTodo(row.original) }
                        }
                    }
                }
            }
        }
    }
}