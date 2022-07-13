package com.karel.comicbook.data.remote.api

import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import java.net.HttpURLConnection

class MockInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val uri = chain.request().url.toUri().toString()
        val responseString = when {
            uri.contains("/v1/public/comics") -> getListOfComicsJson
            else -> ""
        }

        return chain.proceed(chain.request())
            .newBuilder()
            .code(HttpURLConnection.HTTP_OK)
            .protocol(Protocol.HTTP_2)
            .message(responseString)
            .body(responseString.toByteArray().toResponseBody("application/json".toMediaTypeOrNull()))
            .addHeader("content-type", "application/json")
            .build()
    }

    private val getListOfComicsJson = "{\"code\":200,\"status\":\"Ok\",\"copyright\":\"© 2022 MARVEL\",\"attributionText\":\"Data provided by Marvel. © 2022 MARVEL\",\"attributionHTML\":\"<a href=\\\"http://marvel.com\\\">Data provided by Marvel. © 2022 MARVEL</a>\",\"etag\":\"cf6a9acb05c028ac19c0cfa21bac64d6b4414003\",\"data\":{\"offset\":0,\"limit\":1,\"total\":52534,\"count\":1,\"results\":[{\"id\":82967,\"digitalId\":0,\"title\":\"Marvel Previews (2017)\",\"issueNumber\":0,\"variantDescription\":\"\",\"description\":null,\"modified\":\"2019-11-07T08:46:15-0500\",\"isbn\":\"\",\"upc\":\"75960608839302811\",\"diamondCode\":\"\",\"ean\":\"\",\"issn\":\"\",\"format\":\"\",\"pageCount\":112,\"textObjects\":[],\"resourceURI\":\"http://gateway.marvel.com/v1/public/comics/82967\",\"urls\":[{\"type\": \"detail\",\"url\": \"http://marvel.com/comics/issue/82967/marvel_previews_2017?utm_campaign=apiRef&utm_source=29b9e03b971408793801bedf99b8a1f9\",\"count\": 1},{\"type\": \"gallery\",\"url\": \"http://marvel.com/comics/issue/82967/marvel_previews_2017?utm_campaign=apiRef&utm_source=29b9e03b971408793801bedf99b8a1f9\",\"short\": true}],\"series\":{\"resourceURI\":\"http://gateway.marvel.com/v1/public/series/23665\",\"name\":\"Marvel Previews (2017 - Present)\"},\"variants\":[{\"resourceURI\":\"http://gateway.marvel.com/v1/public/comics/82965\",\"name\":\"Marvel Previews (2017)\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/comics/82970\",\"name\":\"Marvel Previews (2017)\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/comics/82969\",\"name\":\"Marvel Previews (2017)\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/comics/74697\",\"name\":\"Marvel Previews (2017)\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/comics/72736\",\"name\":\"Marvel Previews (2017)\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/comics/75668\",\"name\":\"Marvel Previews (2017)\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/comics/65364\",\"name\":\"Marvel Previews (2017)\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/comics/65158\",\"name\":\"Marvel Previews (2017)\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/comics/65028\",\"name\":\"Marvel Previews (2017)\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/comics/75662\",\"name\":\"Marvel Previews (2017)\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/comics/74320\",\"name\":\"Marvel Previews (2017)\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/comics/73776\",\"name\":\"Marvel Previews (2017)\"}],\"collections\":[],\"collectedIssues\":[],\"dates\":[{\"type\":\"onsaleDate\",\"date\":\"2099-10-30T00:00:00-0500\"},{\"type\":\"focDate\",\"date\":\"2019-10-07T00:00:00-0400\"}],\"prices\":[{\"type\":\"printPrice\",\"price\":0}],\"thumbnail\":{\"path\":\"http://i.annihil.us/u/prod/marvel/i/mg/b/40/image_not_available\",\"extension\":\"jpg\"},\"images\":[],\"creators\":{\"available\":1,\"collectionURI\":\"http://gateway.marvel.com/v1/public/comics/82967/creators\",\"items\":[{\"resourceURI\":\"http://gateway.marvel.com/v1/public/creators/10021\",\"name\":\"Jim Nausedas\",\"role\":\"editor\"}],\"returned\":1},\"characters\":{\"available\":0,\"collectionURI\":\"http://gateway.marvel.com/v1/public/comics/82967/characters\",\"items\":[],\"returned\":0},\"stories\":{\"available\":2,\"collectionURI\":\"http://gateway.marvel.com/v1/public/comics/82967/stories\",\"items\":[{\"resourceURI\":\"http://gateway.marvel.com/v1/public/stories/183698\",\"name\":\"cover from Marvel Previews (2017)\",\"type\":\"cover\"},{\"resourceURI\":\"http://gateway.marvel.com/v1/public/stories/183699\",\"name\":\"story from Marvel Previews (2017)\",\"type\":\"interiorStory\"}],\"returned\":2},\"events\":{\"available\":0,\"collectionURI\":\"http://gateway.marvel.com/v1/public/comics/82967/events\",\"items\":[],\"returned\":0}}]}}"
}

