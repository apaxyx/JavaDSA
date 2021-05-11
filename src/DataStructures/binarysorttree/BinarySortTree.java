package DataStructures.binarysorttree;

public class BinarySortTree {
    private Node root;

    public Node getRoot() {
        return root;
    }

    public void add(Node node){
        if(root == null){
            root = node;
        }else{
            root.add(node);
        }
    }

    public Node search(int value){
        if(root == null){
            return null;
        }else{
            return root.search(value);
        }
    }

    public Node searchParent(int value){
        if(root == null){
            return null;
        }else{
            return root.searchParent(value);
        }
    }

    /*
    返回以的以node为根结点的二叉排序树的最小结点的值
    删除node为根结点的二叉排序树的最小结点
     */
    public int delRightTreeMin(Node node){
        Node target = node;
        //循环查找左子节点，就会找到最小值
        while(target.left != null){
            target = target.left;
        }
        //这里target就指向了最小结点
        //删除最小结点
        delNode(target.value);
        return target.value;
    }

    public void delNode(int value){
        if(root == null){
            return;
        }
        else{
            Node targetNode = search(value);
            if(targetNode == null){
                return;
            }
            if(root.left == null && root.right == null){
                root = null;
                return;
            }
            Node parent = searchParent(value);
            //---------------------1.如果要删除的结点是叶子结点---------------------
            if(targetNode.left == null && targetNode.right == null){
                if(parent.left != null && parent.left.value == value){
                    parent.left = null;
                } else if(parent.right != null && parent.right.value == value){
                    parent.right = null;
                }
            }
            //---------------------2.删除有两棵子树的节点---------------------
            else if(targetNode.left != null && targetNode.right != null){
                int minVal = delRightTreeMin(targetNode.right);
                targetNode.value = minVal;
            }
            //---------------------3.删除只有一棵子树的结点---------------------
            // （这个else先于上一个else编写，因为可以用排除法确定只有一棵子树的情况）
            else{
                //如果要删除的结点有左子结点
                if(targetNode.left != null){
                    if(parent != null){
                        //如果targetNode是parent的左子结点
                        if(parent.left.value == value){
                            parent.left = targetNode.left;
                        }else{
                            //targetNode是parent的右子结点
                            parent.right = targetNode.left;
                        }
                    } else{
                        //考虑只有两个结点的特殊情况，此时的第一个结点是root，它的parent结点是null
                        //所以直接将root置为此时树的第二个结点
                        root = targetNode.left;
                    }
                }
                //如果要删除的结点有右子结点
                else{
                    if(parent != null){
                        //如果targetNode是parent的左子结点
                        if(parent.left.value == value){
                            parent.left = targetNode.right;
                        }else{
                            //如果targetNode是parent的右子结点
                            parent.right = targetNode.right;
                        }
                    }else{
                        //考虑只有两个结点的特殊情况，此时的第一个结点是root，它的parent结点是null
                        //所以直接将root置为此时树的第二个结点
                        root = targetNode.right;
                    }
                }
            }
        }
    }

    public void infixOrder(){
        if(root != null){
            root.infixOrder();
        }else{
            System.out.println("二叉排序树为空，不能遍历");
        }
    }
}
