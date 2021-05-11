package DataStructures.avl;

public class Node {
    int value;
    Node left;
    Node right;

    public Node(int value){
        this.value = value;
    }

    public int leftHeight(){
        if(left == null){
            return 0;
        }
        return left.height();
    }

    public int rightHeight(){
        if(right == null){
            return 0;
        }
        return right.height();
    }

    public int height(){
        return Math.max(left == null ? 0 : left.height(),
                right == null ? 0 : right.height()) + 1;
    }

    //左旋转
    private void leftRotate(){
        //创建新的结点，以当前根结点的值
        Node newNode = new Node(value);
        //把新的结点的左子树设置成当前结点的左子树
        newNode.left = left;
        //把新的结点的右子树设置成当前结点的右子树的左子树
        newNode.right = right.left;
        //把当前结点的值替换成右子结点的值
        value = right.value;
        //把当前结点的右子树设置成当前结点右子树的右子树
        right = right.right;
        //把当前结点的左子树（左子结点）设置成新的结点
        left = newNode;
    }

    //右旋转
    private void rightRotate(){
        //创建新的结点，以当前结点的值
        Node newNode = new Node(value);
        //把新节点的右子树设置为当前节点的右子树
        newNode.right = right;
        //把新节点的左子树设置为当前节点的左子树的右子树
        newNode.left = left.right;
        //把当前节点的值换为左子节点的值
        value = left.value;
        //把当前节点的左子树设置成左子树的左子树
        left = left.left;
        //把当前节点的右子树设置为新节点
        right = newNode;
    }



    // 查找要删除的结点
    public Node search(int value) {
        if (value == this.value) { // 找到就是该结点
            return this;
        } else if (value < this.value) {// 如果查找的值小于当前结点，向左子树递归查找
            // 如果左子结点为空
            if (this.left == null) {
                return null;
            }
            return this.left.search(value);
        } else { // 如果查找的值不小于当前结点，向右子树递归查找
            if (this.right == null) {
                return null;
            }
            return this.right.search(value);
        }

    }

    // 查找要删除结点的父结点
    public Node searchParent(int value) {
        // 如果当前结点就是要删除的结点的父结点，就返回
        if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
            return this;
        } else {
            // 如果查找的值小于当前结点的值, 并且当前结点的左子结点不为空
            if (value < this.value && this.left != null) {
                return this.left.searchParent(value); // 向左子树递归查找
            } else if (value >= this.value && this.right != null) {
                return this.right.searchParent(value); // 向右子树递归查找
            } else {
                return null; // 没有找到父结点
            }
        }

    }

    @Override
    public String toString() {
        return "Node [value=" + value + "]";
    }

    // 添加结点的方法
    // 递归的形式添加结点，注意需要满足二叉排序树的要求
    public void add(Node node) {
        if(node == null){
            return;
        }

        if(node.value < this.value){
            if(this.left == null){
                this.left = node;
            }else{
                this.left.add(node);
            }
        }else{
            if(this.right == null){
                this.right = node;
            }else{
                this.right.add(node);
            }
        }

        //当添加完一个结点后，如果（右子树的高度 - 左子树的高度）> 1，左旋转
        if(rightHeight() - leftHeight() > 1){
            //如果它的右子树的左子树的高度大于它的右子树的右子树的高度
            if(right != null && right.leftHeight() > right.rightHeight()){
                //先对右子结点进行右旋转
                right.rightRotate();
                //然后再对当前结点进行左旋转
                leftRotate();
            }else{
                //否则直接进行左旋转即可
                leftRotate();
            }
            return;//这里处理完不用执行下面的旋转，直接返回
        }

        //当添加完一个结点后，如果（左子树的高度 - 右子树的高度）> 1，右旋转
        if(leftHeight() - rightHeight() > 1){
            //如果它的左子树的右子树高度大于它的左子树的左子树的高度
            if(left != null && left.rightHeight() > left.leftHeight()){
                //先对当前结点的左结点（左子树）-> 左旋转
                left.leftRotate();
                //再对当前结点进行右旋转
                rightRotate();
            }else{
                //直接进行右旋转即可
                rightRotate();
            }
        }
    }

    // 中序遍历
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    //
}
