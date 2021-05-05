package com.wappiter.app.module.main.fragments.scanner

import com.wappiter.app.infrastucture.di.FragmentScope
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [ScannerFragmentModule::class])

interface ScannerFragmentComponent {

    fun inject(scannerFragment: ScannerFragment)
}