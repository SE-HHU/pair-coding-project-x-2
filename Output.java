import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class Output {
    public static void main(String[] args)throws Exception {
        Scanner in = new Scanner(System.in);
        int n1,n2,n3,n4;
        try {
            System.out.println("请输入题目数量");
             n1 = in.nextInt();
            System.out.println("请输入自然数最大数值");
             n2 = in.nextInt();
            System.out.println("请输入真分数最大数值");
            n3 = in.nextInt();
            System.out.println("请输入分母最大数值");
            n4 = in.nextInt();
        }catch (Exception e){
            System.out.println("输入数据有误！");
            return;
        }
        File file=new File(new File("").getAbsoluteFile()+"\\Exercises.txt");
        File fileAnswer=new File(new File("").getAbsoluteFile()+"\\Answers.txt");
        BufferedWriter w = new BufferedWriter(new FileWriter( file) );
        BufferedWriter o = new BufferedWriter(new FileWriter( fileAnswer) );
        int j=1;
        for (int s=0;s<n1;s++){
            //中缀表达式存放
            LinkedList<String> list = new LinkedList<>();
            //还原式存放
            LinkedList<String> RestoreList=new LinkedList<>();
            //决定数字个数
            int m = (int) (Math.random() * 3);
            //决定是否要括号
            int b0 = (int) (Math.random() * 2);
            int b1 = -100;
            int b2 = -100;
            if (b0==1){
                //决定左括号位置
                if (m!=0){
                    b1 = (int) (Math.random() * (m+1));
                    //决定括号长度
                    b2 = (int) (Math.random() * 2+1);
                }
            }
            //一次循环生成一个数字和一个符号
            for(int i=0;i<=m+1;i++){
                boolean whether = false;
                if ((b1==0&&i==0)||(b1==1&&i==1)||(b1==2&&i==2)) {
                    list.add("(");
                }
               int x ;
                String a ;
                //随机自然数还是分数
                int NumClass = (int) (Math.random() * 2);
                if (NumClass==0){
                    x = (int) (Math.random() * (n2-1)+1);
                    a=String.valueOf(x);
                }
                else{
                    int up,down;
                    int real;
                    do {
                        up = (int) (Math.random() * (n4-1)+1);
                        down = (int) (Math.random() * (n4-1)+1);
                        if (up>down){
                            int t = up;
                            up = down;
                            down = t;
                        }
                       real=up/down;
                    }while (!(real < n3));
                    Fraction simplify = new Fraction(up,down);
                    simplify.Simplify();
                    a=simplify.up+"/"+simplify.down;
                }
                list.add(a);
                //加上左括号并且排除括起整个算式的括号
                if (b1==0  && b2 == m + 1){
                    if (i==m)
                        list.add(")");
                }
                else {
                    if ((b2 == 1 && i == b1 + 1) || (b2 == 2 && i == b1 + 2))
                        list.add(")");
                    if (b1+b2>m+1){
                        if (i==m+1)
                            list.add(")");
                    }

                }
                //决定符号
                if (i!=m+1){
                    int sym = (int) (Math.random() * 4);
                    switch (sym){
                        case 0-> list.add("+");
                        case 1-> list.add("-");
                        case 2-> list.add("*");
                        case 3-> list.add("÷");
                    }
                }
            }
            //存入还原式
            String re= Repeat.Restore(Postfix(list));
            if (Repeat.IfRepeat(re,RestoreList)){
                s--;
            }
            else
            RestoreList.add(re);
//            计算结果为Calculate(Postfix(list))
            Iterator it = list.iterator();
            w.write(j+"、");
            o.write(j+"、");
            j++;
            while (it.hasNext()) {
                w.write((String) it.next());
            }
            String calculate=Calculate(Postfix(list));
            if (calculate.equals("false")){
                s--;
            }
            else
            o.write(calculate);
            o.newLine();
            w.newLine();
            System.out.println("Done");
        }
        w.close();
        o.close();
    }
    //中缀表达式转化成后缀表达式
    public static LinkedList<String> Postfix (LinkedList<String> list){
        LinkedList<String> list1=new LinkedList<>();
        Iterator it = list.iterator();
        LinkedList<Object> stack = new LinkedList<>();
        while (it.hasNext()) {
            String sym = (String) it.next();
            //乘除符号优先级高
            //加减符号入栈前要弹出优先级更高的符号加入后缀表达式
            //遍历到右括号时把括号中符号弹出加入后缀表达式
            //如果是数字直接加入后缀表达式
            switch (sym) {
                case "*", "÷" -> {
                    if (!stack.isEmpty()) {
                        String sym1 = (String) stack.getFirst();
                        if (sym1.equals("*") || sym1.equals("÷")) {
                            list1.add((String) stack.pop());
                        }
                    }
                    stack.push(sym);
                }
                case "(" -> stack.push(sym);
                case "+", "-" -> {
                    if (!stack.isEmpty()) {
                        String sym1 = (String) stack.getFirst();
                        if (sym1.equals("*") || sym1.equals("÷") || sym1.equals("+") || sym1.equals("-")) {
                            list1.add((String) stack.pop());
                        }
                    }
                    stack.push(sym);
                }
                case ")" -> {
                    String sym1;
                    do {
                        sym1 = (String) stack.pop();
                        if (!sym1.equals("("))
                            list1.add(sym1);
                    } while (!sym1.equals("("));
                }
                default -> list1.add(sym);
            }
        }
        while (!stack.isEmpty()){
            String o= (String) stack.pop();

            if (!o.equals("("))
                list1.add(o);
        }
        return list1;
    }
    //计算后缀表达式
    public static String Calculate(LinkedList<String> list1){
        LinkedList<Object> stack = new LinkedList<>();
            //创建Operation类调用方法
            Operation ope=new Operation();
            //遍历后缀表达式,数字入栈，遇到符号出栈两个数字做相应计算
            for (Object o : list1) {
                String find = (String) o;
                if (find.equals("+") || find.equals("-") || find.equals("*") || find.equals("÷")) {
                    String a = (String) stack.pop();
                    String b = (String) stack.pop();
                    switch (find) {
                        case "+" ->
                            stack.push(ope.add(a, b));
                        case "-" ->
                            stack.push(ope.sub(b, a));
                        case "*" ->
                            stack.push(ope.mul(a, b));
                        case "÷" ->
                            stack.push(ope.div(b, a));
                    }
                }
                else {
                    stack.push(find);
                }
            }
            return (String) stack.pop();
    }
}