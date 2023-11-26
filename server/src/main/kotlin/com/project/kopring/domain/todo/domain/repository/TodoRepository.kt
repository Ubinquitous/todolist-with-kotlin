package com.project.kopring.domain.todo.domain.repository

import com.project.kopring.domain.todo.domain.Todo
import org.springframework.data.repository.CrudRepository

interface TodoRepository : CrudRepository<Todo, Long> {
}