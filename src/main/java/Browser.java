import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Browser {

    public static WebDriver driver = new FirefoxDriver();
    public final static Properties selectorsRepository = Main.loadObjectRepository();

    public static void setImplicitWaitTime(int waitTime) {
        // Allow every page to load fully in time of X seconds.
        if (waitTime > 0) {
            driver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);
        }
    }

    public static void goTo(String url) {
        driver.get(url);
    }

    public static String getTitle() {
        return driver.getTitle();
    }

    public static void close() {
        driver.close();
    }

    public static ArrayList<ProductItem> getProductItems() {
        List<WebElement> smartwatchItemElements = Browser.driver.findElements(
                By.cssSelector(Browser.selectorsRepository.getProperty("xkom.products_list.product_item.css")));
        return productWebElementsToProductItems(smartwatchItemElements);
    }

    private static ArrayList<ProductItem> productWebElementsToProductItems(List<WebElement> productWebElements) {
        ArrayList<ProductItem> productItems = new ArrayList<>();
        for (WebElement productElement: productWebElements) {
            ProductItem product = new ProductItem(productElement);
            productItems.add(product);
        }
        return productItems;
    }
}
