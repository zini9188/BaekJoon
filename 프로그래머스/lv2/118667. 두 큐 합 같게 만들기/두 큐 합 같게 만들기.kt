import java.util.LinkedList

class Solution {
    fun solution(queue1: IntArray, queue2: IntArray): Int {
        var answer: Int = 0

        var a = queue1.sumOf { it.toLong() }
        var b = queue2.sumOf { it.toLong() }
        val q1 = LinkedList<Int>()
        val q2 = LinkedList<Int>()

        queue1.forEach {
            q1.offer(it)
        }
        queue2.forEach {
            q2.offer(it)
        }

        while(answer <= 9999999){
            if(a == b){
                return answer
            }
            else if(a > b){
                a -= q1.peek()
                b += q1.peek()
                q2.offer(q1.poll())
                answer++
            }else{
                a += q2.peek()
                b -= q2.peek()
                q1.offer(q2.poll())
                answer++
            }
        }

        return -1
    }
}
