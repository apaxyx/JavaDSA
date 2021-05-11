package DataStructures.queue;
//数组实现环型队列
/*发现一个问题：就是加数取数几次后
会出现arr[3]，也就是数组最后一个元素被用到的情况*/

//数组实现队列
public class CircleArray {
    private int maxSize;//最大容量
    private int front;//指向队列的第一个元素，front = 0
    private int rear;//指向队列最后一个元素的后一个位置，rear = 0
    private int[] arr;//存放数据

    public CircleArray(int arrMaxSize){
        maxSize = arrMaxSize;
        arr = new int[maxSize];
    }

    //判断满, 特殊实现, 不容易理解
    public boolean isFull(){
        return (rear + 1) % maxSize == front;
    }

    //判断空
    public boolean isEmpty(){
        return rear == front;
    }

    //添加数据
    public void addQueue(int n){
        if(isFull()){
            System.out.println("队列满，不能加入数据");
            return;
        }

        arr[rear] = n;
        //rear后移，考虑取模
        //当rear大于数组最后一位的小标时，因为取模运算而指向0
        //因为rear = maxSize - 1，rear = (maxSize -1 + 1) % maxSize = 0
        //从而指向第一个元素，也就是下标为0的数组元素
        rear = (rear + 1) % maxSize;
    }

    //出队列
    public int getQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列空，不能取数据");
        }
        int value = arr[front];
        //后移取模
        front = (front + 1) % maxSize;
        return value;
    }

    //求出当前队列有效数据的个数
    public int size(){
        return (rear + maxSize - front) % maxSize;
    }
    //显示队列所有数据
    public void showQueue(){
        if(isEmpty()){
            System.out.println("队列空的，没有数据");
            return;
        }
        //从front开始遍历，遍历size()个元素
        for(int i = front; i < front + size(); i++){
            System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
        }
    }
    public int headQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列空，不能取数据");
        }
        return arr[front];
    }
}
