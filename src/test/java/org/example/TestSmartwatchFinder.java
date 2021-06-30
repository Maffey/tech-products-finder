package org.example;

import org.example.products_framework.Browser;
import org.example.products_framework.ProductCategoryPage;
import org.example.products_framework.ProductItem;
import org.example.products_framework.ProductsPage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.io.File;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class TestSmartwatchFinder {

    private static final String TEST_SAVE_FILE = ".\\src\\test\\test_files\\test_saving_objects.csv";
    private static final String[] HEADER = {"NAME", "PRICE", "RATING"};
    private static ProductItem smartwatch;
    private static ArrayList<ProductItem> smartwatchItemElements;

    @BeforeAll
    static void setUpDriver() {
        ProductsPage smartwatchesPage = new ProductsPage(ProductCategoryPage.SMARTWATCHES);
        smartwatchesPage.goTo();

        smartwatchItemElements = smartwatchesPage.getSmartwatches();
    }

    @AfterAll
    static void tearResources() {
        Browser.close();
        File file = new File(TEST_SAVE_FILE);
        if (file.delete()) {
            System.out.println("File deleted successfully");
        } else {
            System.out.println("Failed to delete the file");
        }
    }

    @ParameterizedTest
    @EnumSource(ProductTestData.class)
    void canFindSmartwatchTitle(ProductTestData testData) {
        smartwatch = smartwatchItemElements.get(testData.getIndex());
        String smartwatchName = smartwatch.getName();
        assertThat(smartwatchName).isEqualTo(testData.getName());
    }

    @ParameterizedTest
    @EnumSource(ProductTestData.class)
    void canFindSmartwatchPrice(ProductTestData testData) {
        smartwatch = smartwatchItemElements.get(testData.getIndex());
        float smartwatchPrice = smartwatch.getPrice();
        assertThat(smartwatchPrice).isEqualTo(testData.getPrice());
    }

    @ParameterizedTest
    @EnumSource(ProductTestData.class)
    void canFindRating(ProductTestData testData) {
        smartwatch = smartwatchItemElements.get(testData.getIndex());
        float smartwatchRating = smartwatch.getRating();
        assertThat(smartwatchRating).isEqualTo(testData.getRating());
    }

    @Test
    void productItemPrintsCorrectInfo() {
        smartwatch = smartwatchItemElements.get(0);
        String printData = smartwatch.print();
        assertThat(printData).isEqualTo("Title: Smartband Xiaomi Mi Band 5 czarny | Price: 119.0 | Rating: 5.5");
    }

    @Test
    void saveObjectRowsToCsvFile() {
        CsvRepository csvRepo = new CsvRepository(TEST_SAVE_FILE);
        ArrayList<String[]> listOfRows = new ArrayList<>();
        listOfRows.add(HEADER);
        for (ProductItem smartwatch : smartwatchItemElements) {
            listOfRows.add(new String[]{smartwatch.getName(),
                    String.valueOf(smartwatch.getPrice()),
                    String.valueOf(smartwatch.getRating())});
        }
        csvRepo.saveProductsList(smartwatchItemElements);
        ArrayList<String[]> csvContents = csvRepo.read();
        assertThat(listOfRows).hasSameElementsAs(csvContents);
    }

}
