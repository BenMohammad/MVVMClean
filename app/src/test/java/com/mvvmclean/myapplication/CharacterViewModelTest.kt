package com.mvvmclean.myapplication

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import com.domain.entities.MarvelCharacter
import com.domain.usecases.GetCharacterByIdUseCase
import com.domain.utils.Result
import com.google.common.truth.Truth
import com.mvvmclean.di.useCasesModule
import com.mvvmclean.di.viewModelsModule
import com.mvvmclean.myapplication.util.captureValues
import com.mvvmclean.myapplication.util.getValueForTest
import com.mvvmclean.repositoriesModule
import com.mvvmclean.utils.Data
import com.mvvmclean.utils.Status
import com.mvvmclean.viewmodels.CharacterViewModel
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.AutoCloseKoinTest
import org.koin.test.inject
import org.koin.test.mock.declareMock
import org.mockito.Mock
import org.mockito.Mockito.`when` as whenever
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit

class CharacterViewModelTest: AutoCloseKoinTest() {

    companion object {
        const val VALID_ID = 1017100
    }

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mockitoRule = MockitoJUnit.rule()

    lateinit var subject: CharacterViewModel

    @Mock lateinit var marvelCharacterResult: Result.Success<MarvelCharacter>
    @Mock lateinit var marvelCharacter: MarvelCharacter
    val getCharacterByIdUseCase: GetCharacterByIdUseCase by inject()

    @Before
    fun setup() {
        startKoin {
            modules(listOf(useCasesModule, viewModelsModule, repositoriesModule))
        }
        declareMock<GetCharacterByIdUseCase>()
        MockitoAnnotations.initMocks(this)
    }

    @After
    fun after() {
       stopKoin()
    }

    @Test
    fun onSearchRemoteTest() {
        subject = CharacterViewModel(getCharacterByIdUseCase)
        val liveDataUnderTest = subject.mainState.testObserver()

        runBlocking {
            whenever(getCharacterByIdUseCase.invoke(VALID_ID, true)).thenReturn(marvelCharacterResult)
            subject.onSearchRemoteClicked(VALID_ID)
        }

        Truth.assert_()
            .that(liveDataUnderTest.observedValues)
            .isEqualTo(listOf(Data(Status.LOADING), Data(Status.SUCCESSFUL, data = marvelCharacter)))
    }

    @Test
    fun `init method sets liveData value to empty list`() {
        val liveDataUnderTest = subject.mainState.testObserver()

        Truth.assert_()
            .that(liveDataUnderTest.observedValues)
            .isEqualTo(emptyList<String>())
    }

    @Test
    fun `on remote search set correct screen status`() {
        declareMock<GetCharacterByIdUseCase> {
            whenever(getCharacterByIdUseCase.invoke(VALID_ID, true)).thenReturn(marvelCharacterResult)
        }
        val liveDataUnderTest = subject.mainState.testObserver()
        runBlocking {
            subject.onSearchRemoteClicked(VALID_ID)
            Truth.assert_()
                .that(liveDataUnderTest.observedValues)
                .isEqualTo(listOf(Data(Status.LOADING), Data(Status.SUCCESSFUL, data = marvelCharacter)))
        }
    }

    @Test
    fun `Display after search remote`() {
        subject.mainState.captureValues {
            runBlocking {
                subject.onSearchRemoteClicked(VALID_ID)
            }
            subject.mainState.getValueForTest()?.responseType?.equals(Status.LOADING)?.let{ assert(it)}
        }
    }

    class TestObserver<T> : androidx.lifecycle.Observer<T> {
        val observedValues = mutableListOf<T?>()
        override fun onChanged(t: T) {
            observedValues.add(t)
        }
    }

    fun <T> LiveData<T>.testObserver() = TestObserver<T>().also {
        observeForever(it)
    }
}