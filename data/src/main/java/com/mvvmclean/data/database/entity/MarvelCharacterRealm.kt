package com.mvvmclean.data.database.entity

import com.mvvmclean.data.DEFAULT_ID
import com.mvvmclean.data.EMPTY_STRING
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey


open class MarvelCharacterRealm (

    @PrimaryKey
    var id: Int = DEFAULT_ID,
    var name: String = EMPTY_STRING,
    var description: String = EMPTY_STRING
): RealmObject()