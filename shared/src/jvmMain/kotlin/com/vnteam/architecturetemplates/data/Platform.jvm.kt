package com.vnteam.architecturetemplates.data

import java.util.UUID

actual fun generateUUID(): String = UUID.randomUUID().toString()

actual fun baseUrl(): String = LOCAL_HOST_URL