import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.tunahankaryagdi.wordmate.data.Word
import com.tunahankaryagdi.wordmate.data.WordRepository
import com.tunahankaryagdi.wordmate.presentation.SharedViewModel
import com.tunahankaryagdi.wordmate.presentation.UiEvent
import com.tunahankaryagdi.wordmate.presentation.UiState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.*
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@ExperimentalCoroutinesApi
@FlowPreview
class SharedViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var wordRepository: WordRepository
    private lateinit var viewModel: SharedViewModel

    @Before
    fun setup() {
        wordRepository = mock()
        viewModel = SharedViewModel(wordRepository)
    }


    @Test
    fun `shuffleWords shuffles the unlearned words`() = runTest {
        val word1 = Word("merhaba", "hello")
        val word2 = Word("kitap", "book")
        val word3 = Word("kalem", "pen")
        val word4 = Word("kapı", "book")
        val word5 = Word("a", "a")
        val word6 = Word("b", "f")
        val word7 = Word("c", "e")
        val word8 = Word("d", "e")
        val word9 = Word("d", "d")
        val word0 = Word("e", "d")
        val initialList = listOf(word1, word2,word3,word4,word5,word6)
        viewModel.setUiStateForTesting(UiState(unlearnedWords = initialList))

        viewModel.shuffleWords()

        val state = viewModel.uiState.value
        assertTrue(state.unlearnedWords.contains(word1) && state.unlearnedWords.contains(word2))
        assertTrue(state.unlearnedWords != initialList)
    }

    @Test
    fun `getLearnedWords updates uiState with learned words`() = runTest {
        val word = Word("merhaba", "hello")
        whenever(wordRepository.getWords()).thenReturn(listOf(word))
        viewModel.getLearnedWords()

        val state = viewModel.uiState.value
        assertEquals(listOf(word), state.learnedWords)
    }


    @Test
    fun `clearEvent sets event to null`() = runTest {
        viewModel.setUiStateForTesting(UiState(event = UiEvent.WordSaved))
        viewModel.clearEvent()

        val state = viewModel.uiState.value
        assertEquals(null, state.event)
    }
}
