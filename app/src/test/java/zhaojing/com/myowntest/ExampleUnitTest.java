package zhaojing.com.myowntest;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void test_one(){// function variable is not the same
        int i=1;
        i++;//i must be initialized
        System.out.println("i = "+i);
    }
}