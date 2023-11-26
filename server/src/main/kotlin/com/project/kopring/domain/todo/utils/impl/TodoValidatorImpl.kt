package com.project.kopring.domain.todo.utils.impl

import com.project.kopring.domain.todo.domain.Todo
import com.project.kopring.domain.todo.domain.repository.TodoRepository
import com.project.kopring.domain.todo.presentation.data.dto.TodoDto
import com.project.kopring.domain.todo.utils.TodoValidator
import org.springframework.stereotype.Component

@Component
class TodoValidatorImpl(private val todoRepository: TodoRepository) : TodoValidator {
    override fun validate(todoDto: TodoDto): Todo =
        todoRepository.findById(todoDto.id).orElseThrow()
}