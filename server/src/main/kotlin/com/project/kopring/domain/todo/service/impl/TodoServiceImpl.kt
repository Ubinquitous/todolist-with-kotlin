package com.project.kopring.domain.todo.service.impl

import com.project.kopring.domain.todo.domain.repository.TodoRepository
import com.project.kopring.domain.todo.presentation.data.dto.TodoDto
import com.project.kopring.domain.todo.service.TodoService
import com.project.kopring.domain.todo.utils.TodoConverter
import com.project.kopring.domain.todo.utils.TodoValidator
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TodoServiceImpl(
    private val todoRepository: TodoRepository,
    private val todoConverter: TodoConverter,
    private val todoValidator: TodoValidator,
) : TodoService {

    @Transactional(rollbackFor = [Exception::class])
    override fun writeTodo(todoDto: TodoDto) {
        todoRepository.save(todoConverter.toEntity(todoDto))
    }

    @Transactional(rollbackFor = [Exception::class])
    override fun updateTodo(todoDto: TodoDto) {
        todoValidator.validate(todoDto)
            .updateComment(todoDto.content)
    }

    @Transactional(rollbackFor = [Exception::class])
    override fun deleteTodo(todoDto: TodoDto) {
        todoValidator.validate(todoDto)
            .let { todoRepository.delete(it) }
    }

    override fun getTodo() = todoRepository.findAll()
            .map { todoConverter.toDto(it) }
            .let { it }
}