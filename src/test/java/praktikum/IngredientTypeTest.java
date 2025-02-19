package praktikum;

import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class IngredientTypeTest {
    private final int INGREDIENT_TYPE_TEST = 2;
    private final String INGREDIENT_TYPE_SAUCE = "SAUCE";
    private final String INGREDIENT_TYPE_FILLING = "FILLING";
    private SoftAssertions softAssertions;

    @Before
    public void startUp(){
        softAssertions = new SoftAssertions();
    }

    @Test
    public void ingredientTypeSizeTest(){
        Assert.assertEquals(INGREDIENT_TYPE_TEST, IngredientType.values().length);
    }

    @Test
    public void ingredientTypeValuesTest() {
        IngredientType integrationTypeSauce = IngredientType.valueOf(INGREDIENT_TYPE_SAUCE);
        IngredientType integrationTypeFilling = IngredientType.valueOf(INGREDIENT_TYPE_FILLING);
        softAssertions.assertThat(integrationTypeSauce.toString())
                .as(String.format("Проверяем значение %s", INGREDIENT_TYPE_SAUCE))
                .isEqualTo(INGREDIENT_TYPE_SAUCE);
        softAssertions.assertThat(integrationTypeFilling.toString())
                .as(String.format("Проверяем значение %s", INGREDIENT_TYPE_FILLING))
                .isEqualTo(INGREDIENT_TYPE_FILLING);
        softAssertions.assertAll();
    }
}
