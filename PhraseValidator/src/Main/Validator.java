import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    public Validator() {
    }

    public  boolean isValidExpression(String expression){

        if (expression==null){throw new IllegalArgumentException("Input is Null");}
        Pattern pattern = Pattern.compile("(A.*a|B.*b|C.*c)|(A.*aB.*b)|(C.*cA.*aB.*b)|(C.*cB.*bA.*a)|(A.*aB.*bC.*c)|(B.*bC.*cA.*a)|(B.*bA.*aC.*c)|(B.*bC.*c)|(C.*cA.*a)|(C.*cB.*b)|(B.*bA.*a)");
        Pattern pattern1 = Pattern.compile("[AaBbCc]+");
        Matcher matcher = pattern.matcher(expression);
        Matcher matcher1 = pattern1.matcher(expression);
        if (matcher.matches()==true&&matcher1.matches()){
           return true;
        }
        return false;
    }
}
