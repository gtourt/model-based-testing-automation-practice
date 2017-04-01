package com.company.helper;

import java.util.List;
import java.util.Random;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by krikar on 2015-02-01.
 */
public class Helper {
    private static final Logger log = LoggerFactory.getLogger(Helper.class);

    /**
     * Random number generator.
     * Will be used to create random data used for input in test.
     */
    static private final Random random = new Random(System.currentTimeMillis());

    /**
     * Timeout time in seconds used for waiting for element(s) to show up.
     */
    final static int TIMEOUT = 10;

    private static WebDriver driver = null;

    /**
     * Get random element from a list.
     *
     * @param elements List of elements.
     * @return The random element
     */
    public static WebElement getRandomElement(List<WebElement> elements) {
        return elements.get(random.nextInt(elements.size()));
    }

    /**
     * If not already created, creates the singleton webdriver object.
     *
     * @return the singleton webdriver object
     */
    public static WebDriver getDriver() {
        if (driver == null) {
            ChromeDriverManager.getInstance().setup();
            driver = new ChromeDriver();
        }
        return driver;
    }

    /**
     * Will wait for a specified web element to appear. If not found
     * an exception will be thrown out.
     *
     * @param by The description of the element
     * @return The matching element if found.
     */
    public static WebElement waitForElement(By by) {
        WebDriverWait wait = new WebDriverWait(getDriver(), TIMEOUT);
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    /**
     * Will wait for a specified web element(s) to appear. If not found
     * an exception will be thrown out.
     *
     * @param by The description of the element
     * @return A list of matching element(s) if found.
     */
    public static List<WebElement> waitForElements(By by) {
        WebDriverWait wait = new WebDriverWait(getDriver(), TIMEOUT);
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    /**
     * Will wait for a specified web element to be visible. If not found
     * an exception will be thrown out.
     *
     * @param by The description of the element
     * @return The matching element if found.
     */
    public static WebElement waitForElementVisible(By by) {
        WebDriverWait wait = new WebDriverWait(getDriver(), TIMEOUT);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    /**
     * Will wait for a specified web element(s) to be visible. If not found
     * an exception will be thrown out.
     *
     * @param by The description of the element
     * @return A list of matching element(s) if found.
     */
    public static List<WebElement> waitForElementsVisible(By by) {
        WebDriverWait wait = new WebDriverWait(getDriver(), TIMEOUT);
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
    }

    /**
     * Assert path of current url.
     *
     * @param path Expected path
     */
    public static void assertPath(String path) {
        try {
            URL url = new URL(driver.getCurrentUrl());
            Assert.assertEquals(path, url.getPath());
        } catch (MalformedURLException e) {
            Assert.fail("Invalid url");
        }
    }

    public static void scrollElementIntoMiddle(WebElement element) {
        String scrollElementIntoMiddle = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
                                                    + "var elementTop = arguments[0].getBoundingClientRect().top;"
                                                    + "window.scrollBy(0, elementTop-(viewPortHeight/2));";

        ((JavascriptExecutor) driver).executeScript(scrollElementIntoMiddle, element);
    }
}
