package com.column.entity

import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "articles")
data class Article (
    @Id
    @GeneratedValue
    val id: Long,

    @Column(name = "title", nullable = true)
    val title: String,

    @Column(name = "detail", nullable = true)
    val detail: String,

    @Column(name = "created", nullable = true)
    val created: LocalDate,

    @Column(name = "updated")
    val updated: LocalDate,
)