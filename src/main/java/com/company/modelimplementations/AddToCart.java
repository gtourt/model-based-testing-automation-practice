package com.company.modelimplementations;


import com.company.AddToCartState;
import com.company.helper.Helper;
import java.util.List;
import org.graphwalker.core.machine.ExecutionContext;
import org.graphwalker.java.annotation.GraphWalker;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implements the model (and interface) AddToCartState
 * The default path generator is Random Path.
 * Stop condition is 100% coverage of all edges.
 */
@GraphWalker(value = "random(edge_coverage(100))")
public class AddToCart extends ExecutionContext implements AddToCartState {

    private static final Logger log = LoggerFactory.getLogger(AddToCart.class);

    @Override
    public void v_AddToCartDialog() {
        Assert.assertTrue(Helper.waitForElementVisible(By.xpath("//div[@id='layer_cart']//div[contains(@class,'layer_cart_product')]/h2")).getText().contains("Product successfully added to your shopping cart"));
    }

    @Override
    public void e_ProceedToCheckout() {
        WebElement button = Helper.waitForElementVisible(By.xpath("//a[@title='Proceed to checkout']"));
        Helper.scrollElementIntoMiddle(button);
        button.click();
        Helper.waitForPageToLoad();
    }

    @Override
    public void e_HomePage() {
        Helper.waitForElement(By.xpath("//a[@title='Return to Home']")).click();
        Helper.waitForPageToLoad();
    }

    @Override
    public void e_AddToCart() {
        List<WebElement> products = Helper.waitForElements(By.xpath("//ul[@id='homefeatured']/li/div[@class='product-container']"));
        WebElement product = Helper.getRandomElement(products);
        WebElement button = product.findElement(By.xpath(".//a[@title='Add to cart']"));
        Actions builder = new Actions(Helper.getDriver());
        builder.moveToElement(product).moveToElement(button).click(button);
        builder.perform();
        Helper.waitForAjax();
        Helper.waitForElementVisible(By.xpath("//div[@id='layer_cart']"));
        log.info("Products in cart: " + getAttribute("productsInCart"));
    }

    @Override
    public void e_ContinueShopping() {
        WebElement button = Helper.waitForElementVisible(By.xpath("//span[@title='Continue shopping']"));
        Helper.scrollElementIntoMiddle(button);
        button.click();
    }

    @Override
    public void v_Cart() {
        Assert.assertTrue(Helper.waitForElement(By.xpath("//h1[@id='cart_title']")).getText().contains("SHOPPING-CART SUMMARY"));
    }

    @Override
    public void e_Subtract() {
        List<WebElement> buttons = Helper.waitForElements(By.xpath("//a[@title='Subtract']"));
        WebElement button = Helper.getRandomElement(buttons);
        Actions builder = new Actions(Helper.getDriver());
        builder.moveToElement(button).click(button);
        builder.perform();
        Helper.waitForAjax();
        // In case there are no products in cart.
        Helper.waitForPageToLoad();
        log.info("Products in cart: " + getAttribute("productsInCart"));
    }

    @Override
    public void e_Add() {
        List<WebElement> buttons = Helper.waitForElements(By.xpath("//a[@title='Add']"));
        WebElement button = Helper.getRandomElement(buttons);
        Actions builder = new Actions(Helper.getDriver());
        builder.moveToElement(button).click(button);
        builder.perform();
        Helper.waitForAjax();
        log.info("Products in cart: " + getAttribute("productsInCart"));
    }

    @Override
    public void v_HomePage() {
        Helper.assertPath("/index.php");
    }
}
