package DataStructures.tree;

//线索化完全二叉树
//n个节点的完全二叉树有n + 1个空指针
//让左空指针指向某种遍历的前一个节点，让右空指针指向某种遍历的下一个节点
//就是线索化过程

public class ThreadedBinaryTree {
    private HeroNode root;
    private HeroNode pre = null;

    public void threadedNodes(){
        this.threadedNodes(root);
    }

    public void threadedList(){
        //定义一个变量存储当前遍历的结点，从root开始
        HeroNode node = root;

        //中序遍历，所以LeftType == 0就去找左边的
        while(node != null) {
            while (node.getLeftType() == 0) {
                node = node.getLeft();
            }

            //中序遍历，打印结点
            System.out.println(node);

            //RightType == 1就直接找右就是下一个，然后打印
            while (node.getRightType() == 1) {
                node = node.getRight();
                System.out.println(node);
            }
            //中序遍历，先左再自己再右，所以重置为右
            node = node.getRight();
        }
    }


    //以中序遍历为例
    public void threadedNodes(HeroNode node){
        if(node == null){
            return;
        }
        //中序遍历先线索化左子树
        threadedNodes(node.getLeft());

        //中序遍历线索化当前节点

        //处理前驱结点
        if(node.getLeft() == null){
            node.setLeft(pre);
            node.setLeftType(1);
        }

        //处理后置结点
        if(pre != null && pre.getRight() == null){
            pre.setRight(node);
            pre.setRightType(1);
        }
        //每处理一个结点后，让当前结点是下一个结点的前驱结点
        pre = node;

        //线索化右子树
        threadedNodes(node.getRight());
    }



    public void setRoot(HeroNode root) {
        this.root = root;
    }

    //删除
    public void delNode(int no) {
        if(root != null){
            if(root.getNo() == no){
                root = null;
            }else{
                root.delNode(no);
            }
        }else{
            System.out.println("空树，不能删除");
        }
    }

    //前序遍历
    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //后序遍历
    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //前序遍历方式查找
    public HeroNode proOrderSearch(int no) {
        if (root != null) {
            return root.preOrderSearch(no);
        } else {
            return null;
        }
    }

    //中序遍历方式查找
    public HeroNode infixOrderSearch(int no) {
        if (root != null) {
            return root.infixOrderSearch(no);
        } else {
            return null;
        }
    }

    //后序遍历方式查找
    public HeroNode postOrderSearch(int no) {
        if (root != null) {
            return root.postOrderSearch(no);
        } else {
            return null;
        }
    }

}
