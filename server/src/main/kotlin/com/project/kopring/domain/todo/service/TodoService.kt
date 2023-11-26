package com.project.kopring.domain.todo.service

import com.project.kopring.domain.todo.presentation.data.dto.TodoDto

interface TodoService {

    fun writeTodo(todoDto: TodoDto)
    fun updateTodo(todoDto: TodoDto)
    fun deleteTodo(todoDto: TodoDto)
    fun getTodo(): List<TodoDto>
}