package Algorithm.recursion;
//递归示例
public class RecursionTest {
    public static void main(String[] args) {
        test(4);

        int res = factorial(3);
        System.out.println("res = " + res);
    }

    private static int factorial(int n) {
        if(n == 1){
            return 1;
        }else{
            return factorial(n - 1) * n;
        }
    }

    private static void test(int n) {
        if(n > 2){
            test(n - 1);
        }
        System.out.println("n = " + n);
    }
}
