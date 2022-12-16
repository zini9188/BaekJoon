class Solution {
    var apeach = MutableList(11) { 0 }
    var lion = MutableList(11) { 0 }
    var result = MutableList(11) { 0 }
    var maxSub = 0

    fun dfs(index: Int, arrow: Int, lionScore: Int, apeachScore: Int) {
        if (index > 10) {
            if (lionScore > apeachScore) {
                lion[10] = arrow
                val sub = lionScore - apeachScore
                if (sub > maxSub) {
                    maxSub = sub
                    result = lion.toMutableList()
                } else if (sub == maxSub) {
                    for (it in 10 downTo 0) {
                        if (lion[it] != result[it]) {
                            if (lion[it] > result[it])
                                result = lion.toMutableList()
                            break
                        }
                    }
                }
            }
            return
        }

        if (arrow > apeach[index]) {
            lion[index] = apeach[index] + 1
            dfs(index + 1, arrow - apeach[index] - 1, lionScore + 10 - index, apeachScore)
            lion[index] = 0
        }

        val currentScore = if (apeach[index] > 0) 10 - index else 0
        dfs(index + 1, arrow, lionScore, apeachScore + currentScore)

    }

    fun solution(n: Int, info: IntArray): IntArray {
        apeach = info.toMutableList()
        dfs(0, n, 0, 0)

        if (maxSub == 0) return intArrayOf(-1)

        return result.toIntArray()
    }
}
