import java.util.LinkedList;

public class Repeat {
    public Repeat(){}
    //还原后缀表达式
    public static String Restore(LinkedList<String> list1){
        LinkedList<Object> stack = new LinkedList<>();
        //遍历后缀表达式,数字入栈，遇到符号出栈两个数字做相应计算
        for (Object o : list1) {
            String find = (String) o;
            if (find.equals("+") || find.equals("-") || find.equals("*") || find.equals("÷")) {
                String one = (String) stack.pop();
                String two = (String) stack.pop();
                switch (find) {
                    case "+" -> {
                        Calu a=new Calu(one,two,'+');
//                        CaluList.add(a);
                        stack.push(a.toString());
                    }
                    case "-" ->{
                        Calu a=new Calu(two,one,'-');
//                        CaluList.add(a);
                        stack.push(a.toString());
                    }
                    case "*" ->{
                        Calu a=new Calu(one,two,'*');
//                        CaluList.add(a);
                        stack.push(a.toString());
                    }
                    case "÷" ->{
                        Calu a=new Calu(two,one,'÷');
//                        CaluList.add(a);
                        stack.push(a.toString());
                    }
                }
            }
            else {
                stack.push(find);
            }
        }
        return (String) stack.pop();
    }
    //判断题目是否与链表中的题目有重复
    public static boolean IfRepeat(String a,LinkedList<String> store){
        boolean b=false;
        if (!store.isEmpty()){
            for (String s : store) {
                if (a.equals(s)) {
                    b = true;
                    break;
                }
            }
        }
        return  b;
    }
}
