package com.project.kopring.domain.todo.utils

import com.project.kopring.domain.todo.domain.Todo
import com.project.kopring.domain.todo.presentation.data.dto.TodoDto

interface TodoValidator {
    fun validate(todoDto: TodoDto): Todo
}