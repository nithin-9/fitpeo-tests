package configs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.*;
import utils.parseJson;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class BrowserInits {
	protected static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	private static BrowserInits instance = new BrowserInits();

	public static BrowserInits getInstance() {
		return instance;
	}

	public static WebDriver getDriver() {
		return driver.get();
	}

	@BeforeSuite(alwaysRun = true)
	public void initiateSuite(ITestContext context){
		System.setProperty("org.uncommons.reportng.title", "fitpeo");

		System.setProperty("org.freemarker.loggerLibrary", "none");
		Reporter.log("Before Suite Started at [" +CommonUtils.getSimpleTimeDate()+"]"+ "<br>");
		System.out.println("Before Suite Started at [" +CommonUtils.getSimpleTimeDate()+"]");
		System.out.println("Started Test Suite name: "+context.getCurrentXmlTest().getSuite().getName());
		Reporter.log("Started Test Suite name: "+context.getCurrentXmlTest().getSuite().getName()+ "<br>");
		//ExtentTestManager.createSuite(context.getCurrentXmlTest().getSuite().getName());
	}

	@BeforeTest(alwaysRun = true)
	@Parameters({ "BROWSER", "ENVIRONMENT", })
	public WebDriver initBrowserInstance(String browser, String environment) throws Throwable {
		Thread.sleep(5000); //hard wait before starting tests - required as parallel test closure delta

		if (browser.equalsIgnoreCase("NA")){
			Reporter.log("API Testing " + "<br>");

		}else {
			try {
				if (browser.equalsIgnoreCase("CHROME")) {
					ChromeOptions chromeOptions = new ChromeOptions();
					chromeOptions.addArguments("--window-size=1920,1080");
					var downloadDir = Paths.get("src/test/resources/download_test_files").toAbsolutePath().toString();
					var prefs = new HashMap<String, Object>();
					prefs.put("download.default_directory", downloadDir); // Bypass default download directory in Chrome
					prefs.put("safebrowsing.enabled", "false");
					chromeOptions.setExperimentalOption("prefs", prefs);

					driver.set(new ChromeDriver(chromeOptions));
					driver.get().manage().window().maximize();
				} else if (browser.equalsIgnoreCase("FIREFOX")) {
					int randomNum = ThreadLocalRandom.current().nextInt(1000, 10000 + 1);
					Thread.sleep(randomNum);
					driver.set(new FirefoxDriver());
					driver.get().manage().window().maximize();
				}
				String url = null;


				url = (String) parseJson.getLoginDetailsFromEnvJson("base_url");
				driver.get().get(url);
				driver.get().manage().timeouts().pageLoadTimeout(180, TimeUnit.SECONDS);

			} catch (Exception e) {
				Reporter.log("Init Browser Instance Failed at Before Test " + "<br>");
				e.printStackTrace();
			}

			return driver.get();
		}

        return null;
    }

	@AfterTest(alwaysRun = true)
	@Parameters({ "BROWSER" })
	public void closeBrowser(String browser) {
		Reporter.log("Closing the "
				+ Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("BROWSER")
				+ " Browser on Thread ID " + Thread.currentThread().getId() + "<br>");
		if (browser.equalsIgnoreCase("NA")){
			Reporter.log("API Testing " + "<br>");

		}else {
			try {
				driver.get().close();
				Reporter.log("Closing the Current Driver Session Completed at After Test" + "<br>");
				Thread.sleep(500);
				driver.remove();
			} catch (Exception e) {
				Reporter.log("Closing the Current Driver Session Failed at After Test" + "<br>");
				e.printStackTrace();
				Thread.currentThread().interrupt();
			}
		}

	}

	@AfterSuite(alwaysRun = true)
	public void quitDriver(ITestContext context) {
		Reporter.log("Trying to Quit the Whole Driver Session at After Suite" + "<br>");
		try {
			//driver.get().quit(); // Quitting the Whole Driver Session.
			Reporter.log("Quitting the Whole Driver Session Completed at After Suite" + "<br>");
		} catch (Exception e) {
			Reporter.log("Quitting the Whole Driver Session Failed at After Suite" + "<br>");
		}
		Reporter.log("After Suite Completed at [" +CommonUtils.getSimpleTimeDate()+"]"+ "<br>");
		System.out.println("After Suite Completed at [" +CommonUtils.getSimpleTimeDate()+"]");
		System.out.println("Completed Test Suite name: "+context.getCurrentXmlTest().getSuite().getName());
		Reporter.log("Completed Test Suite name: "+context.getCurrentXmlTest().getSuite().getName()+ "<br>");
	}

}
