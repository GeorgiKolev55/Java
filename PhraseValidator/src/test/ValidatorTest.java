
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
        boolean f = validator.isValidExpression("");
        assertFalse(f);
    }
    @Test(expected=IllegalArgumentException.class)
    public void testWithNull(){
        validator.isValidExpression(null);

    }
    @Test
    public void testWithTrueExpression1(){

        boolean t = validator.isValidExpression("AaBb");
        assertTrue(t);
    }
    @Test
    public void testWithTrueExpression2(){

        boolean t = validator.isValidExpression("BCAacb");
        assertTrue(t);
    }
    @Test
    public  void testWithTrueExpression3(){
        boolean t = validator.isValidExpression("BCcAab");
        assertTrue(t);
    }
    @Test
    public  void testWithTrueExpression4(){
        boolean t = validator.isValidExpression("ACABbBbaca");
        assertTrue(t);
    }
    @Test
    public  void testWithFalseExpression1(){
        boolean f = validator.isValidExpression("AabB");
        assertFalse(f);
    }
    @Test
    public  void testWithFalseExpression2(){
        boolean f = validator.isValidExpression("BCAcaB");
        assertFalse(f);
    }
    @Test
    public  void testWithFalseExpression3(){
        boolean f = validator.isValidExpression("AFaCa");
        assertFalse(f);
    }
    @Test
    public  void testWithFalseExpression4(){
        boolean f = validator.isValidExpression("Afa");
        assertFalse(f);
    }

}
