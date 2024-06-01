package com.vnteam.architecturetemplates.data.database

import app.cash.sqldelight.async.coroutines.awaitAsList
import app.cash.sqldelight.async.coroutines.awaitAsOneOrNull
import com.vnteam.architecturetemplates.ForkWithOwner
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class ForkDaoImpl(private val sharedDatabase: SharedDatabase): ForkDao {
    override suspend fun clearForks() {
        sharedDatabase { database ->
            database.appDatabaseQueries.clearForks()
        }
    }

    override suspend fun insertForkWithOwners(forks: List<ForkWithOwner>, result: (Unit) -> Unit) {
        sharedDatabase { database ->
            database.appDatabaseQueries.transaction {
                forks.forEach { fork ->
                    database.appDatabaseQueries.insertForkWithOwner(
                        id = fork.id,
                        name = fork.name,
                        fullName = fork.fullName,
                        ownerId = fork.ownerId,
                        login = fork.login,
                        avatarUrl = fork.avatarUrl,
                        htmlUrl = fork.htmlUrl,
                        description = fork.description,
                        url = fork.url
                    )
                }
                result(Unit)
            }
        }
    }

    override suspend fun getForkWithOwners(): Flow<List<ForkWithOwner>> = callbackFlow {
        sharedDatabase { database ->
            trySend(database.appDatabaseQueries.getForkWithOwners().awaitAsList()).isSuccess
        }
        awaitClose { }
    }

    override suspend fun getForkById(id: Long): Flow<ForkWithOwner?> = callbackFlow {
        sharedDatabase { database ->
            trySend(database.appDatabaseQueries.getForkWithOwnerById(id).awaitAsOneOrNull()).isSuccess
        }
        awaitClose { }
    }
}