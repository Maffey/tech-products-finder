import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Properties;

import static org.assertj.core.api.Assertions.assertThat;

// TODO: Refactor later
public class TestSmartwatchFinder {

    private static WebElement smartwatchItem;
    private static List<WebElement> smartwatchItemElements;
    private static Properties seleniumObjectRepository;
    private static WebDriver driver;

    @BeforeAll
    static void setUpDriver() {
        seleniumObjectRepository = Main.loadObjectRepository();
        driver = Main.configureWebDriver();
        driver.get(Main.XKOM_SMARTWATCHES_URL);

        smartwatchItemElements = driver.findElements(
                By.cssSelector(seleniumObjectRepository.getProperty(
                        "xkom.products_list.product_item.css")));
    }

    @AfterAll
    static void tearDownDriver() {
        driver.close();
    }

    @Test
    void canFindSmartwatchTitleOfFirstElement() {
        smartwatchItem = smartwatchItemElements.get(0);
        String smartwatchTitle = Main.getSmartwatchName(smartwatchItem, seleniumObjectRepository);
        assertThat(smartwatchTitle).isEqualTo("Smartband Xiaomi Mi Band 5 czarny");
    }

    @Test
    void canFindSmartwatchPriceOfFirstElement() {
        smartwatchItem = smartwatchItemElements.get(0);
        String smartwatchPrice = Main.getSmartwatchPrice(smartwatchItem, seleniumObjectRepository);
        assertThat(smartwatchPrice).isEqualTo("119,00");
    }

    @Test
    void canFindRatingOfFirstElement() {
        smartwatchItem = smartwatchItemElements.get(0);
        String smartwatchRating = Main.getSmartwatchRating(smartwatchItem, seleniumObjectRepository);
        assertThat(smartwatchRating).isEqualTo("5,5");
    }

    @Test
    void canFindSmartwatchTitleOfThirdElement() {
        smartwatchItem = smartwatchItemElements.get(2);
        String smartwatchTitle = Main.getSmartwatchName(smartwatchItem, seleniumObjectRepository);
        assertThat(smartwatchTitle).isEqualTo("Smartband Xiaomi Mi Band 6");
    }

    @Test
    void canFindSmartwatchPriceOfThirdElement() {
        smartwatchItem = smartwatchItemElements.get(2);
        String smartwatchPrice = Main.getSmartwatchPrice(smartwatchItem, seleniumObjectRepository);
        assertThat(smartwatchPrice).isEqualTo("179,00");
    }

    @Test
    void canFindRatingOfThirdElement() {
        smartwatchItem = smartwatchItemElements.get(2);
        String smartwatchRating = Main.getSmartwatchRating(smartwatchItem, seleniumObjectRepository);
        assertThat(smartwatchRating).isEqualTo("5,8");
    }

    @Test
    void productObjectHasCorrectTitle() {
        smartwatchItem = smartwatchItemElements.get(0);
        ProductItem product = new ProductItem(smartwatchItem);
        assertThat(product.getName()).isEqualTo("Smartband Xiaomi Mi Band 5 czarny");
    }

    @Test
    void productObjectHasCorrectPricing() {
        smartwatchItem = smartwatchItemElements.get(0);
        ProductItem product = new ProductItem(smartwatchItem);
        assertThat(product.getPrice()).isEqualTo(119.0f);
    }

    @Test
    void productObjectHasCorrectRating() {
        smartwatchItem = smartwatchItemElements.get(0);
        ProductItem product = new ProductItem(smartwatchItem);
        assertThat(product.getRating()).isEqualTo(5.5f);
    }

    @Test
    void productItemPrintsCorrectInfo() {
        smartwatchItem = smartwatchItemElements.get(0);
        ProductItem product = new ProductItem(smartwatchItem);
        String printData = product.print();
        assertThat(printData).isEqualTo("Title: Smartband Xiaomi Mi Band 5 czarny | Price: 119.0 | Rating: 5.5");
    }

}
