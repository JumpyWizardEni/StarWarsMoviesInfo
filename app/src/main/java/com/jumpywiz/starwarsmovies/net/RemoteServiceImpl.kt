package com.jumpywiz.starwarsmovies.net

import com.jumpywiz.starwarsmovies.model.CharacterRequest
import com.jumpywiz.starwarsmovies.model.MovieListRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.lang.Exception
import com.jumpywiz.starwarsmovies.net.Result

class RemoteServiceImpl(private val retrofitService: RetrofitService): IRemoteService {
    override suspend fun getMoviesList(): Result<MovieListRequest> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                val result = retrofitService.getMoviesList()
                if (result.isSuccessful) {
                    val movieList = result.body()
                    Result.Success(movieList)
                } else {
                    Result.Success(null)
                }
            } catch (exception: Exception) {
                Result.Error(exception)
            }
        }

    override suspend fun getCharacterInfo(id: Int): Result<CharacterRequest> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                val result = retrofitService.getCharacterInfo(id)
                if (result.isSuccessful) {
                    val charInfo = result.body()
                    Result.Success(charInfo)
                } else {
                    Result.Success(null)
                }
            } catch (exception: Exception) {
                Result.Error(exception)
            }
        }
}