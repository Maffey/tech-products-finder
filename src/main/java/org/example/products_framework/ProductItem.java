package org.example.products_framework;

import com.opencsv.bean.CsvIgnore;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.StaleElementReferenceException;

public class ProductItem {

    @CsvIgnore
    private static final String NO_RATING = "Ten produkt czeka";
    private final String name;
    private final float price;
    private final float rating;

    public ProductItem(WebElement productElement) {
        this.name = getProductName(productElement);
        this.price = getProductPrice(productElement);
        this.rating = getProductRating(productElement);
    }

    private static String getProductName(WebElement productItemElement) {
        WebElement productName = productItemElement.findElement(
                By.className(Browser.selectorsRepository.getProperty(
                        "xkom.products_list.product_item.title.class")));
        return retryGettingElementTitle(productName);
    }

    private static float getProductPrice(WebElement productItemElement) {
        WebElement productPrice = productItemElement.findElement(
                By.cssSelector(Browser.selectorsRepository.getProperty(
                        "xkom.products_list.product_item.price.css")));
        String smartwatchPrice = retryGettingElementText(productPrice);

        // Remove currency information from string
        smartwatchPrice = smartwatchPrice.substring(0, smartwatchPrice.length() - 3);
        // Remove whitespace number separators
        smartwatchPrice = smartwatchPrice.replaceAll(" ", "");
        // Replace value delimiter
        smartwatchPrice = changeDelimiter(smartwatchPrice);
        return Float.parseFloat(smartwatchPrice);
    }

    private static float getProductRating(WebElement productItemElement) {
        WebElement productRating = productItemElement.findElement(
                By.cssSelector(Browser.selectorsRepository.getProperty(
                        "xkom.products_list.product_item.rating.css")));
        String smartwatchRating = retryGettingElementTitle(productRating);

        // If there's no rating yet, set it as 0.0
        if (smartwatchRating.contains(NO_RATING)) {
            smartwatchRating = "0.0";
        } else {
            // Remove description of rating
            smartwatchRating = smartwatchRating.substring(16);
            smartwatchRating = changeDelimiter(smartwatchRating);
        }
        return Float.parseFloat(smartwatchRating);
    }

    private static String changeDelimiter(String value) {
        return value.replace(",", ".");
    }

    private static String retryGettingElementTitle(WebElement elementOfProduct) {
        String result = "";
        int attempts = 0;
        while(attempts < 2) {
            try {
                result = elementOfProduct.getAttribute("title");
                break;
            } catch(StaleElementReferenceException e) {
                Browser.logger.debug("StaleElementReferenceException has been caught.");
            }
            attempts++;
        }
        return result;
    }

    private static String retryGettingElementText(WebElement elementOfProduct) {
        String result = "";
        int attempts = 0;
        while(attempts < 2) {
            try {
                result = elementOfProduct.getText();
                break;
            } catch(StaleElementReferenceException e) {
                Browser.logger.debug("StaleElementReferenceException has been caught.");
            }
            attempts++;
        }
        return result;
    }

    public String print() {
        String printData = "Title: " + this.name + " | Price: " + this.price + " | Rating: " + this.rating;
        System.out.println(printData);
        return printData;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public float getRating() {
        return rating;
    }
}
