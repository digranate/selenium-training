package tests;

import app.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by dinara.trifanova on 3/24/2017.
 */
@RunWith(Parameterized.class)
public class AddToCartTest extends BaseTest {

    @Parameterized.Parameter
    public int numberOfProductsToAdd;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { 3 },{4}  });
    }

    @Test
    public void cartTest() {
        app.addAndRemoveProductsToCart(numberOfProductsToAdd);
    }

}
