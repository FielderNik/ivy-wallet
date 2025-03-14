package com.ivy.wallet.di

import com.ivy.core.AppStarter
import com.ivy.wallet.IvyAppStarter
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModuleBindings {
    @Binds
    abstract fun appStarter(appStarter: IvyAppStarter): AppStarter
}