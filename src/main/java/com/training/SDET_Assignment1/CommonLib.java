package com.training.SDET_Assignment1;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.Proxy.ProxyType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class CommonLib {
	
	//---------------------------------------------------------------------------------------------------
	public static Proxy getProxy() throws Exception{
		Proxy oProxy= new Proxy();
		
		String sProxyString=String.format("%s:%d", AutomationConstance.sProxyHostName, AutomationConstance.iProxyPort);
		oProxy.setProxyType(ProxyType.MANUAL);
		oProxy.setHttpProxy(sProxyString);
		oProxy.setSslProxy(sProxyString);
		
		return oProxy;
	}
	
	//---------------------------------------------------------------------------------------------------
	
	public static DesiredCapabilities getCapability() throws Exception{
		
		DesiredCapabilities oCapability= new DesiredCapabilities();
		oCapability.setJavascriptEnabled(true);
		oCapability.setCapability("proxy", getProxy());
		return oCapability;
	}
	
	
	//---------------------------------------------------------------------------------------------------
	public static InternetExplorerOptions getIEOptions() throws Exception {
		InternetExplorerOptions oIEOptions = new InternetExplorerOptions();
		oIEOptions.merge(getCapability());
		oIEOptions.ignoreZoomSettings();
		oIEOptions.introduceFlakinessByIgnoringSecurityDomains();
		
		return oIEOptions;
	}
	
	
	
	//---------------------------------------------------------------------------------------------------
	
	public static ChromeOptions getChromeOptions() throws Exception {
		ChromeOptions oChromeOptions = new 	ChromeOptions();
		oChromeOptions.merge(getCapability());
		return oChromeOptions;
	}
	
	
	//---------------------------------------------------------------------------------------------------
	
	public static FirefoxOptions getFirefoxOpions() throws Exception{
		FirefoxOptions oFirefoxOptions = new FirefoxOptions();
		oFirefoxOptions.merge(getCapability());
		return oFirefoxOptions;
		
	}
	
	
	//---------------------------------------------------------------------------------------------------
	public static int getBrowserId (String sBrowserName) throws Exception
	{
		if(sBrowserName.equalsIgnoreCase("ie")) 
		{
			return 1;
				};
		if(sBrowserName.equalsIgnoreCase("firefox")) return 2;
		if(sBrowserName.equalsIgnoreCase("chrome")) return 3;
		if(sBrowserName.equalsIgnoreCase("htmlunit")) return 4;
		
		return -1;
	}
	
	//---------------------------------------------------------------------------------------------------
	
	public static WebDriver getDriver( String sBrowserName) throws Exception
	{
		
		WebDriver oDriver=null;
		
		switch(getBrowserId (sBrowserName))
		{
			case 1: 
				
				System.setProperty("webdriver.ie.driver", AutomationConstance.sIEDriverPath);
				oDriver =new InternetExplorerDriver(getIEOptions());
				break;
		
			case 2: 
			
				System.setProperty("webdriver.gecko.driver", AutomationConstance.sGeckoDriverPath);
				oDriver =new FirefoxDriver (getFirefoxOpions());
				break;
			case 3: 
			
				
				System.setProperty("webdriver.chrome.driver", AutomationConstance.sChromeDriverPath);
				oDriver = new ChromeDriver (getChromeOptions());
				break;
			case 4: 
			
				DesiredCapabilities oCap= getCapability();
				oCap.setBrowserName("htmlunit");
				oDriver=new HtmlUnitDriver(oCap);
				break;
				
			default: 
				throw new Exception("Unknown Browser");
		}
		
		
		if(getBrowserId(sBrowserName) != 4)
		{
			oDriver.manage().window().maximize();
		}
		
		oDriver.manage().deleteAllCookies();
		oDriver.manage().timeouts().pageLoadTimeout(AutomationConstance.lngpageLoadTimeout, TimeUnit.SECONDS);
		oDriver.manage().timeouts().implicitlyWait(AutomationConstance.lngImplicitWaitTimeout, TimeUnit.SECONDS);
		
		return oDriver;

	}
	
	public static WebDriver getRemoteDriver(String sHubUrl, String sBrowserName) throws Exception
	{
		WebDriver oDriver;
		DesiredCapabilities oCapability = getCapability();
		
		switch (getBrowserId(sBrowserName)) 
		{
			case 1:
				oCapability.setBrowserName("internet explorer");
				break;

			case 2:
				oCapability.setBrowserName("firefox");
				break;
				
			case 3:
				oCapability.setBrowserName("chrome");
				break;
				
			case 4:
				oCapability.setBrowserName("htmlunit");
				
			default:
				throw new Exception("Unknown browsername = " + sBrowserName +
							"  Valid names are: ie,firefox,chrome,htmlunit");
		}
		
		oCapability.setPlatform(Platform.WINDOWS);
		
		oDriver = new RemoteWebDriver(new URL(sHubUrl), oCapability);
		
		if (getBrowserId(sBrowserName) != 4)
		{
			oDriver.manage().window().maximize();
		}
		
		
		oDriver.manage().deleteAllCookies();
		oDriver.manage().timeouts().pageLoadTimeout(AutomationConstance.lngpageLoadTimeout, TimeUnit.SECONDS);
		oDriver.manage().timeouts().implicitlyWait(AutomationConstance.lngImplicitWaitTimeout, TimeUnit.SECONDS);

		return oDriver;
	}
	

	
}