package com.vnteam.architecturetemplates.data.repositoryimpl

import com.vnteam.architecturetemplates.data.network.ApiService
import com.vnteam.architecturetemplates.data.network.NetworkResult
import com.vnteam.architecturetemplates.domain.mappers.ForkResponseMapper
import com.vnteam.architecturetemplates.domain.models.Fork
import com.vnteam.architecturetemplates.domain.repositories.ApiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class ApiRepositoryImpl(private val apiService: ApiService, private val forkResponseMapper: ForkResponseMapper) :
    ApiRepository {

    override suspend fun insertForksToApi(forks: List<Fork>?): Flow<Unit> {
        when (val response = apiService.insertForksToApi(forkResponseMapper.mapToImplModelList(forks.orEmpty()))) {
            is NetworkResult.Success -> {
                return flowOf( Unit )
            }
            is NetworkResult.Failure -> {
                println(response.errorMessage)
                throw Exception(response.errorMessage)
            }
        }
    }

    override suspend fun getForksFromApi(): Flow<List<Fork>> {
        when (val response = apiService.getForksFromApi()) {
            is NetworkResult.Success -> {
                return flowOf( response.data?.map { forkResponseMapper.mapFromImplModel(it) }.orEmpty() )
            }
            is NetworkResult.Failure -> {
                println(response.errorMessage)
                throw Exception(response.errorMessage)
            }
        }
    }

    override suspend fun getForkById(forkId: String?): Flow<Fork?> {
        when (val response = apiService.getForkById(forkId.orEmpty())) {
            is NetworkResult.Success -> {
                return flowOf( response.data?.let { forkResponseMapper.mapFromImplModel(it) } )
            }
            is NetworkResult.Failure -> {
                println(response.errorMessage)
                throw Exception(response.errorMessage)
            }
        }
    }

    override suspend fun deleteForkById(forkId: String): Flow<Unit> {
        when (val response = apiService.deleteForkById(forkId)) {
            is NetworkResult.Success -> {
                return flowOf( Unit )
            }
            is NetworkResult.Failure -> {
                println(response.errorMessage)
                throw Exception(response.errorMessage)
            }
        }
    }
}