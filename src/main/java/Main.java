import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Main {

    // TODO: Create a framework layer

    private static final Logger logger = LogManager.getLogger(Main.class);
    private static final String SELENIUM_OBJECT_REPOSITORY_FILE = "\\selenium_object_repository.properties";
    protected static final String XKOM_URL = "https://www.x-kom.pl";
    protected static final String XKOM_SMARTWATCHES_URL = "https://www.x-kom.pl/g-4/c/2435-smartwatche.html";
    private static final int IMPLICIT_WAIT_TIME = 0;

    public static void main(String[] args) {
        // Loads object repository with selector variables.
        Properties objectRepo = loadObjectRepository();
        WebDriver driver = configureWebDriver();
        driver.get(XKOM_SMARTWATCHES_URL);

        // Find all smartwatches and gather information about them
        List<WebElement> smartwatchItemElements = driver.findElements(
                By.cssSelector(objectRepo.getProperty("xkom.products_list.product_item.css")));
        ArrayList<String[]> listOfSmartwatches = new ArrayList<>();
        for (WebElement smartwatchItem: smartwatchItemElements) {

            String smartwatchTitle = getSmartwatchName(smartwatchItem, objectRepo);
            String smartwatchPrice = getSmartwatchPrice(smartwatchItem, objectRepo);
            String smartwatchRating =  getSmartwatchRating(smartwatchItem, objectRepo);

            String[] csvRow = {smartwatchTitle, smartwatchPrice, smartwatchRating};
            listOfSmartwatches.add(csvRow);
        }
        logger.info("Reading smartwatch data successful.");

        String[] header = {"Smartwatch Name", "Price (PLN)", "Rating (x/6)"};
        CsvRepository csvRepo = new CsvRepository("csv_output/smartwatches_list.csv");
        csvRepo.save(listOfSmartwatches, header);
        // csvRepo.print();

        driver.close();
    }

    public static String getSmartwatchName(WebElement smartwatchItemElement, Properties seleniumObjectRepository) {
        return smartwatchItemElement.findElement(
                By.className(seleniumObjectRepository.getProperty(
                        "xkom.products_list.product_item.title.class")))
                .getAttribute("title");
    }

    public static String getSmartwatchPrice(WebElement smartwatchItemElement, Properties seleniumObjectRepository) {
        String smartwatchPrice = smartwatchItemElement.findElement(
                By.cssSelector(seleniumObjectRepository.getProperty(
                        "xkom.products_list.product_item.price.css")))
                .getText();
        return smartwatchPrice.substring(0, smartwatchPrice.length()-3);
    }

    public static String getSmartwatchRating(WebElement smartwatchItemElement, Properties seleniumObjectRepository) {
        WebElement smartwatchRatingElement = smartwatchItemElement.findElement(
                By.cssSelector(seleniumObjectRepository.getProperty(
                        "xkom.products_list.product_item.rating.css")));
        return smartwatchRatingElement.getAttribute("title").substring(16);
    }

    @NotNull
    protected static WebDriver configureWebDriver() {
        WebDriver driver = new FirefoxDriver();
        // Allow every page to load fully in time of X seconds.
        logger.debug("IMPLICIT_WAIT_TIME set to: " + IMPLICIT_WAIT_TIME);
        if (IMPLICIT_WAIT_TIME > 0) {
            driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT_TIME, TimeUnit.SECONDS);
        }
        return driver;
    }

    protected static Properties loadObjectRepository() {
        Properties seleniumObjectRepository = new Properties();
        try {
            FileInputStream repositoryFile = new FileInputStream(System.getProperty("user.dir") + SELENIUM_OBJECT_REPOSITORY_FILE);
            seleniumObjectRepository.load(repositoryFile);
        } catch (IOException e) {
            logger.error("Incorrect repository file.");
        }
        return seleniumObjectRepository;
    }

    // Just for testing navigation.
    protected static void navigateStepByStep(WebDriver driver, Properties objectRepo) {
        // Get into "Smartfony i smartwache" tab
        WebElement smartphonesAndSmartwatchesElement = driver.findElement(
                By.cssSelector(objectRepo.getProperty("xkom.smartphones_and_smartwatches_tab.css")));
        smartphonesAndSmartwatchesElement.click();

        // Select "Inteligentne zegarki" category
        WebElement smartwatchesElement = driver.findElement(
                By.cssSelector(objectRepo.getProperty("xkom.smartphones_and_smartwatches_tab.smartwatches.css")));
        smartwatchesElement.click();
        smartwatchesElement.click();

        // Select "Smartwatche" subcategory
        WebElement smartwatchesCategoryElement = driver.findElement(
                By.cssSelector(objectRepo.getProperty("xkom.smartphones_and_smartwatches_tab.smartwatches.smartwatches_category.css")));
        smartwatchesCategoryElement.click();
    }

}
