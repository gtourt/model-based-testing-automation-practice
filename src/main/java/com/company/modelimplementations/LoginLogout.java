/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.modelimplementations;

import com.company.LoginLogoutState;
import com.company.helper.Helper;
import org.graphwalker.core.machine.ExecutionContext;
import org.graphwalker.java.annotation.GraphWalker;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author vimac
 */
@GraphWalker(value = "random(edge_coverage(100))")
public class LoginLogout extends ExecutionContext implements LoginLogoutState{
    private static final Logger log = LoggerFactory.getLogger(LoginLogout.class);
    
    @Override
    public void v_Authentication (){
        Assert.assertTrue(Helper.waitForElementVisible(By.xpath(".//*[@id='center_column']/h1")).getText().contains("Authentication"));

    }
    @Override
    public void e_clickSignIn (){
        Helper.waitForElement(By.linkText("Sign in")).click();
        Helper.waitForPageToLoad();
    }
    @Override
    public void e_LoginSucceeded (){
        Helper.waitForElement(By.id("email")).sendKeys("mbt.automation.practice@gmail.com");
        Helper.waitForElement(By.id("passwd")).sendKeys("12345");
        Helper.waitForElement(By.id("SubmitLogin")).click();
        Helper.waitForPageToLoad();
        Assert.assertTrue(Helper.waitForElement(By.id("columns")).getText().contains("> My account \\n \\n \\n My account Welcome to your account. Here you can manage all of your personal information and orders.\\n Order history and details My credit slips My addresses My personal information \\n My wishlists \\n \\n Home"));
        
    }

    @Override
    public void e_LoginSucceededAfterFailure() {
        Helper.waitForElement(By.id("email")).sendKeys("mbt.automation.practice@gmail.com");
        Helper.waitForElement(By.id("passwd")).sendKeys("12345");
        Helper.waitForElement(By.id("SubmitLogin")).click();
        Helper.waitForPageToLoad();
        Assert.assertTrue(Helper.waitForElement(By.id("columns")).getText().contains("> My account \\n \\n \\n My account Welcome to your account. Here you can manage all of your personal information and orders.\\n Order history and details My credit slips My addresses My personal information \\n My wishlists \\n \\n Home"));
    }

    @Override
    public void v_ErrorPage() {
        Assert.assertTrue(Helper.waitForElementVisible(By.xpath(".//*[@id='center_column']/h1")).getText().contains("Authentication"));
        Assert.assertTrue(Helper.waitForElement(By.cssSelector("ol > li")).getText().contains("Authentication failed."));
    }

    @Override
    public void v_MyAccount() {
        Assert.assertTrue(Helper.waitForElementVisible(By.cssSelector("h1.page-heading")).getText().contains("My account"));
        Assert.assertTrue(Helper.waitForElement(By.id("columns")).getText().contains("> My account \\n \\n \\n My account Welcome to your account. Here you can manage all of your personal information and orders.\\n Order history and details My credit slips My addresses My personal information \\n My wishlists \\n \\n Home"));


    }

    @Override
    public void e_LoginFailed() {
        Helper.waitForElement(By.id("email")).sendKeys("vimac2610@gmail.com");
        Helper.waitForElement(By.id("passwd")).sendKeys("12345");
        Helper.waitForElement(By.id("SubmitLogin")).click();
        Assert.assertTrue(Helper.waitForElement(By.cssSelector("ol > li")).getText().contains("Authentication failed."));
    }

    @Override
    public void e_LoginFailedAgain() {
        Helper.waitForElement(By.id("email")).sendKeys("vimac.go1@gmail.com");
        Helper.waitForElement(By.id("passwd")).sendKeys("12345");
        Helper.waitForElement(By.id("SubmitLogin")).click();
        Assert.assertTrue(Helper.waitForElement(By.cssSelector("ol > li")).getText().contains("Authentication failed."));

    }

    @Override
    public void e_Logout() {
        Helper.waitForElement(By.xpath("//a[@title='Log me out']")).click();
        Helper.waitForPageToLoad();
    }

    @Override
    public void v_HomePage() {
        Helper.assertPath("/index.php");
    }
    
}
