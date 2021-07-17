package com.jumpywiz.starwarsmovies.net

import com.jumpywiz.starwarsmovies.model.CharacterRequest
import com.jumpywiz.starwarsmovies.model.MovieListRequest
import com.jumpywiz.starwarsmovies.model.PlanetRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class RemoteServiceImpl @Inject constructor(private val retrofitService: RetrofitService): IRemoteService {
    override suspend fun getMoviesList(): Result<MovieListRequest> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                val result = retrofitService.getMoviesList()
                if (result.isSuccessful) {
                    val movieList = result.body()
                    Result.Success(movieList)
                } else {
                    Result.Error(Exception("no data"))
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
                    Result.Error(Exception("no data"))
                }
            } catch (exception: Exception) {
                Result.Error(exception)
            }
        }

    override suspend fun getPlanetInfo(id: Int): Result<PlanetRequest> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                val result = retrofitService.getPlanetInfo(id)
                if (result.isSuccessful) {
                    val charInfo = result.body()
                    Result.Success(charInfo)
                } else {
                    Result.Error(Exception("no data"))
                }
            } catch (exception: Exception) {
                Result.Error(exception)
            }
        }

}