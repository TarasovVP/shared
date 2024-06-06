package com.vnteam.architecturetemplates

import com.vnteam.architecturetemplates.di.appModule
import io.ktor.http.HttpHeaders
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.plugins.cors.routing.CORS
import io.ktor.server.routing.routing
import kotlinx.serialization.json.Json
import io.ktor.server.plugins.contentnegotiation.*
import org.koin.ktor.plugin.Koin
import org.koin.ktor.ext.get
import io.ktor.serialization.kotlinx.json.*

fun main() {
    embeddedServer(
        Netty,
        host = "0.0.0.0",
        port = 8080,
        module = Application::appModule
    ).start(wait = true)
}

fun Application.appModule() {
    install(CORS) {
        anyHost()
        allowHeader(HttpHeaders.ContentType)
        allowHeader(HttpHeaders.AccessControlAllowOrigin)
    }
    install(Koin) {
        modules(appModule, serverModule)
    }
    val jsonInstance = get<Json>()
    install(ContentNegotiation) {
        json(jsonInstance)
    }
    routing {
        insertForksToDB()
        getForksFromDB()
        getForkById()
        deleteForkById()
    }
}