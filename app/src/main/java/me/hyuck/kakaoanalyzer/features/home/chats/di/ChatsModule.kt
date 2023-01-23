package me.hyuck.kakaoanalyzer.features.home.chats.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import me.hyuck.kakaoanalyzer.features.home.chats.data.ChatsEnvironment
import me.hyuck.kakaoanalyzer.features.home.chats.data.IChatsEnvironment

@Module
@InstallIn(ViewModelComponent::class)
abstract class ChatsModule {

    @Binds
    abstract fun provideEnvironment(
        environment: ChatsEnvironment
    ): IChatsEnvironment

}