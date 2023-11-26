package com.project.kopring.domain.todo.utils.impl

import com.project.kopring.domain.todo.domain.Todo
import com.project.kopring.domain.todo.presentation.data.dto.TodoDto
import com.project.kopring.domain.todo.presentation.data.request.TodoRequest
import com.project.kopring.domain.todo.utils.TodoConverter
import org.springframework.stereotype.Component

@Component
class TodoConverterImpl : TodoConverter {
    override fun toDto(todo: Todo): TodoDto =
        TodoDto(todo.id, todo.content)

    override fun toDto(id: Long): TodoDto =
        TodoDto(id, "")

    override fun toDto(id: Long, request: TodoRequest): TodoDto = TodoDto(id, request.content)

    override fun toEntity(dto: TodoDto): Todo =
        Todo(dto.id, dto.content)
}