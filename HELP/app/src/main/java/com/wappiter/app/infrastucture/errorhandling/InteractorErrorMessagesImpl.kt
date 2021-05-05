package com.wappiter.app.infrastucture.errorhandling

import android.content.Context
import com.wappiter.app.R
import com.wappiter.infrastructure.interactor.InteractorErrorMessages

/**
 * Created by porta on 16/11/17.
 */
class InteractorErrorMessagesImpl(private val mContext: Context) : InteractorErrorMessages {
    override fun invalidUserName(): String {
        return mContext.resources.getString(R.string.error_generic)
    }

    override fun invalidPassword(): String {
        return mContext.resources.getString(R.string.error_generic)
    }

    override fun networkError(): String {
        return mContext.resources.getString(R.string.error_generic)
    }

    override fun mapperError(): String {
        return mContext.resources.getString(R.string.error_generic)
    }

}