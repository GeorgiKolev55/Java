
import com.company.Validator;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class ValidatorTest {
    private ArrayList<String> source;
    private Validator validator ;

    @Before
    public void setUp(){
        validator=new Validator();
        source = new ArrayList<>();
        source.add("AaBb");
        source.add("BCAacb");
        source.add("BCcAab");
        source.add("ACABbBbaca");
        source.add("AabB");
        source.add("BCAcaB");
        source.add("FaCa");
        source.add("CAa");
    }
    @Test
    public void testTheSourse(){
       Boolean[] expected={true,true,true,true,false,false,false,false};

       for (int i =0;i<source.size();i++){
           boolean curr = validator.isValidExpression(source.get(i));
         assertEquals(curr,expected[i]);
       }
    }
}
