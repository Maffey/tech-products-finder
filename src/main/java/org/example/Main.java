package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.products_framework.Browser;
import org.example.products_framework.ProductCategoryPage;
import org.example.products_framework.ProductItem;
import org.example.products_framework.ProductsPage;

import java.util.ArrayList;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

        ProductsPage smartwatchesPage = new ProductsPage(ProductCategoryPage.SMARTWATCHES);
        smartwatchesPage.goTo();

        // Find all smartwatches and gather information about them
        ArrayList<ProductItem> listOfSmartwatches = smartwatchesPage.getProductItems();
        logger.info("Reading smartwatch data successful.");

        CsvRepository csvRepo = new CsvRepository("csv_output/smartwatches_list.csv");
        csvRepo.saveProductsList(listOfSmartwatches);
        // csvRepo.saveStringsList(csvRows, header);
        // csvRepo.print();

        Browser.close();
    }

}
