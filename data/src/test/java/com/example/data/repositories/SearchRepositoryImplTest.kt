package com.example.data.repositories

import com.example.data.api.CoroutinesTestRule
import com.example.data.api.FakeRemoteDataSourceImpl
import com.example.domain.model.Station
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class SearchRepositoryImplTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private val fakeStationList = listOf(
        Station("Station1", 1.000, 2.000, "pic1"),
        Station("Station2", 1.000, 2.000, "pic2"),
        Station("Station3", 1.000, 2.000, "pic3"),
        Station("Station4", 1.000, 2.000, "pic4")
    )

    private val fakeRemoteDataSource = FakeRemoteDataSourceImpl(fakeStationList, 6_000)
    private val repositoryImpl = SearchRepositoryImpl(fakeRemoteDataSource)


    @Test
    fun `when city is sent, a list is returned`() = runBlockingTest {

        repositoryImpl.search("city").collect {
            Assert.assertEquals(fakeStationList, it)
        }
    }
}