package usescase.getstation

import com.example.domain.model.Station
import com.example.domain.repositories.SearchRepository
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import common.CoroutinesTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

@ExperimentalCoroutinesApi
class GetStationsUseCaseTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private val searchRepository: SearchRepository = mock()

    private val fakeStationList = listOf(
        Station("Station1", 1.000, 2.000, "pic1"),
        Station("Station2", 1.000, 2.000, "pic2"),
        Station("Station3", 1.000, 2.000, "pic3"),
        Station("Station4", 1.000, 2.000, "pic4")
    )

    @Test
    fun `should have a list on the first call`() = runBlockingTest {

        val flow = flow {
            emit(fakeStationList)
        }
        Mockito.`when`(searchRepository.search("city")).thenReturn(flow)
        val result = searchRepository.search("city")

        result.collect {
            Assert.assertEquals(fakeStationList, it)
        }
    }

    @Test
    fun `given fakeStationList first item name should be Station1`() = runBlockingTest {

        val flow = flow {
            emit(fakeStationList)
        }
        Mockito.`when`(searchRepository.search("city")).thenReturn(flow)
        val result = searchRepository.search("city")

        result.collect {
            Assert.assertEquals("Station1", it.first().name)
        }
    }

}