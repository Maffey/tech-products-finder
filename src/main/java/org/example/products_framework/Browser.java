package org.example.products_framework;

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
    private static final String SELENIUM_OBJECT_REPOSITORY_FILE = "\\selenium_object_repository.properties";
    public final static Properties selectorsRepository = loadObjectRepository();
    public static WebDriver driver = new FirefoxDriver();

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
