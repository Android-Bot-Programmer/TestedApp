package ru.vaa.testedapp.di

import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import ru.vaa.testedapp.repository.MongoRepository
import ru.vaa.testedapp.repository.MongoRepositoryImpl

object DatabaseModule {

    fun provideRealm(): Realm {
        val config = RealmConfiguration.Builder(
            schema = setOf(
                /** empty :) **/
            )
        )
            .compactOnLaunch()
            .build()
        return Realm.open(config)
    }

    fun provideMongoRepository(realm: Realm): MongoRepository {
        return MongoRepositoryImpl(realm = realm)
    }

}