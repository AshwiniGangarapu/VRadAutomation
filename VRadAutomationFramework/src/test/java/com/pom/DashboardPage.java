package com.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage {

	public By expandShowingListBox() {	

		return By.cssSelector("#demo-simple-select");

	}

	public By optionsShowingListBox() {

		return By.cssSelector(".MuiMenu-list>li");

	}

	public By paginationLastPage() {

		return By.xpath("//button[@aria-label='last page']");

	}

	public By summaryTable() {

		return By.xpath("//table[@class='MuiTable-root css-1y4b4fj']");

	}
	
	public By summaryTableBody() {
	return By.xpath("//table[@class='MuiTable-root css-1y4b4fj']/tbody");
	}
	
	public By summaryTableRow() {
		return By.xpath("//table[@class='MuiTable-root css-1y4b4fj']/tbody/tr");	
	}
	
	public By selectingColumnSentOn() {
		return By.xpath("//table[@class='MuiTable-root css-1y4b4fj']//tr/td[1]");
	}
	public By selectingDispatchMessagePreview() {
		return By.xpath("//table[@class='MuiTable-root css-1y4b4fj']//tr/td[3]");
	}
	
	public By actionsColumn() {
		return By.xpath("//table[@class='MuiTable-root css-1y4b4fj']//tr/td[7]");
	}
	
	public By viewIconButton() {
		//$('.MuiButtonBase-root.MuiIconButton-root.MuiIconButton-edgeEnd.MuiIconButton-sizeSmall.css-carvac>svg>path')
		//return By.cssSelector(".MuiButtonBase-root.MuiIconButton-root.MuiIconButton-edgeEnd.MuiIconButton-sizeSmall.css-carvac>svg>path");
		return By.xpath("//button[@class='MuiButtonBase-root MuiIconButton-root MuiIconButton-edgeEnd MuiIconButton-sizeSmall css-carvac']");
	}
			
}

	
	
	
	

