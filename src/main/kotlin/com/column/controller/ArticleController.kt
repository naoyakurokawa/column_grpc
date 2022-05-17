package com.column.controller

import com.column.service.ArticleService
import org.springframework.web.bind.annotation.*
import com.column.proto.ArticleServiceGrpc
import com.column.proto.ArticleServiceGrpcKt
import com.column.proto.ArticleProvider
import com.column.proto.ArticleProvider.GetArticlesResponse
import org.lognet.springboot.grpc.GRpcService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import io.grpc.stub.StreamObserver
import com.google.protobuf.Empty

@GRpcService
class ArticleController(private val articleService: ArticleService): ArticleServiceGrpcKt.ArticleServiceCoroutineImplBase(){
    override suspend fun getArticles(request: Empty): ArticleProvider.GetArticlesResponse  {
        val article: List<com.column.entity.Article> = articleService.findAll()
        val grpcArticles: List<ArticleProvider.Article> =
            article.map {
                toGrpcUser(it)
            }

        val response = ArticleProvider.GetArticlesResponse.newBuilder()
            .addAllArticles(grpcArticles)
            .build()

        return response
    }

    fun toGrpcUser(article: com.column.entity.Article) : ArticleProvider.Article {
        val articleBuilder = ArticleProvider.Article.newBuilder()
            .setId(article.id)
            .setTitle(article.title)
            .setDetail(article.detail)
//            .setCreated(article.created)
//            .setUpdated(article.updated)
        return articleBuilder.build()
    }
}