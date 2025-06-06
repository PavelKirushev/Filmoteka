package com.example.film.data

import com.example.film.data.mappers.toFilmDetails
import com.example.film.domain.models.FilmDetails
import com.example.film.data.network.FilmApi
import com.example.film.domain.FilmRepository
import com.example.film.data.network.models.FilmVideos
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope


class FilmRepositoryImpl(
    private val filmApi: FilmApi
) : FilmRepository {
//    override suspend fun getFilm(filmId: Int): FilmDetails {
//        return filmApi.getFilmById(filmId).toFilmDetails(filmApi.getMovieById(filmId))
//    }
//    override suspend fun getFilm(filmId: Int): FilmDetails {
//        return try {
//            val film = filmApi.getFilmById(filmId)
//            val videos = filmApi.getMovieById(filmId)
//            film.toFilmDetails(videos)
//        } catch (e: Exception) {
//            throw Exception("Ошибка загрузки фильма", e)
//        }
//    }
    override suspend fun getFilm(filmId: Int): FilmDetails = coroutineScope {
        val filmDeferred = async { filmApi.getFilmById(filmId) }
        val videosDeferred = async { filmApi.getMovieById(filmId) }
        filmDeferred.await().toFilmDetails(videosDeferred.await())
    }

}