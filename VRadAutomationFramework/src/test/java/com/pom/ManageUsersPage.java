package com.pom;

import org.openqa.selenium.By;

public class ManageUsersPage {
	
	public By PaginationRowsDropdown() {
		return By.id("pagination-rows");
	}
	
	public By selectRowsPerPage() {
		return By.cssSelector("#pagination-menu-list>li[data-value=\"20\"]");
	}
	
	public By firstRowInUsersTable(){
		return By.cssSelector("[data-testid=\"MUIDataTableBodyRow-0\"]");
	}

	public By AllRowsInTableDisplayed() {
	return By.xpath("//tr[starts-with(@data-testid, \"MUIDataTableBodyRow\")]");
	}
	
	public By NextPageArrow() {
	return By.cssSelector("#pagination-next");
	}
	
	public By EditIconSymbol() {
	 return By.cssSelector("[data-testid=\"EditIcon\"]");
	}
	
	public By DeleteIconSymbol() {
	return By.cssSelector("[data-testid=\"DeleteOutlineIcon\"]");
	}
	
	public By CrossSymbol() {
	return By.cssSelector(".MuiButtonBase-root.MuiIconButton-root.MuiIconButton-colorDark.MuiIconButton-sizeMedium.css-gfip7b>svg[data-testid=\"CloseIcon\"]");
	}
}
