package com.example.gifter_single_module.di

import android.app.Application
import androidx.room.Room
import com.example.gifter_single_module.gift.data_source.GiftDatabase
import com.example.gifter_single_module.gift.gift_detail.use_case.AddEditGiftUseCase
import com.example.gifter_single_module.gift.gift_detail.use_case.GetGiftUseCase
import com.example.gifter_single_module.gift.gift_detail.use_case.GiftDetailUseCaseWrapper
import com.example.gifter_single_module.gift.gift_detail.use_case.validation.*
import com.example.gifter_single_module.gift.repository.GiftRepository
import com.example.gifter_single_module.gift.repository.GiftRepositoryImpl
import com.example.gifter_single_module.gift.gift_list.use_case.*
import com.example.gifter_single_module.profile.data_source.ProfileDatabase
import com.example.gifter_single_module.profile.profile_list.repository.ProfileRepository
import com.example.gifter_single_module.profile.profile_list.repository.ProfileRepositoryImpl
import com.example.gifter_single_module.profile.profile_list.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    //  Provide Databases

    @Provides
    @Singleton
    fun providingGiftDatabase(app: Application): GiftDatabase {
        return Room.databaseBuilder(
            app,
            GiftDatabase::class.java,
            GiftDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun providingProfileDatabase(app: Application): ProfileDatabase {
        return Room.databaseBuilder(
            app,
            ProfileDatabase::class.java,
            ProfileDatabase.DATABASE_NAME
        ).build()
    }

    // Provide Repositories
    @Provides
    @Singleton
    fun provideGiftRepository(db: GiftDatabase): GiftRepository {
        return GiftRepositoryImpl(db.giftDao)
    }

    @Provides
    @Singleton
    fun provideProfileRepository(db: ProfileDatabase): ProfileRepository {
        return ProfileRepositoryImpl(db.profileDao)
    }

    // Provide UseCases
    @Provides
    @Singleton
    fun provideGiftListUseCases(repository: GiftRepository): GiftListUseCaseWrapper {
        return GiftListUseCaseWrapper(
            getGifts = GetGiftsUseCase(repository),
            deleteGift = DeleteGiftUseCase(repository),
            addEditGift = AddEditGiftUseCase(repository)

        )
    }

    @Provides
    @Singleton
    fun provideGiftDetailUseCases(repository: GiftRepository): GiftDetailUseCaseWrapper {
        return GiftDetailUseCaseWrapper(
            getGift = GetGiftUseCase(repository),
            addEditGift = AddEditGiftUseCase(repository),
            validateSaveGift = ValidateSaveGiftUseCase(),
            validateTitle = ValidateTitleUseCase(),
            validateDescription = ValidateDescriptionUseCase(),
            validateOwnerName = ValidateOwnerNameUseCase(),
            validateMark = ValidateMarkUseCase(),
            validatePrice = ValidatePriceUseCase()
        )
    }

    @Provides
    @Singleton
    fun provideProfileUseCases(repository: ProfileRepository): ProfileUseCaseWrapper {
        return ProfileUseCaseWrapper(
            getProfiles = GetProfilesUseCase(repository),
            getProfile = GetProfileUseCase(repository),
            deleteProfile = DeleteProfileUseCase(repository),
            addEditProfile = AddEditProfileUseCase(repository),
            getProfileWithGifts = GetProfileWithGiftsUseCase(repository)
        )
    }
}