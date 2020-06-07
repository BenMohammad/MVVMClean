package com.mvvmclean.data.mapper

import com.domain.entities.MarvelCharacter
import com.mvvmclean.data.database.entity.MarvelCharacterRealm

class CharacterMapperLocal: BaseMapperRepository<MarvelCharacterRealm, MarvelCharacter> {

    override fun transform(type: MarvelCharacterRealm): MarvelCharacter = MarvelCharacter(
        type.id,
        type.name,
        type.description
    )


    override fun transformToRepository(type: MarvelCharacter): MarvelCharacterRealm =
        MarvelCharacterRealm(
            type.id,
            type.name,
            type.description
        )
}