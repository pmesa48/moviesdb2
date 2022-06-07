package com.pmesa48.moviedatabase.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.pmesa48.moviedatabase.TestCoroutineRule
import com.pmesa48.moviedatabase.usecase.discover.DiscoverInteractor
import com.pmesa48.moviedatabase.usecase.discover.DiscoverInteractorImpl
import com.pmesa48.moviedatabase.domain.repo.DiscoverRepository
import com.pmesa48.moviedatabase.domain.model.Movie
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when` as whenever
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class DiscoverInteractorTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()
    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var repository: DiscoverRepository
    private lateinit var discoverInteractor : DiscoverInteractor

    @Before
    fun setup(){
        discoverInteractor = DiscoverInteractorImpl(repository)
    }

    @Test
    fun `test discover movies success`() {
        val movieList = listOf(Movie(1, "title1", "poster1"))
        runBlocking {
            whenever(repository.discover(FetchStrategy.API)).thenReturn(movieList)
            val answers = discoverInteractor.discover().toList()
            assertEquals(2, answers.size)
            assertEquals(Result<List<Movie>?>(Result.Status.LOADING), answers[0])
            assertEquals(Result<List<Movie>?>(Result.Status.SUCCESS, data = movieList), answers[1])
        }
    }

    @Test
    fun `test discover movies empty`() {
        val movieList = emptyList<Movie>()
        runBlocking {
            whenever(repository.discover(FetchStrategy.API)).thenReturn(movieList)
            val answers = discoverInteractor.discover().toList()
            assertEquals(2, answers.size)
            assertEquals(Result<List<Movie>?>(Result.Status.LOADING), answers[0])
            assertEquals(Result<List<Movie>?>(Result.Status.ERROR), answers[1])
        }
    }


    @Test
    fun `test discover movies null`() {
        val movieList = null
        runBlocking {
            whenever(repository.discover(FetchStrategy.API)).thenReturn(movieList)
            val answers = discoverInteractor.discover().toList()
            assertEquals(2, answers.size)
            assertEquals(Result<List<Movie>?>(Result.Status.LOADING), answers[0])
            assertEquals(Result<List<Movie>?>(Result.Status.ERROR), answers[1])
        }
    }
}