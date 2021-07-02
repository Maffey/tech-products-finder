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
        int pageCount = Integer.parseInt(args[0]);

        ProductsPage smartwatchesPage = new ProductsPage(ProductCategoryPage.SMARTWATCHES);
        smartwatchesPage.goTo();

        // Find all smartwatches and gather information about them
        ArrayList<ProductItem> listOfSmartwatches = new ArrayList<>();
        for (int i=0; i < pageCount; i++) {
            listOfSmartwatches.addAll(ProductsPage.getProductItems());
            logger.info("Reading products data successful. Page: " + smartwatchesPage.getCurrentPage());
            // Change page
            smartwatchesPage.nextPage();
        }
        logger.debug("Total products found: " + listOfSmartwatches.size());

        CsvRepository csvRepo = new CsvRepository("csv_output/smartwatches_list.csv");
        csvRepo.saveProductsList(listOfSmartwatches);
        logger.debug("Products saved to file.");

        // csvRepo.saveStringsList(csvRows, header);
        // csvRepo.print();

        Browser.close();
        logger.debug("Browser has been closed. Execution completed.");
    }

}
