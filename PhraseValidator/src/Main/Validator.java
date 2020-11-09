import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    public Validator() {
    }

    public  boolean isValidExpression(String expression){
        Pattern pattern = Pattern.compile("(A.*a|B.*b|C.*c)|(A.*aB.*b|C.*c)|(A.*aB.*bC.*c)|(A.*a|B.*bC.*c)|(B.*b|C.*cA.*a)");

        Matcher matcher = pattern.matcher(expression);
        if (matcher.matches()){
           return true;
        }
        return false;
    }
}
