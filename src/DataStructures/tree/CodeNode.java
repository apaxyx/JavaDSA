package DataStructures.tree;

public class CodeNode implements Comparable<CodeNode> {
    Byte data;
    int weight;
    CodeNode left;
    CodeNode right;
    public CodeNode(Byte data, int weight){
        this.data = data;
        this.weight = weight;
    }


    @Override
    public int compareTo(CodeNode o) {
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "CodeNode{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    public void preOrder(){
        System.out.println(this);
        if(this.left != null){
            this.left. preOrder();
        }
        if(this.right != null){
            this.right.preOrder();
        }
    }
}
