public class FractionalNumberCalculator {
    public static void printCalculationResult(String equation) {
        // DO NOT change the skeleton code.
        // You can add codes anywhere you want.
        String[] sEquation = equation.split(" ");
        String operator = sEquation[1];

        String[] sNum1 = sEquation[0].split("/");
        String[] sNum2 = sEquation[2].split("/");

        FractionalNumber num1 = new FractionalNumber(sNum1);

        FractionalNumber num2 = new FractionalNumber(sNum2);

        FractionalNumber result = null;
        switch(operator){
            case "+":
                result = addition(num1, num2);
                break;

            case "-":
                result = subtract(num1, num2);
                break;

            case "*":
                result = multiply(num1, num2);
                break;

            case "/":
                result = divide(num1, num2);
                break;

            default:

        }

        result.print();
    }

    public static FractionalNumber addition(FractionalNumber num1, FractionalNumber num2){
        int denominator = num1.getDenominator() * num2.getDenominator();
        int numerator = num1.getNumerator() * num2.getDenominator() + num2.getNumerator() * num1.getDenominator();
        return new FractionalNumber(numerator, denominator);
    }

    public static FractionalNumber subtract(FractionalNumber num1, FractionalNumber num2){
        int denominator = num1.getDenominator() * num2.getDenominator();
        int numerator = num1.getNumerator() * num2.getDenominator() - num2.getNumerator() * num1.getDenominator();
        return new FractionalNumber(numerator, denominator);
    }

    public static FractionalNumber multiply(FractionalNumber num1, FractionalNumber num2){
        int denominator = num1.getDenominator() * num2.getDenominator();
        int numerator = num1.getNumerator() * num2.getNumerator();

        return new FractionalNumber(numerator, denominator);
    }

    public static FractionalNumber divide(FractionalNumber num1, FractionalNumber num2){
        int denominator = num1.getDenominator() * num2.getNumerator();
        int numerator = num1.getNumerator() * num2.getDenominator();

        return new FractionalNumber(numerator, denominator);
    }

}

class FractionalNumber {
    private String sign = "+";
    private int numerator;
    private int denominator;

    FractionalNumber(int numerator, int denominator){
        this.numerator = numerator;
        this.denominator = denominator;
    }

    FractionalNumber(String[] sNum){
        this.numerator = Integer.parseInt(sNum[0]);
        if(sNum.length == 1){
            this.denominator = 1;
        } else{
            this.denominator = Integer.parseInt(sNum[1]);
        }
    }

    public int getNumerator(){
        return this.numerator;
    }

    public int getDenominator(){
        return this.denominator;
    }

    public void print(){
        this.checkSign();
        this.reduce();
        if(this.sign == "-") System.out.print("-");
        System.out.print(this.numerator);
        if(this.denominator != 1){
            System.out.print("/" + this.denominator);
        }
        System.out.println("");
    }

    public void reduce(){
        int num1 = denominator;
        int num2 = numerator;
        int gcd = 1;

        while(true) {
            if (num1 > num2) {
                num1 = num1 % num2;
                if(num1 == 0){
                    gcd = num2;
                    break;
                }
            } else if (num1 < num2) {
                num2 = num2 % num1;
                if(num2 == 0){
                    gcd = num1;
                    break;
                }
            } else {
                gcd = num1;
                break;
            }
        }
        this.numerator = this.numerator / gcd;
        this.denominator = this.denominator / gcd;
    }

    public void checkSign(){
        if(this.numerator < 0 && this.denominator < 0){
            this.numerator = -1 * denominator;
            this.denominator = -1 * denominator;
        } else if(this.numerator < 0 && this.denominator > 0){
            this.numerator = -1 * this.numerator;
            this.sign = "-";
        } else if(this.numerator > 0 && this.denominator < 0){
            this.denominator = -1 * this.denominator;
            this.sign = "-";
        } else{
            return;
        }
    }

}