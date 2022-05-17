package com.column.grpc

import com.column.service.ArticleService
import com.column.proto.ArticleServiceGrpcKt
import com.column.proto.ArticleProvider
import org.lognet.springboot.grpc.GRpcService
import com.google.protobuf.Empty
import java.time.LocalDateTime
import java.time.ZoneId

@GRpcService
class ArticleGrpcService(private val articleService: ArticleService): ArticleServiceGrpcKt.ArticleServiceCoroutineImplBase(){
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
            .setCreated(toGrpcTimestamp(article.created))
            .setUpdated(toGrpcTimestamp(article.updated))
        return articleBuilder.build()
    }

    fun toGrpcTimestamp(date: LocalDateTime) : com.google.protobuf.Timestamp {
        val dateInstant = date.atZone(ZoneId.systemDefault()).toInstant()
        return com.google.protobuf.Timestamp.newBuilder()
            .setSeconds(dateInstant.epochSecond)
            .setNanos(dateInstant.nano).build()
    }
}