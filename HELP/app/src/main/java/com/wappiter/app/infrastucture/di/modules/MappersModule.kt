package com.wappiter.app.infrastucture.di.modules

import com.wappiter.data.base.mapper.ApiVoid
import com.wappiter.data.base.mapper.VoidApiModelMapper
import com.wappiter.domain.base.Mapper
import com.wappiter.domain.base.VoidModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by porta on 18/05/17.
 */
@Module
class MappersModule {

    @Singleton
    @Provides
    fun provideVoidModelMapper(): Mapper<ApiVoid, VoidModel> {
        return VoidApiModelMapper()
    }
}