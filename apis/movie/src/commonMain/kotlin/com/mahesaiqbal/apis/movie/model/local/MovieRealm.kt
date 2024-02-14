package com.mahesaiqbal.apis.movie.model.local

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class MovieRealm : RealmObject {
    @PrimaryKey
    var id: Int = 0
    var backdropPath: String = ""
    var overview: String = ""
    var popularity: Double = 0.0
    var posterPath: String = ""
    var releaseDate: String = ""
    var title: String = ""
    var voteAverage: Double = 0.0
    var voteCount: Int = 0
}