package sort;

import java.util.ArrayList;

public class QSort {
    public static void Swap(int[] a, int Lhs, int Rhs) {
        int Tmp = a[Lhs];
        a[Lhs] = a[Rhs];
        a[Rhs] = Tmp;
        /*
        a[Lhs] ^= a[Rhs];
        a[Rhs] ^= a[Lhs];
        a[Lhs] ^= a[Rhs];
        */
    }

    public static int Median3(int A[], int Left, int Right) {
        int Center = (Left + Right) / 2;

        if (A[Left] > A[Center])
            Swap(A, Left, Center);
        if (A[Left] > A[Right])
            Swap(A, Left, Right);
        if (A[Center] > A[Right])
            Swap(A, Center, Right);

        Swap(A, Center, Right - 1);
        return A[Right - 1];
    }


    public static void sort(int[] A, int Left, int Right) {
        int i, j;
        int Pivot;

        if (Left + 3 <= Right) {
            Pivot = Median3(A, Left, Right);
            i = Left;
            j = Right - 1;
            for (; ; ) {
                while (A[++i] < Pivot) {
                }
                while (A[--j] > Pivot) {
                }
                if (i < j) {
                    Swap(A, i, j);
                } else {
                    break;
                }
            }
            Swap(A, i, Right - 1);  /* Restore pivot */

            sort(A, Left, i - 1);
            sort(A, i + 1, Right);
        } else {
            InsertSort.sort(A);
        }
    }

    //《数据结构与算法分析》C++11版的第七章快排用Vector的例子，改为Java用ArrayList的版本
    //比较容易理解和记忆
    public static void listSort(ArrayList<Integer> arr){
        if(arr.size() > 1){
            ArrayList<Integer> smaller = new ArrayList<>();
            ArrayList<Integer> same = new ArrayList<>();
            ArrayList<Integer> larger = new ArrayList<>();

            int chosenNum = arr.get(arr.size() / 2);

            for(int i : arr){
                if(i < chosenNum){
                    smaller.add(i);
                }else if(chosenNum < i){
                    larger.add(i);
                }else{
                    same.add(i);
                }
            }

            //递归调用
            listSort(smaller);
            listSort(larger);

            arr.clear();
            arr.addAll(smaller);
            arr.addAll(same);
            arr.addAll(larger);
            //System.out.println(arr.toArray());
        }
    }
}
