package com.project.kopring.domain.todo.domain

import javax.persistence.*

@Entity
class Todo(
    @Id @GeneratedValue
    @Column(name = "todo_id")
    val id: Long,
    var content: String,
) {
    fun updateComment(content: String) {
        this.content = content
    }
}