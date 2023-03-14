import java.util.ArrayList;

class Solution {
    static String[] permutation = {
            "*-+",
            "*+-",
            "+*-",
            "+-*",
            "-*+",
            "-+*"
    };

    public static long solution(String expression) {
        long answer = 0;
        ArrayList<Character> operators = new ArrayList<>();
        ArrayList<Long> numbers = new ArrayList<>();
        split(expression, operators, numbers);
        for (String cases : permutation) {
            ArrayList<Character> operatorsClone = (ArrayList<Character>) operators.clone();
            ArrayList<Long> numbersClone = (ArrayList<Long>) numbers.clone();
            for(char operator : cases.toCharArray()){
                for (int j = 0; j < operatorsClone.size(); j++) {
                    if (operatorsClone.get(j) == operator) {
                        long cal = calculate(operator, numbersClone.remove(j), numbersClone.remove(j));
                        operatorsClone.remove(j);
                        numbersClone.add(j, cal);
                        j--;
                    }
                }
            }
            answer = Math.max(Math.abs(numbersClone.get(0)), answer);
        }
        return answer;
    }

    private static void split(String expression, ArrayList<Character> operators, ArrayList<Long> numbers) {
        StringBuilder temp = new StringBuilder();
        for (char charAt : expression.toCharArray()) {
            if (charAt == '*' || charAt == '-' || charAt == '+') {
                operators.add(charAt);
                numbers.add(Long.parseLong(temp.toString()));
                temp = new StringBuilder();
            } else {
                temp.append(charAt);
            }
        }
        numbers.add(Long.parseLong(temp.toString()));
    }

    private static long calculate(char operator, long left, long right) {
        if (operator == '-') {
            return left - right;
        } else if (operator == '+') {
            return left + right;
        } else {
            return left * right;
        }
    }
}