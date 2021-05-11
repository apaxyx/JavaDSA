package DataStructures.binarysorttree;
/*
二叉排序树：BST(Binary Sort(Search) Tree) 对于二叉排序树的任何一个非叶子
节点，要求左子节点的值比当前节点的值小，右子节点的值比当前节点的大
特别说明：如果有相同的值，可以将该节点放在左子节点或右子节点

二叉排序树的删除
三种情况：
1.删除叶子节点
2.删除只有一颗子树的节点
3.删除有两颗子树的节点

对删除结点的各种情况的思路分析：
第一种情况
删除叶子节点
思路
1.需求先去找到要删除的结点 targetNode
2.找到targetNode的父结点
3.确定targetNode是parent的左子结点还是右子结点
4.根据前面的情况来对应删除
左子结点 parent.left = null
右子结点 parent.right = null

第二种情况：删除只有一棵子树的节点
思路
1.需求行动找到要删除的结点 targetNode
2.找到targetNode的父结点parent
3.确定targetNode的子结点是左子结点还是右子结点
4.targetNode 是parent的左子结点还是右子结点
5.如果targetNode有左子结点
    5.1如果targetNode是parent的左子结点
        parent.left = targetNode.left;
    5.2如果targetNode是parent的右子结点
        parent.right = targetNode.left;
6.如果targetNode有右子结点
    6.1如果targetNode是parent的左子结点
        parent.left = targetNode.right;
    6.2如果targetNode是parent的右子结点
        parent.right = targetNode.right;

第三种情况：删除有两棵子树的节点
思路
1.需求先去找到要删除的结点 targetNode
2.找到targetNode的父结点parent
3.从targetNode的右子树找到最小的结点
4.用一个临时变量，将最小结点的值保存temp = xxx
5.删除该最小结点
6.targetNode.value = temp

 */
public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        BinarySortTree binarySortTree = new BinarySortTree();
        //循环的添加结点到二叉排序树
        for(int i = 0; i< arr.length; i++) {
            binarySortTree.add(new Node(arr[i]));
        }

        //中序遍历二叉排序树
        System.out.println("中序遍历二叉排序树~");
        binarySortTree.infixOrder(); // 1, 3, 5, 7, 9, 10, 12

        //中序遍历二叉排序树
        System.out.println("中序遍历二叉排序树~");
        binarySortTree.infixOrder(); // 1, 3, 5, 7, 9, 10, 12

        //测试一下删除叶子结点


        binarySortTree.delNode(12);


        binarySortTree.delNode(5);
        binarySortTree.delNode(10);
        binarySortTree.delNode(2);
        binarySortTree.delNode(3);

        binarySortTree.delNode(9);
        binarySortTree.delNode(1);
        binarySortTree.delNode(7);


        System.out.println("root=" + binarySortTree.getRoot());


        System.out.println("删除结点后");
        binarySortTree.infixOrder();

    }
}
