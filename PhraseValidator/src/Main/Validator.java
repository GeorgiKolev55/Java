import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    public Validator() {
    }

    public  boolean isValidExpression(String expression){

        if (expression==null){
            throw new IllegalArgumentException("Input is Null");
        }
        Pattern firstPattern = Pattern.compile("(A.*a|B.*b|C.*c)|(C.*cA.*aB.*b)|(C.*cB.*bA.*a)|(A.*aB.*bC.*c)|(B.*bC.*cA.*a)|(B.*bA.*aC.*c)|(B.*bC.*c)|(C.*cA.*a)|(C.*cB.*b)|(B.*bA.*a)|(A.*aC.*c)|(A.*aB.*b)");
        Pattern secondPattern =  Pattern.compile("[AaBbCc]+");

        Matcher matcherOne = firstPattern.matcher(expression);
        Matcher matcherTwo = secondPattern.matcher(expression);


        if (matcherOne.matches()&&matcherTwo.matches()){
           return true;
        }

        return false;
    }
}
