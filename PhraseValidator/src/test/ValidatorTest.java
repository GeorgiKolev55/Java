
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ValidatorTest {

    private Validator validator ;

    @Before
    public void setUp(){
        validator=new Validator();

    }
    @Test
    public void testWithEmptyString(){
        boolean tf = validator.isValidExpression("");
        assertEquals(false,tf);
    }
    @Test(expected=NullPointerException.class)
    public void testWithNull(){
        boolean tf = validator.isValidExpression(null);

    }
    @Test
    public void testWithExample1(){

        boolean tf = validator.isValidExpression("AaBb");
        assertEquals(true,tf);
    }
    @Test
    public void testWithExample2(){

        boolean tf = validator.isValidExpression("BCAacb");
        assertEquals(true,tf);
    }
    @Test
    public  void testWithExample3(){
        boolean tf = validator.isValidExpression("BCcAab");
        assertEquals(true,tf);
    }
    @Test
    public  void testWithExample4(){
        boolean tf = validator.isValidExpression("ACABbBbaca");
        assertEquals(true,tf);
    }
    @Test
    public  void testWithExample5(){
        boolean tf = validator.isValidExpression("AabB");
        assertEquals(false,tf);
    }
    @Test
    public  void testWithExample6(){
        boolean tf = validator.isValidExpression("BCAcaB");
        assertEquals(false,tf);
    }
    @Test
    public  void testWithExample7(){
        boolean tf = validator.isValidExpression("FaCa");
        assertEquals(false,tf);
    }
    @Test
    public  void testWithExample8(){
        boolean tf = validator.isValidExpression("cCAa");
        assertEquals(false,tf);
    }
}
