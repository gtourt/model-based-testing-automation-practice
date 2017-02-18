package com.company.modelimplementations;


import com.company.AutomationPracticeSharedState;
import com.company.helper.Helper;
import org.graphwalker.core.machine.ExecutionContext;
import org.graphwalker.java.annotation.*;
import org.junit.Assert;
import org.openqa.selenium.By;

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
        Assert.assertTrue(Helper.WaitForElement(By.xpath("//h1[@class='cart_title']")).getText().contains("Shopping-cart summary"));
    }

    @Override
    public void e_HomePage() {
        Helper.WaitForElement(By.xpath("//a[@title='Return to Home']")).click();
    }

    @Override
    public void e_SearchProducts() {
        Helper.WaitForElement(By.xpath("//button[contains(@class,'button-search')]")).click();
    }

    @Override
    public void v_SearchProducts() {
        Assert.assertTrue(Helper.WaitForElement(By.xpath("//h1[contains(@class,'product-listing')]")).getText().contains("Search"));
    }

    @Override
    public void e_Cart() {
        Helper.WaitForElement(By.xpath("//div[@class='shopping_cart']")).click();
    }

    @Override
    public void v_HomePage() {
        Assert.assertTrue(Helper.WaitForElement(By.xpath("//ul[contains(@class,'htmlcontent-home')]")).findElements(By.tagName("li")).size() == 5);
    }

    @Override
    public void e_StartBrowser() {
        Helper.getInstance().get("http://automationpractice.com");
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
