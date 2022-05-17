package com.column.controller

import com.column.entity.Article
import com.column.service.ArticleService
import org.springframework.web.bind.annotation.*
import java.util.*
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/article")
class ArticleController (private val articleService: ArticleService){
    @GetMapping("/list")
    fun findAll(): List<Article> {
        return articleService.findAll()
    }
}