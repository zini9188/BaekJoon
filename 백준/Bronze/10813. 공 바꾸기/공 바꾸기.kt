import java.util.Scanner

fun main() = with(Scanner(System.`in`)) {
    val N = nextInt()
    val M = nextInt()
    var buckets = IntArray(N) { it + 1 }
    
    for (time in 0 until M) {
        val i = nextInt()
        val j = nextInt()
        
        val temp = buckets[i - 1]
        buckets[i - 1] = buckets[j - 1]
        buckets[j - 1] = temp
    }
    
    buckets.forEach { print("${it} ") }
}