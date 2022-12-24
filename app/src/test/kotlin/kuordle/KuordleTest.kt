package kuordle

import kotlin.test.*

class KuordleTest {
    @Test
    fun answersMustBeCorrectLength() {
        assertFailsWith<IllegalArgumentException> { Kuordle("brushes") }
        assertFailsWith<IllegalArgumentException> { Kuordle("rush") }
        assertFailsWith<IllegalArgumentException> { Kuordle("rush ") }
        assertFailsWith<IllegalArgumentException> { Kuordle("") }
        Kuordle("brush ")
        Kuordle("brush")
    }

    @Test
    fun statusContainsGuess() {
        assertEquals("brush", Kuordle("brush").guess("brush").guess)
    }

    @Test
    fun statusContainsLetterResults() {
        val results = Kuordle("brush").guess("slash").results
        assertEquals(ResultStatus.MISS, results[0])
        assertEquals(ResultStatus.WRONG, results[1])
        assertEquals(ResultStatus.WRONG, results[2])
        assertEquals(ResultStatus.CORRECT, results[3])
        assertEquals(ResultStatus.CORRECT, results[4])
    }

    @Test
    fun correctChoiceIsRecognised() {
        assertTrue(Kuordle("brush").guess("brush").result)
        assertTrue(Kuordle("brush ").guess("brush").result)
        assertTrue(Kuordle(" brush").guess("BRUSH").result)
        assertFalse(Kuordle("brush").guess("crush").result)
    }
}
