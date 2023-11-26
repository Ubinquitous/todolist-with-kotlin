package com.project.kopring.domain.todo.utils

import com.project.kopring.domain.todo.domain.Todo
import com.project.kopring.domain.todo.presentation.data.dto.TodoDto
import com.project.kopring.domain.todo.presentation.data.request.TodoRequest

interface TodoConverter {

    fun toDto(todo: Todo): TodoDto
    fun toDto(id: Long): TodoDto
    fun toDto(id: Long, request: TodoRequest): TodoDto
    fun toEntity(dto: TodoDto): Todo
}