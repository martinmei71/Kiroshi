package com.wappiter.app.infrastucture.domain.invoker

class UnhandledInteractorException(interactorName: String?,
                                   initiatorException: String?) : RuntimeException(String.format("Your interactor %s does not handle the exception: %s", interactorName, initiatorException))