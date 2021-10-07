public class Operation{
    public Operation (){}
    //将字符串类型的数字转化成Fraction类
    public Fraction deal(String a){
        if (a.contains("/")){
            int i=a.indexOf("/");
            StringBuilder x= new StringBuilder();
            StringBuilder y= new StringBuilder();
            StringBuilder z1= new StringBuilder();
            StringBuilder z2= new StringBuilder();
            for (int j=0;j<i;j++){
                String location=String.valueOf(a.charAt(j));
                x.append(location);
            }
            for (int k=i+1;k<a.length();k++){
                String location=String.valueOf(a.charAt(k));
                y.append(location);
            }
            int up;
            int down = Integer.parseInt(String.valueOf(y));
            if (a.contains("'")){
                int lo=a.indexOf("'");
                for (int l=0;l<lo;l++){
                    String location=String.valueOf(a.charAt(l));
                    z1.append(location);
                }
                for (int m=lo+1;m<i;m++){
                    String location=String.valueOf(a.charAt(m));
                    z2.append(location);
                }
                up=Integer.parseInt(String.valueOf(z1))*down+Integer.parseInt(String.valueOf(z2));
            }
            else
                up=Integer.parseInt(String.valueOf(x));
            return new Fraction(up,down);
        }
        else
            return new Fraction(Integer.parseInt(a),1);

    }
    //四则运算
    public String add(String a,String b){
        if (a.equals("false")||b.contains("false"))
            return "false";
        if (a.equals("0"))
            return b;
        if (b.equals("0"))
            return a;
        if (a.contains("/") || b.contains("/")) {
            Fraction A=deal(a);
            Fraction B=deal(b);
            Fraction c=new Fraction(A.up*B.down+A.down*B.up,A.down*B.down);
            return c.GetFraction();
        } else {
            int x=Integer.parseInt(a);
            int y=Integer.parseInt(b);
            return String.valueOf(x+y);
        }
    }
    public String sub(String a, String b){
        if (a.equals("false")||b.contains("false"))
            return "false";
        if (a.equals("0"))
            return "-"+b;
        if (b.equals("0"))
            return a;
        if (a.contains("/") || b.contains("/")) {
            Fraction A=deal(a);
            Fraction B=deal(b);
            Fraction c=new Fraction(A.up*B.down-A.down*B.up,A.down*B.down);
            return c.GetFraction();
        } else {
            int x=Integer.parseInt(a);
            int y=Integer.parseInt(b);
            return String.valueOf(x-y);
        }
    }
    public String mul(String a, String b){
        if (a.equals("false")||b.contains("false"))
            return "false";
        if (a.equals("0")||b.equals("0"))
            return "0";
        if (a.contains("/") || b.contains("/")) {
            Fraction A=deal(a);
            Fraction B=deal(b);
            Fraction c=new Fraction(A.up*B.up,A.down*B.down);
            return c.GetFraction();
        } else {
            int x=Integer.parseInt(a);
            int y=Integer.parseInt(b);
            return String.valueOf(x*y);
        }
    }
    public String div(String a, String b){
        if (a.equals("0"))
            return "0";
        if (b.equals("0"))
            return "false";
        Fraction A=deal(a);
        Fraction B=deal(b);
        Fraction c=new Fraction(A.up*B.down,A.down*B.up);
        return c.GetFraction();
    }
}