package com.wappiter.app.infrastucture.domain.invoker;

import com.wappiter.app.presentation.base.InteractorResult;
import com.wappiter.app.presentation.base.invoker.InteractorExecution;
import com.wappiter.domain.base.interactor.InteractorResponse;
import com.wappiter.domain.base.interactor.baseerrors.InteractorError;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class InteractorExecutionFutureTask<T> extends FutureTask<T> implements PriorizableInteractor {

    private final InteractorExecution<T> mInteractorExecution;
    private final Thread.UncaughtExceptionHandler mUncaughtExceptionHandler;
    private final String mDescription;

    InteractorExecutionFutureTask(InteractorExecution<T> interactorExecution, Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {

        super((Callable) interactorExecution.getInteractor());
        mInteractorExecution = interactorExecution;
        mUncaughtExceptionHandler = uncaughtExceptionHandler;
        mDescription = interactorExecution
                .getInteractor()
                .getClass()
                .toString();
    }

    @Override
    protected void done() {

        super.done();
        try {
            handleResponse((InteractorResponse<T>) get());
        } catch (Exception e) {
            handleException(e);
        }
    }

    private void handleException(Exception e) {

        Throwable causeException = e.getCause();
        unhandledException(causeException != null ? causeException : e);
    }

    private void handleResponse(InteractorResponse<T> response) {

        if (response.hasError()) {
            handleError(response.getError());
        } else {
            handleResult(response.getResult());
        }
    }

    private void handleResult(T result) {

        mInteractorExecution
                .getInteractorResult()
                .onResult(result);
    }

    private void handleError(InteractorError error) {

        InteractorResult errorResult = mInteractorExecution.getInteractorErrorResult(error.getClass());
        if (errorResult != null) {
            errorResult.onResult(error);
        } else {
            unhandledException(new RuntimeException("Unhandled handleError action: " + error
                    .getClass()
                    .toString()));
        }
    }

    private void unhandledException(Throwable cause) {

        UnhandledInteractorException e = new UnhandledInteractorException(mDescription,
                                                                          cause
                                                                                  .getClass()
                                                                                  .getName());
        e.initCause(cause);
        mUncaughtExceptionHandler.uncaughtException(Thread.currentThread(), e);
    }

    public int getPriority() {

        return mInteractorExecution.getPriority();
    }

    public String getDescription() {

        return mDescription;
    }
}
