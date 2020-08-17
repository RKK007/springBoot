package ZNKW;

import java.util.Random;

/*
@Home:Lenovo
@author:REN
@program:java_spring_boot
@date:2020-08-17 10:57:38
@description:
*/
public class T4 {
//   如果 n = 6
    /*
    数组中某些数字是重复的，但不知道有几个数字是重复的。
    也不知道每个数字重复几次。
    请找出数组中任意一个重复的数字。
    例如，如果输入长度为8的数组{0,10,2,20,4,1,9,1}，那么对应的输出是第一个重复的数字1。*/

    public static void main(String[] args) {
        int[] arr = {10,10,20,20,4,1,9,1};
        int tep = 0;
        int dq = 0;
        int i = 0;
        Random random = new Random();
        for(int a=arr.length-1;a>=0;a--){
            tep = arr[a];
            for(int b = arr.length-1;b>=0;b--){
                if(a==b){
                    break;
                }else if(tep==arr[b]){
                    i = random.nextInt(2);
                    System.err.println("当前重复为："+arr[b]);
                    dq = arr[b];
                }
            }
        }
        System.out.println("第一个重复的数字为："+dq);
    }
}
