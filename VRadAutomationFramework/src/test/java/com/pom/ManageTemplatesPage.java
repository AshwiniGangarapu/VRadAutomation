package com.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;



public class ManageTemplatesPage {
	
	public By createNewTemplateButton(WebDriver driver) {
		return By.xpath("//button[text()='Create New Template']");
	}
	public By mainTitle(WebDriver driver) {
		return By.xpath("//h6[@class='MuiTypography-root MuiTypography-subtitle1 css-g72m4a']");
	}	
	
	public By subTitle(WebDriver driver) {	
		return By.cssSelector(".MuiGrid-root.MuiGrid-item.MuiGrid-grid-xs-12.css-12nqbl7>form>h6");
	}
	
	public By templateName(WebDriver driver) {	
		return By.id("TemplateName");
	}
	
	public By templateMsg(WebDriver driver) {	
		return By.id("TemplateMessage");
	}
	
	public By previewMsg(WebDriver driver) {	
		return By.cssSelector(".MuiOutlinedInput-input.MuiInputBase-input.Mui-disabled.MuiInputBase-inputMultiline.css-xin0tr");
	}
	
	public By saveButton(WebDriver driver) {	
	return By.cssSelector(".MuiButton-root.MuiButton-contained.MuiButton-containedPrimary.MuiButton-sizeMedium.MuiButton-containedSizeMedium.MuiButton-disableElevation.MuiButton-fullWidth.MuiButtonBase-root.css-1gvj3vj");
	}
	public By tableTemplates(WebDriver driver) {
		return By.xpath("//table[@class='MuiTable-root tss-900muf-MUIDataTable-tableRoot css-1owb465']");
	}
	
	public By tableRows(WebDriver driver) {
		return By.xpath("//tbody[@class='MuiTableBody-root css-1xnox0e']//tr[starts-with(@id,'MUIDataTableBodyRow-003972941049492751')]");
	}
	public By editIcon(WebDriver driver) {	
	return By.cssSelector("[data-testid=\"EditIcon\"]");
	}
	
	public By deleteIcon(WebDriver driver) {	
		return By.cssSelector("[data-testid=\"DeleteOutlineIcon\"]");
		}
	
	public By viewIcon(WebDriver driver) {	
		return By.cssSelector("[data-testid=\"VisibilityOutlinedIcon\"]");
		}
	
	public By templateCrossSymbol(WebDriver driver) {
		return By.cssSelector(".MuiButtonBase-root.MuiIconButton-root.MuiIconButton-colorDark.MuiIconButton-sizeMedium.css-gfip7b>svg[data-testid=\"CloseIcon\"]");
		}
		
		

}
