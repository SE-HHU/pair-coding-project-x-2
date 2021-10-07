public class Calu {
   private String one;
   private String two;
   private char symbol;

    @Override
    public String toString() {
        return
                "("+one+"" +symbol +""+ two+""+")";
    }
    public Calu(String one, String two, char symbol) {
        switch (symbol) {
            case '+', '*' -> {
//                if (!one.contains("/")&&!two.contains("/")){
//                    if (Integer.parseInt(one) >= Integer.parseInt(two)) {
//                        this.one = one;
//                        this.two = two;
//                    } else {
//                        this.one = two;
//                        this.two = one;
//                    }
//                }
//                else {
                    if (one.compareTo(two)<0){
                        this.one=two;
                        this.two=one;
                    }
                    else {
                        this.one=one;
                        this.two=two;
                    }
                this.symbol = symbol;
//                }
//                this.symbol = symbol;
            }
            //                    if (Integer.parseInt(one) >=Integer.parseInt(two)) {
            case '-', '÷' -> {
                this.one = one;
                this.two = two;
                this.symbol = symbol;
            }
        }
    }
    public String getOne() {
        return one;
    }
    public void setOne(String one) {
        this.one = one;
    }
    public String getTwo() {
        return two;
    }
    public void setTwo(String two) {
        this.two = two;
    }
    public char getSymbol() {
        return symbol;
    }
    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }
}
