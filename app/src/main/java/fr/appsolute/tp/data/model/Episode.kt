package fr.appsolute.tp.data.model

import com.google.gson.annotations.SerializedName

/**
 * This class define what is the model Character
 */

/*
{
  "info": {
    "count": 31,
    "pages": 2,
    "next": "https://rickandmortyapi.com/api/episode?page=2",
    "prev": ""
  },
  "results": [
    {
      "id": 1,
      "name": "Pilot",
      "air_date": "December 2, 2013",
      "episode": "S01E01",
      "characters": [
        "https://rickandmortyapi.com/api/character/1",
        "https://rickandmortyapi.com/api/character/2",
        //...
      ],
      "url": "https://rickandmortyapi.com/api/episode/1",
      "created": "2017-11-10T12:56:33.798Z"
    },
    // ...
  ]
}
 */

data class Episode(
    @SerializedName("id") val id: Int,
    @SerializedName("air_date") val air_date: String,
    @SerializedName("episode") val episode: String,
    @SerializedName("characters") val characters: List<String>,
    @SerializedName("url") val url: String,
    @SerializedName("created") val created: String
)
{
}