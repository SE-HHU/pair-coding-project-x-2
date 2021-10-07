public class Fraction {
    int up;
    int down;
    public Fraction(int a, int b){
        this.up=a;
        this.down=b;
    }
    public String GetFraction(){
        int judge=1;
        if (this.down<0){
            this.down= -this.down;
            judge *= -1;
        }
        if (this.up<0){
            this.up= -this.up;
            judge *= -1;
        }
        if (this.up<this.down){
            this.Simplify();
            if (judge==1)
            return this.up+"/"+this.down;
            else return "-"+this.up+"/"+this.down;
        }
        else if(this.down<this.up){
            this.Simplify();
            int c=this.up/this.down;
            this.up = this.up % this.down;
            if (this.down==1){
                if (judge==1)
                    return String.valueOf(c);
                else
                    return "-"+c;
            }
            else{
                if (judge==1)
                    return c+"'"+this.up+"/"+this.down;
                else
                    return "-"+c+"'"+this.up+"/"+this.down;
            }
        }
        else{
            if (judge==1)
            return "1";
            else
                return "-1";
        }
    }
    //分数的化简
    public void Simplify(){
        int judge=1;
        if (this.down<0){
            this.down= -this.down;
            judge *= -1;
        }
        if (this.up<0){
            this.up= -this.up;
            judge *= -1;
        }

        int x = this.up;
        int y = this.down;
        int r=find(x,y);
        if (judge==1)
            this.up=x/r;
        else
            this.up=-x/r;
        this.down=y/r;
    }
    //寻找最大公约数
    public int find(int x,int y){
        if (y==0)
            return x;
        int r = x % y;
        return find(y,r);
    }

    public static void main(String[] args) {

    }
}

