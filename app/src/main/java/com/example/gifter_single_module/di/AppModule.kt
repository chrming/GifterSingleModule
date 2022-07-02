package com.example.gifter_single_module.di

import android.app.Application
import androidx.room.Room
import com.example.gifter_single_module.gift.data_source.GiftDatabase
import com.example.gifter_single_module.gift.gift_detail.use_case.AddEditGiftUseCase
import com.example.gifter_single_module.gift.gift_detail.use_case.GetGiftUseCase
import com.example.gifter_single_module.gift.gift_detail.use_case.GetProfileNameIdUseCase
import com.example.gifter_single_module.gift.gift_detail.use_case.GiftDetailUseCaseWrapper
import com.example.gifter_single_module.gift.gift_detail.use_case.validation.*
import com.example.gifter_single_module.gift.gift_list.use_case.DeleteGiftUseCase
import com.example.gifter_single_module.gift.gift_list.use_case.GetGiftsUseCase
import com.example.gifter_single_module.gift.gift_list.use_case.GiftListUseCaseWrapper
import com.example.gifter_single_module.gift.repository.GiftRepository
import com.example.gifter_single_module.gift.repository.GiftRepositoryImpl
import com.example.gifter_single_module.profile.common.use_case.AddEditProfileUseCase
import com.example.gifter_single_module.profile.common.use_case.GetProfileUseCase
import com.example.gifter_single_module.profile.profile_detail.use_case.GetProfileWithGiftsUseCase
import com.example.gifter_single_module.profile.profile_detail.use_case.ProfileAddEditUseCaseWrapper
import com.example.gifter_single_module.profile.profile_detail.use_case.ProfileDetailUseCaseWrapper
import com.example.gifter_single_module.profile.profile_detail.use_case.validation.ValidateBirthdayDateUseCase
import com.example.gifter_single_module.profile.profile_detail.use_case.validation.ValidateNameUseCase
import com.example.gifter_single_module.profile.profile_detail.use_case.validation.ValidateNamedayDateUseCase
import com.example.gifter_single_module.profile.profile_detail.use_case.validation.ValidateSaveProfileUseCase
import com.example.gifter_single_module.profile.profile_list.use_case.DeleteProfileUseCase
import com.example.gifter_single_module.profile.profile_list.use_case.GetProfileListUseCase
import com.example.gifter_single_module.profile.profile_list.use_case.ProfileListUseCaseWrapper
import com.example.gifter_single_module.profile.repository.ProfileRepository
import com.example.gifter_single_module.profile.repository.ProfileRepositoryImpl
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

/*    @Provides
    @Singleton
    fun providingProfileDatabase(app: Application): ProfileDatabase {
        return Room.databaseBuilder(
            app,
            ProfileDatabase::class.java,
            ProfileDatabase.DATABASE_NAME
        ).build()
    }
*/
    // Provide Repositories
    @Provides
    @Singleton
    fun provideGiftRepository(db: GiftDatabase): GiftRepository {
        return GiftRepositoryImpl(db.giftDao)
    }

    @Provides
    @Singleton
    fun provideProfileRepository(db: GiftDatabase): ProfileRepository {
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
            getProfileNameId = GetProfileNameIdUseCase(repository),
            validateSaveGift = ValidateSaveGiftUseCase(),
            validateTitle = ValidateTitleUseCase(),
            validateDescription = ValidateDescriptionUseCase(),
            validateOwnerName = ValidateOwnerNameUseCase(),
            validateMark = ValidateMarkUseCase(),
            validatePrice = ValidatePriceUseCase(),
            validateUrl = ValidateUrlUseCase()
        )
    }

    @Provides
    @Singleton
    fun provideProfileListUseCases(repository: ProfileRepository): ProfileListUseCaseWrapper {
        return ProfileListUseCaseWrapper(
            addEditProfile = AddEditProfileUseCase(repository),
            deleteProfile = DeleteProfileUseCase(repository),
            getProfiles = GetProfileListUseCase(repository)
        )
    }

    @Provides
    @Singleton
    fun provideProfileAddEditUseCases(repository: ProfileRepository): ProfileAddEditUseCaseWrapper {
        return ProfileAddEditUseCaseWrapper(
            getProfile = GetProfileUseCase(repository),
            addEditProfile = AddEditProfileUseCase(repository),
            validateName = ValidateNameUseCase(),
            validateBirthdayDate = ValidateBirthdayDateUseCase(),
            validateNamedayDate = ValidateNamedayDateUseCase(),
            validateSaveProfile = ValidateSaveProfileUseCase(),
        )
    }

    @Provides
    @Singleton
    fun provideProfileDetailUseCases(repository: ProfileRepository): ProfileDetailUseCaseWrapper {
        return ProfileDetailUseCaseWrapper(
            getProfile = GetProfileUseCase(repository),
            getWithGifts = GetProfileWithGiftsUseCase(repository)
        )
    }
}