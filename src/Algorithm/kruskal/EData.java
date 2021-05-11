package Algorithm.kruskal;

//创建一个类EData，它的对象实例就表示一条边
class EData {
    char start;//边的一个点
    char end;//边的另一个点
    int weight;//边的权值

    public EData(char start, char end, int weight){
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "EData [<" + start + ", " + end + ">= " + weight + "]\n";
    }
}
