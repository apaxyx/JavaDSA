package DataStructures.tree;

class HuffmanNode implements Comparable<HuffmanNode>{

    int value;
    //char c;
    HuffmanNode left;
    HuffmanNode right;

    public void preOrder(){
        System.out.println(this);
        if(this.left != null){
            this.left.preOrder();
        }
        if(this.right != null){
            this.right.preOrder();
        }
    }

    public HuffmanNode(int value){
        this.value = value;
    }

    @Override
    public String toString() {
        return "HuffmanNode{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(HuffmanNode o) {
        return this.value - o.value;
    }
}

