package com.wappiter.app.presentation.base.invoker;

import java.util.concurrent.Future;

public interface InteractorInvoker {

    <T> Future<T> execute(InteractorExecution<T> interactor);
}
