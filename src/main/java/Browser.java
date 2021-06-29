import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Browser {

    private static final Logger logger = LogManager.getLogger(Browser.class);
    public static WebDriver driver = new FirefoxDriver();
    public final static Properties selectorsRepository = loadObjectRepository();
    private static final String SELENIUM_OBJECT_REPOSITORY_FILE = "\\selenium_object_repository.properties";

    public static void setImplicitWaitTime(int waitTime) {
        // Allow every page to load fully in time of X seconds.
        if (waitTime > 0) {
            driver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);
        }
        logger.debug("Implicit wait time set to: " + waitTime);
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

    private static Properties loadObjectRepository() {
        Properties seleniumObjectRepository = new Properties();
        try {
            FileInputStream repositoryFile = new FileInputStream(System.getProperty("user.dir") + SELENIUM_OBJECT_REPOSITORY_FILE);
            seleniumObjectRepository.load(repositoryFile);
        } catch (IOException e) {
            logger.error("No object repository found. Program will crash.");
        }
        return seleniumObjectRepository;
    }
}
