package com.company.modelimplementations;


import com.company.AutomationPracticeSharedState;
import com.company.helper.Helper;
import org.graphwalker.core.machine.ExecutionContext;
import org.graphwalker.java.annotation.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Implements the model (and interface) AutomationPracticeSharedState
 * The default path generator is Random Path.
 * Stop condition is 100% coverage of all edges.
 * The start element in the model is e_StartBrowser.
 */
@GraphWalker(value = "random(edge_coverage(100))", start = "e_StartBrowser")
public class AutomationPractice extends ExecutionContext implements AutomationPracticeSharedState {

    @Override
    public void v_Cart() {
        Assert.assertTrue(Helper.waitForElement(By.xpath("//h1[@id='cart_title']")).getText().contains("SHOPPING-CART SUMMARY"));
    }

    @Override
    public void e_HomePage() {
        Helper.waitForElement(By.xpath("//a[@title='Return to Home']")).click();
        Helper.waitForPageToLoad();
    }

    @Override
    public void e_SearchProducts() {
        Helper.waitForElement(By.xpath("//button[contains(@class,'button-search')]")).click();
        Helper.waitForPageToLoad();
    }

    @Override
    public void v_SearchProducts() {
        Pattern p = Pattern.compile("SEARCH\\s+\\d+ results have been found.");
        Matcher m = p.matcher(Helper.waitForElement(By.xpath("//h1[contains(@class,'product-listing')]")).getText());
        Assert.assertTrue(m.find());
    }

    @Override
    public void e_Cart() {
        Helper.waitForElement(By.xpath("//a[@title='View my shopping cart']")).click();
        Helper.waitForPageToLoad();
    }

    @Override
    public void v_HomePage() {
        Helper.assertPath("/index.php");
    }

    @Override
    public void e_StartBrowser() {
        Helper.getDriver().get("http://automationpractice.com");
    }

    @BeforeExecution
    public void setup() {
        System.out.println("AutomationPractice: Any setup steps happens here. " +
                "The annotation @BeforeExecution makes sure that before any elements in the " +
                "model is called, this method is called first");
    }

    @AfterExecution
    public void cleanup() {
        System.out.println("AutomationPractice: Any cleanup  steps happens here. " +
                "The annotation @AfterExecution makes sure that after the test is done, " +
                "this method is called last.");
        Helper.getDriver().quit();
    }

    @BeforeElement
    public void printBeforeElement(){
        System.out.println("Before element " + getCurrentElement().getName());
    }

    @AfterElement
    public void printAfterElement(){
        System.out.println("After element " + getCurrentElement().getName());
    }
}
