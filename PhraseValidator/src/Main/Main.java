public class Main {

    public static void main(String[] args) {
       Validator validator = new Validator();

       String d  ="AFaBba";
       boolean tf=validator.isValidExpression(d);
       System.out.println(tf);
    }

}
