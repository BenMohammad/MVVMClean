package com.mvvmclean.data.database

import com.domain.entities.MarvelCharacter
import com.domain.utils.Result
import com.mvvmclean.data.mapper.CharacterMapperLocal
import io.realm.Realm
import java.lang.Exception

class CharacterDatabase {



        fun getCharacterById(id: Int): Result<MarvelCharacter> {
            val mapper = CharacterMapperLocal()
            Realm.getDefaultInstance().use {
                val character = it.where(MarvelCharacterRealm::class.java).equalTo("id", id).findFirst()
                character?.let { return Result.Success(mapper.transform(character))}
                return Result.Failure(Exception("Character not found"))
            }
        }

        fun insertOrUpdateCharacter(character: MarvelCharacter) {
            val mapperLocal = CharacterMapperLocal()
            Realm.getDefaultInstance().use {
                it.executeTransaction{
                    it.insertOrUpdate(mapperLocal.transformToRepository(character))
                }
            }
        }

}