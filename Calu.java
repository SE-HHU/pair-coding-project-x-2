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
                    if (one.compareTo(two)<0){
                        this.one=two;
                        this.two=one;
                    }
                    else {
                        this.one=one;
                        this.two=two;
                    }
                this.symbol = symbol;
            }
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
