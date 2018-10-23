package com.soa.webdriver.page;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Config Logins page.
 * @author krishna Rao
 */

public class ConfigLoginsPage extends HeaderPage {

	@FindBy(xpath = "//div[@id='container_Left']/div/div/div/ul/li[7]/a[text()='Config']")
	private WebElement lnkConfig;

	@FindBy(xpath = "//div[@id='container_Left']/div/div/div/ul/li[7]/a[text()='Config']/../ul/li/a[text()='Custom Styles']")
	private WebElement lnkCustomStyles;

	@FindBy(xpath = "//div[@id='container_Left']/div/div/div/ul/li[7]/a[text()='Config']/../ul/li/a[text()='Logins']")
	private WebElement lnkLogins;

	@FindBy(xpath = "//div[@id='container_Left']/div/div/div/ul/li[7]/a[text()='Config']/../ul/li/a[text()='Resources']")
	private WebElement lnkResources;

	@FindBy(id = "soa-control-cm-user-logins-form")
	private WebElement tableLogins;

	@FindBy(xpath = "//form[@id='soa-control-cm-user-logins-form']/div/ul/li/button[text()='Save']")
	private WebElement btnReset;

	@FindBy(xpath = "//form[@id='soa-control-cm-user-logins-form']/div/ul/li/button[text()='Save']")
	private WebElement btnSave;

	@FindBy(xpath = "//div[text()='Login domain configuration has been successfully updated.']/../ul/li/button[text()='OK']")
	private WebElement btnOk;

	private static final String XPATH_DOMAIN_LOGIN_ENABLE_CHECKBOX = "//form[@id='soa-control-cm-user-logins-form']/table/tbody/tr/td[1][normalize-space(text())='{Domain_Name}']/../td[3]/input[@type='checkbox']";

	private WebDriver driver;

	public ConfigLoginsPage() throws Exception {
		LOG.debug("Initializing Logins page");
		driver = driver();
	}

	/**
	 * Click on Config link From Left Menu Administration Items.
	 */
	public void clickOnConfigLink(){
		lnkConfig.click();
	}

	/**
	 * Click on Logins link From Left Menu Administration Items.
	 */
	public ConfigLoginsPage clickOnLoginsLink(){
		lnkLogins.click();
		return initPage(ConfigLoginsPage.class);
	}

	public boolean enableLogin(String domainName) throws InterruptedException {
		try{
			String xpathLoginEnableCheckBox = XPATH_DOMAIN_LOGIN_ENABLE_CHECKBOX.replace("{Domain_Name}",domainName);
			WebElement loginEnable = driver.findElement(By.xpath(xpathLoginEnableCheckBox));
			UniversalPage.isElementPresent(loginEnable);
			loginEnable.click();
			btnSave.click();
			btnOk.click();
		} catch (NoSuchElementException e) {
			LOG.debug("Element not found: \n" + e);
			return false;
		}
		return true;
	}
}

