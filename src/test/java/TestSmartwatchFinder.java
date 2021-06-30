import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

// TODO: Parametrize
public class TestSmartwatchFinder {

    private static ProductItem smartwatch;
    private static ArrayList<ProductItem> smartwatchItemElements;

    @BeforeAll
    static void setUpDriver() {
        ProductsPage smartwatchesPage = new ProductsPage(ProductCategoryPage.SMARTWATCHES);
        smartwatchesPage.goTo();

        smartwatchItemElements = smartwatchesPage.getSmartwatches();
    }

    @AfterAll
    static void tearDownDriver() {
        Browser.close();
    }

    @Test
    void canFindSmartwatchTitleOfFirstElement() {
        smartwatch = smartwatchItemElements.get(0);
        String smartwatchName = smartwatch.getName();
        assertThat(smartwatchName).isEqualTo("Smartband Xiaomi Mi Band 5 czarny");
    }

    @Test
    void canFindSmartwatchPriceOfFirstElement() {
        smartwatch = smartwatchItemElements.get(0);
        float smartwatchPrice = smartwatch.getPrice();
        assertThat(smartwatchPrice).isEqualTo(119.00f);
    }

    @Test
    void canFindRatingOfFirstElement() {
        smartwatch = smartwatchItemElements.get(0);
        float smartwatchRating = smartwatch.getRating();
        assertThat(smartwatchRating).isEqualTo(5.5f);
    }

    @Test
    void canFindSmartwatchTitleOfThirdElement() {
        smartwatch = smartwatchItemElements.get(2);
        String smartwatchName = smartwatch.getName();
        assertThat(smartwatchName).isEqualTo("Smartband Xiaomi Mi Band 6");
    }

    @Test
    void canFindSmartwatchPriceOfThirdElement() {
        smartwatch = smartwatchItemElements.get(2);
        float smartwatchPrice = smartwatch.getPrice();
        assertThat(smartwatchPrice).isEqualTo(179.00f);
    }

    @Test
    void canFindRatingOfThirdElement() {
        smartwatch = smartwatchItemElements.get(2);
        float smartwatchRating = smartwatch.getRating();
        assertThat(smartwatchRating).isEqualTo(5.8f);
    }

    @Test
    void productObjectHasCorrectTitle() {
        smartwatch = smartwatchItemElements.get(0);
        assertThat(smartwatch.getName()).isEqualTo("Smartband Xiaomi Mi Band 5 czarny");
    }

    @Test
    void productObjectHasCorrectPricing() {
        smartwatch = smartwatchItemElements.get(0);
        assertThat(smartwatch.getPrice()).isEqualTo(119.0f);
    }

    @Test
    void productObjectHasCorrectRating() {
        smartwatch = smartwatchItemElements.get(0);
        assertThat(smartwatch.getRating()).isEqualTo(5.5f);
    }

    @Test
    void productItemPrintsCorrectInfo() {
        smartwatch = smartwatchItemElements.get(0);
        String printData = smartwatch.print();
        assertThat(printData).isEqualTo("Title: Smartband Xiaomi Mi Band 5 czarny | Price: 119.0 | Rating: 5.5");
    }

}
