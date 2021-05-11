package DataStructures.hashtab;

import java.util.Scanner;

public class HashTabDemo {
    public static void main(String[] args) {
        HashTab hashTab = new HashTab(7);

        String key = "";
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("add: 添加雇员");
            System.out.println("list: 显示雇员");
            System.out.println("find: 查找雇员");
            System.out.println("exit: 退出系统");

            key = scanner.next();
            switch(key){
                case "add":
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();
                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("请输入要查找的id");
                    id = scanner.nextInt();
                    hashTab.findEmpById(id);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                 default:
                     System.out.println("命令有误，请重试");
                     break;
            }
        }
    }
}

//雇员信息类，结点类
class Emp{
    public int id;
    public String name;
    public Emp next;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

//雇员链表
class EmpLinkedList{
    private Emp head;

    //add方法
    public void add(Emp emp) {
        if (head == null) {
            head = emp;
            return;
        }

        Emp curEmp = head;
        while (true) {
            if (curEmp.next == null) {
                break;
            }
            curEmp = curEmp.next;
        }
        curEmp.next = emp;
    }

    //show所有
    public void list(int no){
        if(head == null){
            System.out.println("第" + (no + 1) + " 链表为空");
            return;
        }
        System.out.print("第" + (no + 1) + "链表的信息为");
        Emp curEmp = head;//辅助节点用于遍历
        while(true){
            System.out.printf("=> id=%d name=%s\t", curEmp.id,curEmp.name);
            if(curEmp.next == null){
                break;
            }
            curEmp = curEmp.next;
        }
        System.out.println();
    }

    //查找
    public Emp findEmpById(int id){
        if(head == null){
            System.out.println("链表为空");
            return null;
        }
        Emp curEmp = head;//辅助节点
        while(true){
            if(curEmp.id == id){
                break;
            }
            if(curEmp.next == null){
                curEmp = null;
                break;
            }
            curEmp = curEmp.next;
        }
        return curEmp;
    }
}

//哈希表
//应该维护一个链表数组，注意初始化，否则nullpointerexception
class HashTab{
    private EmpLinkedList[] empLinkedListArray;
    private int size;

    //初始化链表数组
    public HashTab(int size){
        this.size = size;
        empLinkedListArray = new EmpLinkedList[size];
        for(int i = 0; i < size; i++){
            empLinkedListArray[i] = new EmpLinkedList();
        }
    }

    public void add(Emp emp){
        int empLinkedListNO = hashFun(emp.id);//调用散列函数，确定应该插在哪条链表上
        empLinkedListArray[empLinkedListNO].add(emp);
    }

    //遍历每一条链表上的每一个
    public void list(){
        for(int i = 0; i < size; i++){
            empLinkedListArray[i].list(i);
        }
    }

    public void findEmpById(int id){
        int empLinkedListNO = hashFun(id);
        Emp emp = empLinkedListArray[empLinkedListNO].findEmpById(id);
        if(emp != null){
            System.out.printf("在第%d条链表中找到 雇员 id = %d\n", (empLinkedListNO + 1), id);
        } else{
            System.out.println("在哈希表中，没有让工厂发该雇员");
        }
    }

    //散列函数
    //这里选择一个按链表数组长度取模的值
    public int hashFun(int id){
        return id % size;
    }
}

