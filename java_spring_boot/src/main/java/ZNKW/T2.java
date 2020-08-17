package ZNKW;

/*
@Home:Lenovo
@author:REN
@program:java_spring_boot
@date:2020-08-16 17:19:08
@description:
*/
public class T2 {
    public static void main(String[] args) {
        /*请实现一个函数，将一个字符串中的每个空格替换成“%20”。例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。*/
        String str = "a b c d ";
        str = str.replace(" ","%20");
        System.out.println(str);
    }
}
