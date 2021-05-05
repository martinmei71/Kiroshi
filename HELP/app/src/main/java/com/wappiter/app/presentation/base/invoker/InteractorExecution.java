package com.wappiter.app.presentation.base.invoker;

import com.wappiter.app.presentation.base.InteractorResult;
import com.wappiter.domain.base.interactor.Interactor;
import com.wappiter.domain.base.interactor.InteractorResponse;
import com.wappiter.domain.base.interactor.baseerrors.InteractorError;

import java.util.HashMap;
import java.util.Map;

public class InteractorExecution<T> {

    private InteractorResult<T> mInteractorResult;
    private final Map<Class<? extends InteractorError>, InteractorResult<? extends InteractorError>> mErrors = new HashMap<>(0);
    private final Interactor<InteractorResponse<T>> mInteractor;
    private int mPriority;

    public InteractorExecution(Interactor<InteractorResponse<T>> interactor) {

        mInteractor = interactor;
    }

    public InteractorExecution<T> result(InteractorResult<T> interactorResult) {

        mInteractorResult = interactorResult;
        return this;
    }

    public InteractorExecution<T> error(Class<? extends InteractorError> errorClass,
            InteractorResult<? extends InteractorError> interactorError) {

        mErrors.put(errorClass, interactorError);
        return this;
    }

    public InteractorExecution<T> priority(int priority) {

        mPriority = priority;
        return this;
    }

    public Interactor<InteractorResponse<T>> getInteractor() {

        return mInteractor;
    }

    public InteractorResult<? extends InteractorError> getInteractorErrorResult(Class<? extends InteractorError> errorClass) {

        InteractorResult<? extends InteractorError> errorResult = mErrors.get(errorClass);

        if (errorResult == null) {

            errorResult = mErrors.get(errorClass.getSuperclass());
        }

        return errorResult;
    }

    public InteractorResult<T> getInteractorResult() {

        return mInteractorResult;
    }

    public void execute(InteractorInvoker interactorInvoker) {

        interactorInvoker.execute(this);
    }

    public int getPriority() {

        return mPriority;
    }
}
