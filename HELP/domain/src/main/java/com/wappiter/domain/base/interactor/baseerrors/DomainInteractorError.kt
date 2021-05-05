package com.wappiter.domain.base.interactor.baseerrors

import com.wappiter.domain.base.globalexception.DomainException

class DomainInteractorError(val domainException: DomainException) : BaseInteractorError(DOMAIN_EXCEPTION)