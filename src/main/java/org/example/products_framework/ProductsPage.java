package org.example.products_framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ProductsPage {

    private final ProductCategoryPage productCategoryPage;

    public ProductsPage(ProductCategoryPage productCategoryPage) {
        this.productCategoryPage = productCategoryPage;
    }

    public void goTo() {
        Browser.goTo(productCategoryPage.getUrl());
    }

    public boolean isAt() {
        return Browser.getTitle().equals(productCategoryPage.getTitle());
    }

    public static ArrayList<ProductItem> getProductItems() {
        List<WebElement> smartwatchItemElements = Browser.driver.findElements(
                By.cssSelector(Browser.selectorsRepository.getProperty("xkom.products_list.product_item.css")));
        return productWebElementsToProductItems(smartwatchItemElements);
    }

    private static ArrayList<ProductItem> productWebElementsToProductItems(List<WebElement> productWebElements) {
        ArrayList<ProductItem> productItems = new ArrayList<>();
        for (WebElement productElement : productWebElements) {
            ProductItem product = new ProductItem(productElement);
            productItems.add(product);
        }
        return productItems;
    }

}
