package ZNKW;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/*
@Home:Lenovo
@author:REN
@program:java_spring_boot
@date:2020-08-18 09:36:27
@description:
*/
public class T5 {
    public static void main(String[] args) throws IOException {
        InputStream is = new FileInputStream("D:\\Upload\\HQ.java");
        int len = 0;
        byte[] bytes = new byte[1024000];

        //读入100个字节长度到字节数组里，此方法将被阻塞，直到读完。
        int temp = is.read(bytes);//temp值会被累加
        System.out.println(is.read(bytes));//此时不再读取，read（）方法已经阻塞且运行完,返回-1
        System.out.println(temp);//为总字节个数，一个字符2个字节。
        System.out.println(new String(bytes,0,temp));//数组转为字符串，从下标0到末尾，并打印字符串。

    }
}

