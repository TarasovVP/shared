package com.vnteam.architecturetemplates

import com.vnteam.architecturetemplates.domain.models.Fork
import com.vnteam.architecturetemplates.domain.repositories.DBRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull

class ForksPresenterImpl(
    private val dbRepository: DBRepository
) : ForksPresenter {

    override suspend fun insertForksToDB(forks: List<Fork>?) {
        return dbRepository.insertForksToDB(forks.orEmpty()).first()
    }

    override suspend fun getForksFromDB(): List<Fork>? {
        return dbRepository.getForksFromDB().firstOrNull()
    }

    override suspend fun getForkById(forkId: Long?): Fork? {
        return dbRepository.getForkById(forkId ?: 0).firstOrNull()
    }

    override suspend fun deleteForkById(forkId: Long) {
        return  dbRepository.deleteForkById(forkId).first()
    }

}