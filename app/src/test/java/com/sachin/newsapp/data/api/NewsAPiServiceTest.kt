package com.sachin.newsapp.data.api

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
// for assertions on Java 8 types (Streams and java.util.Optional)
import com.google.common.truth.Truth.assertThat
import com.google.common.truth.Truth.assertWithMessage
import kotlinx.coroutines.runBlocking

// for assertions on Java 8 types (Streams and java.util.Optional)


class NewsAPiServiceTest {
    private lateinit var  service:NewsAPIService
    private lateinit var server:MockWebServer

    @Before
    fun setUp() {
        server = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(server.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsAPIService::class.java)
    }

    private fun enqueueMockResponse(
        filName:String){
        val inputStream = javaClass.classLoader!!.getResourceAsStream(filName)
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        mockResponse.setBody(source.readString(Charsets.UTF_8))
        server.enqueue(mockResponse)

    }
    @Test
    fun getTopHeadlines_sentRequest_receivesExpected(){
        runBlocking {
            enqueueMockResponse("newsresponse.json")
            val responseBody = service.getTopHeadlines("in", 1).body()
            val request = server.takeRequest()
            assertThat(responseBody).isNotNull()
            //assertThat(request.path).isEqualTo("/v2/top-headlines?country=in&apiKey=a6b507e77afa45698aef0f7a0dd15707")
        }
    }

    @Test
    fun getTopHeadlines_recivedResponse_correctPageSize(){
        runBlocking {
            enqueueMockResponse("newsresponse.json")
            val responseBody = service.getTopHeadlines("in", 1).body()
            val articleList = responseBody!!.articles
            assertThat(articleList.size).isEqualTo(20)
        }
    }

    @Test
    fun getTopHeadlines_recivedResponse_correctContent(){
        runBlocking {
            enqueueMockResponse("newsresponse.json")
            val responseBody = service.getTopHeadlines("in", 1).body()
            val articleList = responseBody!!.articles
            val article = articleList[2]
            assertThat(article.author).isEqualTo("Mihir Vasavda")
        }
    }
//    @After
//    fun tearDown() {
//        TODO("Not yet implemented")
//    }
}