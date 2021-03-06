package Algorithm.dac;
/*
分治算法(Divide and Conquer(P))

分治算法介绍
分治法是一种很重要的算法，字面上的解释是“分而治之”，就是把一个复杂的问题分成两个
或更多的相同或相似的子问题，再把子问题分成更小的子问题……直到最后子问题可以简单的
直接求解，原问题的解即子问题的解的合并。这个技巧是很多高效算法的基础，如排序算法
（快速排序、归并排序），傅立叶变换（快速傅立叶变换）……
分治算法可以求解一些经典问题
二分搜索
大整数乘法
棋盘覆盖
合并排序
快速排序
线性时间选择
最接近点对问题
循环赛日程表
汉诺塔

分治算法的基本步骤
分治法在每一层递归上都有三个步骤
1.分解：将原问题分解为若干个规模较小，相互独立，与原问题形式相同的子问题
2.解决：若子问题规模较小而容易被解决则直接解，否则递归地解各个子问题
3.合并：将各个子问题的解合并为原问题的解
 */

//汉诺塔问题
/*
汉诺塔（Tower of Hanoi），又称河内塔，是一个源于印度古老传说的益智玩具。
大梵天创造世界的时候做了三根金刚石柱子，在一根柱子上从下往上按照大小顺序
摞着64片黄金圆盘。大梵天命令婆罗门把圆盘从下面开始按大小顺序重新摆放在另
一根柱子上。并且规定，在小圆盘上不能放大圆盘，在三根柱子之间一次只能移动
一个圆盘。
 */
public class Hanoitower {

    public static void main(String[] args){
        hanoiTower(30, 'A', 'B', 'C');
    }
    public static void hanoiTower(int num, char a, char b, char c){
        if(num == 1){
            System.out.println("第一个盘从" + a + "->" + c);
        } else{
            /*
            如果我们有n>=2个情况，我们总是可以看做是两个盘
            1.最下边的一个盘
            2.上面的所有盘
             */
            //1.先把最上面的所有盘A->B,移动过程会使用到c
            hanoiTower(num - 1, a, c, b);
            //2.把最下边的盘A->C
            System.out.println("第" + num + "个盘从" + a + "->" + c);
            //3.把B塔的所有盘从B->C，移动过程使用到a塔
            hanoiTower(num - 1, b, a, c);
        }
    }
}

