import kotlin.math.min

class Solution {
    fun solution(rows: Int, columns: Int, queries: Array<IntArray>): IntArray {
        var answer = intArrayOf()
        var n = 1
        val board = Array(rows) { Array(columns) { n++ } }


        queries.forEach {
            val (x1, y1) = it[0] - 1 to it[1] - 1
            val (x2, y2) = it[2] - 1 to it[3] - 1
            val left = Array(x2 - x1) { i -> board[x1 + i + 1][y1] }
            val up = Array(y2 - y1) { i -> board[x1][y1 + i] }
            val right = Array(x2 - x1) { i -> board[x1 + i][y2] }
            val down = Array(y2 - y1) { i -> board[x2][y1 + 1 + i] }
            var myMin = rows * columns

            left.forEachIndexed { index, i ->
                board[x1 + index][y1] = i
                myMin = min(myMin, i)
            }
            up.forEachIndexed { index, i ->
                board[x1][y1 + index + 1] = i
                myMin = min(myMin, i)
            }
            right.forEachIndexed { index, i ->
                board[x1 + 1 + index][y2] = i
                myMin = min(myMin, i)
            }
            down.forEachIndexed { index, i ->
                board[x2][y1 + index] = i
                myMin = min(myMin, i)
            }

            answer += myMin
        }
        return answer
    }
}