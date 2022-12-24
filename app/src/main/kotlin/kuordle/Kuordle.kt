package kuordle

enum class ResultStatus { CORRECT, MISS, WRONG }

class Kuordle(private var answer: String) {

    init {
        if (answer.trim().length != 5) throw IllegalArgumentException()

        answer = answer.trim()
    }

    fun guess(guess: String): Status {
        val results: MutableList<ResultStatus> = mutableListOf()

        checkLetter(guess[0], answer[0], results)
        checkLetter(guess[1], answer[1], results)
        checkLetter(guess[2], answer[2], results)
        checkLetter(guess[3], answer[3], results)
        checkLetter(guess[4], answer[4], results)

        return Status(answer.lowercase() == guess.lowercase(), guess, results)
    }

    private fun checkLetter(guessLetter: Char, answerLetter: Char, results: MutableList<ResultStatus>
    ) {
        if (guessLetter == answerLetter) {
            results.add(ResultStatus.CORRECT)
        } else {
            if (answer.contains(guessLetter)) {
                results.add(ResultStatus.MISS)
            } else {
                results.add(ResultStatus.WRONG)
            }
        }
    }

    class Status(val result: Boolean, val guess: String, val results: List<ResultStatus>)
}

fun main() {
    println(Kuordle("brush"))
}
