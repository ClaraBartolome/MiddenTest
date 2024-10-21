package com.example.middentest.data.models

import com.google.gson.annotations.SerializedName

data class UserInfoApiResult(

    @SerializedName("results") var results: List<UserInfo> = listOf(),
    @SerializedName("info") var info: Info? = Info()

)

data class UserInfo(
    @SerializedName("gender") var gender: String? = null,
    @SerializedName("name") var name: Name? = Name(),
    @SerializedName("location") var location: Location? = Location(),
    @SerializedName("email") var email: String? = null,
    /*@SerializedName("login"      ) var login      : Login?      = Login(),
    @SerializedName("dob"        ) var dob        : Dob?        = Dob(),*/
    @SerializedName("registered") var registered: Registered? = Registered(),
    @SerializedName("phone") var phone: String? = null,
    @SerializedName("cell") var cell: String? = null,
//    @SerializedName("id"         ) var id         : Id?         = Id(),
    @SerializedName("picture") var picture: Picture? = Picture(),
    @SerializedName("nat") var nat: String? = null
)

data class Info(
    @SerializedName("seed") var seed: String? = null,
    @SerializedName("results") var results: Int? = null,
    @SerializedName("page") var page: Int? = null,
    @SerializedName("version") var version: String? = null
)


data class Name(
    @SerializedName("title") var title: String? = null,
    @SerializedName("first") var first: String? = null,
    @SerializedName("last") var last: String? = null
) {
    override fun toString(): String {
        return "$first $last"
    }
}

data class Location(

    @SerializedName("street") var street: Street? = Street(),
    @SerializedName("city") var city: String? = null,
    @SerializedName("state") var state: String? = null,
    @SerializedName("country") var country: String? = null,
    @SerializedName("postcode") var postcode: String? = null,
    @SerializedName("coordinates") var coordinates: Coordinates? = Coordinates(),
    @SerializedName("timezone") var timezone: Timezone? = Timezone()

) {
    fun markerLocation(): String {
        return "$city, $state, $country"
    }
}

data class Street(

    @SerializedName("number") var number: Int? = null,
    @SerializedName("name") var name: String? = null

) {
    override fun toString(): String {
        return "$name $number"
    }
}

data class Coordinates(

    @SerializedName("latitude") var latitude: String? = null,
    @SerializedName("longitude") var longitude: String? = null

)

data class Timezone(

    @SerializedName("offset") var offset: String? = null,
    @SerializedName("description") var description: String? = null

)

data class Picture(

    @SerializedName("large") var large: String? = null,
    @SerializedName("medium") var medium: String? = null,
    @SerializedName("thumbnail") var thumbnail: String? = null

)

data class Registered(

    @SerializedName("date") var date: String? = null,
    @SerializedName("age") var age: Int? = null

)