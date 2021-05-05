package com.wappiter.app.infrastucture.domain.invoker

interface PriorizableInteractor {
    val priority: Int
    val description: String
}