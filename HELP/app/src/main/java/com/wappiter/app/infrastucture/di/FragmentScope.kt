package com.wappiter.app.infrastucture.di

/**
 * Created by porta on 18/05/17.
 */

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy.RUNTIME
import javax.inject.Scope

/**
 * A scoping annotation to permit objects whose lifetime should
 * conform to the life of the activity to be memorized in the
 * correct component.
 */
@Scope
@Retention(RUNTIME)
annotation class FragmentScope