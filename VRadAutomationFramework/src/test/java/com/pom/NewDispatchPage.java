package com.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NewDispatchPage {


	public By ExpandCollapseButton(WebDriver driver) {
		return By.xpath("//button[@class='MuiButtonBase-root MuiIconButton-root MuiIconButton-colorDark MuiIconButton-sizeMedium css-gfip7b']");
	}

	public By NewDispatchButton(WebDriver driver) {
		return By.xpath("//div[@class='MuiListItemButton-root MuiListItemButton-gutters MuiButtonBase-root css-1qun6fe']");
	}

	public By CreateDispatchRadioButton(WebDriver driver) {
		return By.xpath("//input[@class='PrivateSwitchBase-input css-1m9pwf3' and @value='CreateNewDispatch']");
	}
	
	public By TextArea(WebDriver driver) {
		return By.xpath("//textarea[@class='MuiOutlinedInput-input MuiInputBase-input MuiInputBase-inputMultiline css-xin0tr']");
	}

	public By NextButton(WebDriver driver) {
	return By.xpath("//button[@class='MuiButton-root MuiButton-contained MuiButton-containedPrimary MuiButton-sizeMedium MuiButton-containedSizeMedium MuiButton-disableElevation MuiButtonBase-root  css-w2q1i3']");
	}
	public By AddButton(WebDriver driver) {
	return By.xpath("//button[@class='MuiButton-root MuiButton-outlined MuiButton-outlinedPrimary MuiButton-sizeSmall MuiButton-outlinedSizeSmall MuiButton-disableElevation MuiButtonBase-root  css-h2ic8j']");
	}
	public By DropDownGroupOrIndividual(WebDriver driver) {
		return By.xpath("//div[@id='demo-select-small']");
	}
	public By DispatchCrossSymbol(WebDriver driver) {
		return By.cssSelector(".MuiButtonBase-root.MuiIconButton-root.MuiIconButton-colorDark.MuiIconButton-sizeMedium.css-gfip7b>svg[data-testid=\"CloseIcon\"]");
		}
	
	public By ListOfAllrecipients(WebDriver driver) {
		return By.cssSelector(".MuiList-root.MuiList-padding.css-1iaijb8>li");
	}
	
	
}
