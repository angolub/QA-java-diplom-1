package praktikum;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {
    @Mock
    private Bun bun;

    @Mock
    private Ingredient ingredient1;

    @Mock
    private Ingredient ingredient2;

    @Spy
    Burger spyBurger;

    private final String expectedIngredient1Name = "Колбаса";
    private final IngredientType expectedIngredient1Type = IngredientType.FILLING;
    private final float expectedIngredient1Price = (float) 165.99;

    private final String expectedIngredient2Name = "Кетчуп";
    private final IngredientType expectedIngredient2Type = IngredientType.SAUCE;
    private final float expectedIngredient2Price = (float) 19.05;

    private final String expectedBunName = "булка";
    private final float expectedBunPrice = (float) 39.99;

    private final float expectedPrice = expectedBunPrice * 2 + expectedIngredient1Price + expectedIngredient2Price;

    private void makeBurger(Burger burger) {
        Mockito.when(bun.getName()).thenReturn(expectedBunName);
        Mockito.when(bun.getPrice()).thenReturn(expectedBunPrice);

        Mockito.when(ingredient1.getName()).thenReturn(expectedIngredient1Name);
        Mockito.when(ingredient1.getType()).thenReturn(expectedIngredient1Type);
        Mockito.when(ingredient1.getPrice()).thenReturn(expectedIngredient1Price);

        Mockito.when(ingredient2.getName()).thenReturn(expectedIngredient2Name);
        Mockito.when(ingredient2.getType()).thenReturn(expectedIngredient2Type);
        Mockito.when(ingredient2.getPrice()).thenReturn(expectedIngredient2Price);

        burger.setBuns(bun);
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
    }

    @Test
    public void setBunsTest() {
        Burger burger = new Burger();
        burger.setBuns(bun);
        Assert.assertNotNull(burger.bun);
    }

    @Test
    public void addIngredientTest() {
        Burger burger = new Burger();
        burger.addIngredient(ingredient1);
        Assert.assertEquals(1, burger.ingredients.size());
        Assert.assertNotNull(burger.ingredients.get(0));
    }

    @Test
    public void removeIngredientTest() {
        Burger burger = new Burger();
        burger.addIngredient(ingredient1);
        burger.removeIngredient(0);
        Assert.assertEquals(0, burger.ingredients.size());
    }

    @Test
    public void moveIngredientTest() {
        Burger burger = new Burger();
        makeBurger(burger);

        burger.moveIngredient(0, 1);
        Assert.assertEquals(expectedIngredient2Name, burger.ingredients.get(0).getName());
        Assert.assertEquals(expectedIngredient1Name, burger.ingredients.get(1).getName());
    }

    @Test
    public void getPriceTest() {
        Burger burger = new Burger();
        makeBurger(burger);

        float actual = burger.getPrice();
        Mockito.verify(bun, Mockito.times(1)).getPrice();
        Mockito.verify(ingredient1, Mockito.times(1)).getPrice();
        Mockito.verify(ingredient2, Mockito.times(1)).getPrice();
        Assert.assertEquals(expectedPrice, actual, 0.0f);
    }

    @Test
    public void getReceiptTest() {
        makeBurger(spyBurger);

        String actual = spyBurger.getReceipt();
        String expected = String.format("(==== %s ====)%n= %s %s =%n= %s %s =%n(==== %s ====)%n%nPrice: %f%n", expectedBunName,
                expectedIngredient1Type.toString().toLowerCase(), expectedIngredient1Name,
                expectedIngredient2Type.toString().toLowerCase(), expectedIngredient2Name,
                expectedBunName, expectedPrice);

        Mockito.verify(bun, Mockito.times(2)).getName();
        Mockito.verify(ingredient1, Mockito.times(1)).getName();
        Mockito.verify(ingredient1, Mockito.times(1)).getType();
        Mockito.verify(ingredient2, Mockito.times(1)).getName();
        Mockito.verify(ingredient2, Mockito.times(1)).getType();

        Mockito.verify(spyBurger, Mockito.times(1)).getPrice();
        Assert.assertEquals(expected, actual);
    }
}
