package com.pmesa48.moviedatabase.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.pmesa48.moviedatabase.TestCoroutineRule
import com.pmesa48.moviedatabase.domain.repo.DiscoverRepository
import com.pmesa48.moviedatabase.domain.repo.DiscoverRepositoryImpl
import com.pmesa48.moviedatabase.data.api.discover.DiscoverService
import com.pmesa48.moviedatabase.data.api.discover.DiscoverServiceResponse
import com.pmesa48.moviedatabase.data.api.dto.DiscoverMovieDto
import com.pmesa48.moviedatabase.data.local.DiscoverCache
import com.pmesa48.moviedatabase.domain.model.Movie
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.Mockito.`when` as whenever

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class DiscoverRepositoryTest {

    @Mock
    private lateinit var mapper: DiscoverMovieMapper

    @Mock
    private lateinit var cache: DiscoverCache

    @Mock
    private lateinit var api: DiscoverService

    private lateinit var repository: DiscoverRepository

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()
    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Before
    fun setup(){
        repository = DiscoverRepositoryImpl(api, cache, mapper)
    }


    @Test
    fun `test discover from api success`() {
        runBlocking {
            val mock = mock(DiscoverServiceResponse::class.java)
            val result = Result(Result.Status.SUCCESS, data = mock)
            val element = DiscoverMovieDto()
            whenever(mock.results).thenReturn(listOf(element))
            whenever(api.fetch(page)).thenReturn(result)
            val movie = Movie(1, "title", "poster")
            whenever(mapper.convertDtoToModel(element)).thenReturn(movie)
            val response = repository.discover(FetchStrategy.API)
            Assert.assertNotNull(response)
            verify(cache, never()).fetch(page)
            verify(cache, times(1)).insertAll(listOf(movie))
        }
    }


    @Test
    fun `test discover from api null`() {
        runBlocking {
            val result = Result(Result.Status.ERROR, data = null)
            whenever(api.fetch(page)).thenReturn(result)
            val response = repository.discover(FetchStrategy.API)
            Assert.assertNull(response)
            verify(cache, never()).insertAll(anyList())
        }
    }


    @Test
    fun `test discover from cache success`() {
        runBlocking {
            val movie = Movie(1, "title", "poster")
            val result = listOf(movie)
            whenever(cache.fetch(page)).thenReturn(result)
            val response = repository.discover(FetchStrategy.CACHE)
            Assert.assertNotNull(response)
            verify(api, never()).fetch(page)
        }
    }

    @Test
    fun `test discover from cache empty`() {
        runBlocking {
            val result = emptyList<Movie>()
            whenever(cache.fetch(page)).thenReturn(result)
            val response = repository.discover(FetchStrategy.CACHE)
            Assert.assertNull(response)
            verify(api, never()).fetch(page)
        }
    }
}