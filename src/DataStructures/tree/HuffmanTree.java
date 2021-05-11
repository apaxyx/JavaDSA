package DataStructures.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
赫夫曼树：最优二叉树
给定n个有权值的叶子结点，构成一棵二叉树，若该树的带权路径长度wpl达到最小，
称这样的二叉树为最优二叉树，也称哈夫曼树，或赫夫曼树,或霍夫曼树
哈夫曼树是带权路径长度最短的树，权值较大的结点离根较近

路径和路径长充：在一棵树中，从一个结点往下可以达到的孩子或孙子结点之前的
通路，称为路径。
通路中分支的数目称为路径长度。
若规定根结点的层数为1，则从根结点到第L层结点的路径长度为L - 1

结点的权及带权路径长度：若将树中结点赋给一个有着某种含义的数值，则这个数值
称为该结占的权。
结点的带权路径长度为：从根结点到该结点之间的路径长度与该结点的权的乘积

树的带权路径长度，树的带权路径长度规定为所有叶子结点的带权路径长度之和，
记为WPL（weighted path length）,权值越大的结点离根结点越近的二叉树才是
最优二叉树
WPL最小的就是赫夫曼树

构成赫夫曼树的步骤：
1.从小到大进行排序，每个数据都是一个节点，每个节点可以看成是一颗最简单的
二叉树
2.取出根节点权值最小的两颗二叉树
3.组成一颗新的二叉树，该新的二叉树的根节点的权值是前面两颗二叉树根节点权值
的和
4.再将这颗新的二叉树，以根节点的权值大小再次排序，不断重复1/2/3/4的步骤，
所有的数据都被处理，就得到一棵赫夫曼树
*/
public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        HuffmanNode root = createHuffmanTree(arr);
        preOrder(root);
    }

    private static void preOrder(HuffmanNode root) {
        if(root != null){
            root.preOrder();
        }else{
            System.out.println("是空树，不能遍历");
        }
    }

    //用数组中的元素构成一棵哈夫曼树
    private static HuffmanNode createHuffmanTree(int[] arr) {
        List<HuffmanNode> nodes = new ArrayList<>();
        for (int value : arr) {
            nodes.add(new HuffmanNode(value));
        }
        //首先将数组中的元素添加到链表中

        while(nodes.size() > 1){
            //排序链表，首先重写HuffmanNode的compareTo方法
            Collections.sort(nodes);
            System.out.println("nodes = " + nodes);

            //取出头两个也就是最小的两个
            HuffmanNode leftNode = nodes.get(0);
            HuffmanNode rightNode = nodes.get(1);

            //用这两个造一个它们的父结点，权等于它们两个的权之合
            HuffmanNode parent = new HuffmanNode(leftNode.value + rightNode.value);

            //这个父结点指向这两个子节点
            parent.left = leftNode;
            parent.right = rightNode;

            //将用过的叶子结点删掉，下次重新排序后只要拿头两个，再组成新的父结点
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //新生的父结点仍然需要参与构建哈夫曼树的过程中
            nodes.add(parent);
        }
        //最后链表中只剩一下元素，它就是这棵哈夫曼树的头结点，也就是root
        //拿到这个root我们就可以前序中序后序遍历哈夫曼树了
        return nodes.get(0);
    }
}
