package com.utils;

import java.time.Duration;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.base.BaseClass;

public class SeleniumWaitUtils {

	public WebElement doWaitForElement(final By element, WebDriver driver) {

		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofMillis(15L)).ignoring(NoSuchElementException.class);

		WebElement ele = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(element);
			}
		});
		return ele;
	}
	
	//Explicit waits

	public WebElement waitForElementVisisbility(WebDriver driver, By element) {
		WebElement result = new WebDriverWait(driver, Duration.ofSeconds(30))
		        .until(ExpectedConditions.elementToBeClickable(element));
		return result;
	}
	
	public boolean waitForTextPresentInElementLocated(WebDriver driver, By element,String text) {
		
		Boolean result = new WebDriverWait(driver, Duration.ofSeconds(30))
		        .until(ExpectedConditions.textToBePresentInElementLocated(element, text));
		return result;
		
	}


}
