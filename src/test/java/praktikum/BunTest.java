package praktikum;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class BunTest {
    private final String name;
    private final float price;

    public BunTest( String name, float price ){
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters
    public static Object[][] getBunData() {
        return new Object[][] {
                { "Булка", (float) 20.55 },
                { "", (float) 0 },
                { "Булка вкусная", (float) -20.9 },
                { "Булка вкусная вкусная вкусная вкусная вкусная вкусная вкусная вкусная вкусная", (float) 20.5588 }
        };
    }

    @Test
    public void getNameTest()  {
        Bun bun = new Bun(this.name, this.price);
        String actual = bun.getName();
        Assert.assertEquals(this.name, actual);
    }

    @Test
    public void getPriceTest()  {
        Bun bun = new Bun(this.name, this.price);
        float actual = bun.getPrice();
        Assert.assertEquals(this.price, actual, 0.0f);
    }
}
