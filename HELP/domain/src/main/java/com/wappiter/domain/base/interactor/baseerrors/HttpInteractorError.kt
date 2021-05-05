package com.wappiter.domain.base.interactor.baseerrors

class HttpInteractorError(var httpCode: Int) : BaseInteractorError(API_EXCEPTION)