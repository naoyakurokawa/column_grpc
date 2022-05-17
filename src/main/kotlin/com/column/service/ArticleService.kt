package com.column.service

import com.column.entity.Article
import com.column.repository.ArticleRepository
import org.springframework.stereotype.Service

@Service
class ArticleService(private val articleRepository: ArticleRepository) {
    fun findAll(): List<Article> = articleRepository.findAll()
}