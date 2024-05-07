package com.vnteam.architecturetemplates.data.repositoryimpl

import com.vnteam.architecturetemplates.data.database.ForkDao
import com.vnteam.architecturetemplates.domain.mappers.ForkDBMapper
import com.vnteam.architecturetemplates.domain.models.Fork
import com.vnteam.architecturetemplates.domain.repositories.DBRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOf

class DBRepositoryImpl(private val forkDao: ForkDao, private val forkDBMapper: ForkDBMapper):
    DBRepository {

    override suspend fun insertForksToDB(forks: List<Fork>) {
        forkDao.insertForkWithOwners(forkDBMapper.mapToImplModelList(forks))
    }

    override suspend fun getForksFromDB(): Flow<List<Fork>> = callbackFlow{
        forkDao.getForks { forkWithOwners ->
            trySend(forkDBMapper.mapFromImplModelList(forkWithOwners)).isSuccess
        }
        awaitClose { }
    }

    override suspend fun getForkById(forkId: Long): Flow<Fork?> = callbackFlow {
        forkDao.getForkById(forkId) { forkWithOwner ->
            trySend(forkWithOwner?.let { forkDBMapper.mapFromImplModel(it) }).isSuccess
        }
        awaitClose { }
    }
}