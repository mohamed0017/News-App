package Com.Task.newsApp

import Com.Task.newsApp.base.BaseMockServerTest
import Com.Task.newsApp.di.networkMockedComponent
import Com.Task.newsApp.di.repoMockedModule
import Com.Task.newsApp.di.viewModelModule
import Com.Task.newsApp.repository.NewsRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.core.context.startKoin
import org.koin.test.inject
import retrofit2.HttpException
import java.net.HttpURLConnection

@RunWith(JUnit4::class)
class NewsRepoTest : BaseMockServerTest() {

    private val newsRepo by inject<NewsRepository>()

    override fun setUp() {
        super.setUp()
        startKoin {
            modules(
                listOf(
                    viewModelModule,
                    networkMockedComponent(mockServer.url("/").toString()),
                    repoMockedModule()
                )
            )
        }
    }

    @Test
    fun get_news_result_ok() {
        mockHttpResponse("result_news_mocked.json", HttpURLConnection.HTTP_OK)
        runBlocking {
            val newsListMocked = newsRepo.getAllNewsWithPagination(getParameters())
            assertNotNull(newsListMocked)
            assertEquals(newsListMocked.isNullOrEmpty(), false)
        }
    }

    @Test(expected = HttpException::class)
    fun get_news_result_error() {
        mockHttpResponse("result_news_mocked.json", HttpURLConnection.HTTP_BAD_REQUEST)
        runBlocking {
            val newsListMocked = newsRepo.getAllNewsWithPagination(getParameters())
            assertEquals(newsListMocked.isNullOrEmpty(), true)
        }
    }

    private fun getParameters(): HashMap<String, String> {
        val parameters =  HashMap<String, String>()
        parameters["page"] = "1"
        parameters["q"] = "all"
        return parameters
    }
}