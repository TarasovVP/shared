package com.vnteam.architecturetemplates.data.mapperimpls

import com.vnteam.architecturetemplates.ForkWithOwner
import com.vnteam.architecturetemplates.domain.mappers.ForkDBMapper
import com.vnteam.architecturetemplates.domain.models.Fork
import com.vnteam.architecturetemplates.domain.models.Owner

class ForkDBMapperImpl : ForkDBMapper {

    override fun mapToImplModel(from: Fork): ForkWithOwner {
        return ForkWithOwner(id = 0,
            forkId = from.forkId.orEmpty(),
            name = from.name,
            ownerId = from.owner?.ownerId,
            login = from.owner?.login,
            avatarUrl = from.owner?.avatarUrl,
            htmlUrl = from.htmlUrl,
            description = from.description,
            url = from.owner?.url)
    }

    override fun mapFromImplModel(to: ForkWithOwner): Fork {
        return Fork(forkId = to.forkId,
        name = to.name,
        owner = Owner(ownerId = to.ownerId,
            login = to.login,
            avatarUrl = to.avatarUrl,
            url = to.url),
        htmlUrl = to.htmlUrl,
        description = to.description)
    }

    override fun mapToImplModelList(fromList: List<Fork>): List<ForkWithOwner> {
        return fromList.map { mapToImplModel(it) }
    }

    override fun mapFromImplModelList(toList: List<ForkWithOwner>): List<Fork> {
        return toList.map { mapFromImplModel(it) }
    }
}