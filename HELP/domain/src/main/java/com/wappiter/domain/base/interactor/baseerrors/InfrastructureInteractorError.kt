package com.wappiter.domain.base.interactor.baseerrors

import com.wappiter.domain.base.globalexception.InfrastructureException


class InfrastructureInteractorError(infrastructureException: InfrastructureException) : BaseInteractorError(INFRASTRUCTURE_EXCEPTION)