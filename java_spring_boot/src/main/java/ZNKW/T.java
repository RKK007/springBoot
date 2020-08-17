package ZNKW;

import java.util.Scanner;

/*
@Home:Lenovo
@author:REN
@program:java_spring_boot
@date:2020-08-16 16:32:18
@description:
二维数组操作：
在控制台输入三行字符串，每一串以逗号分开，前两行每行都代表一个数组维度，左右依次递增，上下依次递增，第三行输入一个数字，回车后将前两行存入数组，第三行用于判断
1、存入该数组
2、遍历该数组
3、判断数字是否存在于数组中
*/
public class T {
    public static void main(String[] args) {
        int[][] array = new int[4][4];
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        String str2 = sc.next();
        int str3 = Integer.parseInt(sc.next());
        String[] s1 = str.split(",");
        String[] s2 = str2.split(",");
       for (int a = 0;a<2;a++){
             for (int b = 0;b<s1.length;b++){
                 if(a==0){
                     array[a][b] = Integer.parseInt(s1[b]);
                 }
                 if(a==1){
                     array[a][b] = Integer.parseInt(s2[b]);
                 }

             }
       }


        System.out.println("输出该数组：");
       for (int a=0;a<2;a++){
           for (int b = 0;b<array.length;b++){
               System.out.print(array[a][b]);
               if(array[a][b]==str3){
                   System.out.println();
                   System.out.println("数字"+str3+"在数组中存在！");
               }
           }
           System.out.println();
       }


    }
}
