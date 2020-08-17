package ZNKW;

import jdk.nashorn.internal.runtime.regexp.joni.ScanEnvironment;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

/*
@Home:Lenovo
@author:REN
@program:java_spring_boot
@date:2020-08-16 17:21:02
@description:
*/
public class T3 {
    public static void main(String[] args) {
        /*输入一个链表，按链表从尾到头的顺序返回一个ArrayList。*/
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        String[] arr = str.split(",");

        LinkedList<String> linkedList = new LinkedList<>();
        ArrayList<String> arrayList = new ArrayList<>();

        for(int a =0;a<arr.length;a++){
            linkedList.add(arr[a]);
        }
        for(int b =linkedList.size()-1;b>=0;b--){
            arrayList.add(linkedList.get(b));
        }

        System.out.println("linkedList Is:"+linkedList);
        System.out.println("ArrayList is :"+arrayList);
        sc.close();



    }
}
