import jmtrace.ASMTest;
import org.junit.Test;

public class ExampleUnitTest {
    @Test
    public void testASM() {
        ASMTest.redefineHelloWorldClass();
    }
}
