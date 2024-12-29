package com.example.goncalonews.app.domain.use_case

import com.example.goncalonews.app.domain.model.Noticia
import com.example.goncalonews.app.domain.model.NoticiaDetail
import com.example.goncalonews.app.domain.repository.NoticiaRepository

class GetNoticiasUseCase(private val repository: NoticiaRepository) {
    suspend operator fun invoke(): List<Noticia> {
        return repository.getNoticias()
    }
}

class GetNoticiaDetailUseCase(private val repository: NoticiaRepository) {
    suspend operator fun invoke(id: String): NoticiaDetail {
        return repository.getNoticiaDetail(id)
    }
}