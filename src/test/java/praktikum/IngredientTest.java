package praktikum;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class IngredientTest {
    private final IngredientType type;
    private final String name;
    private final float price;

    public IngredientTest( IngredientType type, String name, float price ){
        this.type = type;
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][] {
                { IngredientType.SAUCE, "Кетчуп", 20.55f },
                { null, "", 0f },
                { IngredientType.FILLING, "Колбаса", -20.9f },
                { IngredientType.FILLING, "Колбаса вкусная вкусная вкусная вкусная вкусная вкусная вкусная вкусная вкусная", 20.55889999f }
        };
    }

    @Test
    public void getNameTest()  {
        Ingredient ingredient = new Ingredient(this.type, this.name, this.price);
        String actual = ingredient.getName();
        Assert.assertEquals(this.name, actual);
    }

    @Test
    public void getPriceTest()  {
        Ingredient ingredient = new Ingredient(this.type, this.name, this.price);
        float actual = ingredient.getPrice();
        Assert.assertEquals(this.price, actual, 0.0f);
    }

    @Test
    public void getTypeTest()  {
        Ingredient ingredient = new Ingredient(this.type, this.name, this.price);
        IngredientType actual = ingredient.getType();
        Assert.assertEquals(this.type, actual);
    }
}
