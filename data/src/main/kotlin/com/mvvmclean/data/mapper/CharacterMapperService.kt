package com.mvvmclean.data.mapper

import com.domain.entities.MarvelCharacter
import com.mvvmclean.data.response.CharacterResponse
import com.mvvmclean.data.response.MarvelBaseResponse

open class CharacterMapperService: BaseMapperRepository<CharacterResponse, MarvelCharacter> {

    override fun transform(type: CharacterResponse): MarvelCharacter =
        MarvelCharacter(
            type.id,
            type.name,
            type.description
        )


    override fun transformToRepository(type: MarvelCharacter): CharacterResponse =
        CharacterResponse(
            type.id,
            type.name,
            type.description
        )
}