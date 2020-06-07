package com.mvvmclean.data.database

import com.domain.entities.MarvelCharacter
import com.domain.utils.Result
import com.mvvmclean.data.mapper.CharacterMapperLocal
import io.realm.Realm
import java.lang.Exception

class CharacterDatabase {

    companion object {
        fun getCharacterById(id: Int): Result<MarvelCharacter> {
            val mapper = CharacterMapperLocal()
            val realm = Realm.getDefaultInstance()
            val character = realm.where(MarvelCharacterRealm::class.java).equalTo("id", id).findFirst()
            character?.let {
                return Result.Success(mapper.transform(character))
            }
            return Result.Failure(Exception("Character not found"))
        }
    }
}