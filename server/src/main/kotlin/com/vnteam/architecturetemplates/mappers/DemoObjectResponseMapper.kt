package com.vnteam.architecturetemplates.mappers

import com.vnteam.architecturetemplates.domain.mappers.BaseMapper
import com.vnteam.architecturetemplates.domain.models.DemoObject
import com.vnteam.architecturetemplates.responses.DemoObjectResponse

interface DemoObjectResponseMapper : BaseMapper<DemoObject, DemoObjectResponse>