package com.wappiter.data.appsession.api.result

import com.google.gson.annotations.SerializedName
import com.wappiter.data.base.ApiModel

/**
 * Created by porta on 18/12/18.
 */
class AppSessionApiModel(@field:SerializedName("code") val code: String) : ApiModel