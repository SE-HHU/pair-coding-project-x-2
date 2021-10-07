import java.io.*;
import java.util.LinkedList;
public class Input {
    public static void main(String[] args) throws IOException {
//         File fileQ=new File(new File("").getAbsoluteFile()+"\\Exercises.txt");
//         File fileA=new File(new File("").getAbsoluteFile()+"\\Answers.txt");
//        FileRepeat(fileQ);
    }
    //批改读入的文件和答案，并返回一个存储对错的链表
    public  static LinkedList<Boolean> Correct(File fileQ,File fileA) throws IOException {
        BufferedReader Q = new BufferedReader(new FileReader(fileQ));
        BufferedReader A = new BufferedReader(new FileReader(fileA));
        String contentLine;
        LinkedList<Boolean> LB = new LinkedList<>();
        while ((contentLine = Q.readLine()) != null) {
            LinkedList<String> list = new LinkedList<>();
            for (int i = contentLine.indexOf("、")+1; i <contentLine.length() ; i++) {
                list=QToList(contentLine);
            }
            String rightAnswer = Output.Calculate(Output.Postfix(list));
            String []  answerArray= A.readLine().split("、");
            String answer=answerArray[1];
            if (rightAnswer.equals(answer)) {
                LB.add(true);
            } else {
                LB.add(false);
            }
        }
        Q.close();
        A.close();
        return LB;
    }
    //把字符串题目转化成链表形式
    public static LinkedList<String> QToList(String contentLine) throws IOException {
        LinkedList<String> list = new LinkedList<>();
        StringBuilder opponent = new StringBuilder();
        boolean judge=false;
        for (int i = contentLine.indexOf("、")+1; i <contentLine.length() ; i++) {
            String o = contentLine.charAt(i) + "";
            if (i != contentLine.length() - 1) {
                if (o.equals("+") || o.equals("-") || o.equals("*") || o.equals("÷")) {
                    if (!judge) {
                        list.add(String.valueOf(opponent));
                        list.add(o);
                        opponent = new StringBuilder();
                    } else {
                        judge = false;
                        list.add(o);
                    }
                } else if (o.equals(")")) {
                    list.add(String.valueOf(opponent));
                    list.add(o);
                    opponent = new StringBuilder();
                    judge = true;
                } else if (o.equals("("))
                    list.add(o);
                else
                    opponent.append(o);

            } else {
                if (o.equals(")")) {
                    list.add(String.valueOf(opponent));
                    list.add(")");
                } else
                    list.add(String.valueOf(opponent.append(o)));
            }
        }
        return list;
    }
    //检查文件内部题目的重复
    public static void FileRepeat(File fileQ) throws IOException {
        BufferedReader Q = new BufferedReader(new FileReader(fileQ));
        String contentLine;
        LinkedList<String> Ls=new LinkedList<>();
        LinkedList<Integer> serialList=new LinkedList<>();
        LinkedList<Integer> serialNumber=new LinkedList<>();
        int re=0;
        boolean judge=false;
        while ((contentLine = Q.readLine()) != null) {
            String[] cL=contentLine.split("、");
            Ls.add(cL[1]);
            serialNumber.add(Integer.parseInt(cL[0]));
        }
        System.out.println();
        System.out.println("RepeatDetail:");
        for (int i = 0; i < Ls.size(); i++) {
            for (int j = i+1; j < Ls.size(); j++) {
                if (Repeat.Restore(Output.Postfix(Input.QToList(Ls.get(i))))
                        .equals(Repeat.Restore(Output.Postfix(Input.QToList(Ls.get(j)))))){
                    serialList.add(j);
                    judge=true;
                }
            }
            if (judge){
                re++;
                System.out.print("("+re+")"+serialNumber.get(i)+","+Ls.get(i)+" "+"Repeat"+" ");
                for (int i1 = 0; i1 < serialList.size(); i1++) {
                    System.out.print(serialNumber.get(serialList.get(i1))+","+Ls.get(serialList.get(i1))+" ");
                }
                System.out.println();
                Ls.remove(i);
                serialNumber.remove(i);
                for (int i1 = 0; i1 < serialList.size(); i1++) {
                    serialNumber.remove(serialList.get(i1)-i1-1);
                    Ls.remove(serialList.get(i1)-i1-1);
                }
               serialList.clear();
                i=0;
                judge=false;
            }

        }
        System.out.println("Repeat:"+re);
    }
}
