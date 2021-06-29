import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ProductItem {

    private final String name;
    private final float price;
    private final float rating;

    public ProductItem(WebElement productElement) {
        this.name = getProductName(productElement);
        this.price = getProductPrice(productElement);
        this.rating = getProductRating(productElement);
    }

    private static String getProductName(WebElement smartwatchItemElement) {
        return smartwatchItemElement.findElement(
                By.className(Browser.selectorsRepository.getProperty(
                        "xkom.products_list.product_item.title.class")))
                .getAttribute("title");
    }

    private static float getProductPrice(WebElement smartwatchItemElement) {
        String smartwatchPrice = smartwatchItemElement.findElement(
                By.cssSelector(Browser.selectorsRepository.getProperty(
                        "xkom.products_list.product_item.price.css")))
                .getText();
        // Remove currency information from string
        smartwatchPrice = smartwatchPrice.substring(0, smartwatchPrice.length() - 3);
        // Replace value delimiter
        smartwatchPrice = changeDelimiter(smartwatchPrice);
        return Float.parseFloat(smartwatchPrice);
    }

    private static float getProductRating(WebElement smartwatchItemElement) {
        String smartwatchRating = smartwatchItemElement.findElement(
                By.cssSelector(Browser.selectorsRepository.getProperty(
                        "xkom.products_list.product_item.rating.css"))).getAttribute("title");
        // Remove description of rating
        smartwatchRating = smartwatchRating.substring(16);
        smartwatchRating = changeDelimiter(smartwatchRating);
        return Float.parseFloat(smartwatchRating);
    }

    private static String changeDelimiter(String value) {
        return value.replace(",", ".");
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
