package com.project.kopring.domain.todo.presentation

import com.project.kopring.domain.todo.domain.repository.TodoRepository
import com.project.kopring.domain.todo.presentation.data.dto.TodoDto
import com.project.kopring.domain.todo.presentation.data.request.TodoRequest
import com.project.kopring.domain.todo.service.TodoService
import com.project.kopring.domain.todo.utils.TodoConverter
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("todo")
class TodoController(
    private val todoService: TodoService,
    private val todoConverter: TodoConverter, private val todoRepository: TodoRepository
) {

    @PostMapping("/create")
    fun write(@RequestBody request: TodoDto): ResponseEntity<Void> =
        request.let { todoService.writeTodo(it) }
            .let { ResponseEntity.status(HttpStatus.CREATED).build() }

    @PutMapping("{todoId}")
    fun update(@PathVariable todoId: Long, @RequestBody request: TodoRequest): ResponseEntity<Void> =
        todoConverter.toDto(todoId, request)
            .let { todoService.updateTodo(it) }
            .let { ResponseEntity.status(HttpStatus.OK).build() }

    @DeleteMapping("{todoId}")
    fun delete(@PathVariable todoId: Long): ResponseEntity<Void> =
        todoConverter.toDto(todoId)
            .let { todoService.deleteTodo(it) }
            .let { ResponseEntity.status(HttpStatus.OK).build() }

    @GetMapping
    fun get() = todoService.getTodo().let { ResponseEntity.ok(it) }
}