package configs;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtils extends BrowserInits {

	public static String getCurrentURL() {
		return BrowserInits.getDriver().getCurrentUrl();
	}


	public static String getSimpleTimeDate() {
		SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yy-HH:mm:ss");
		Date date = new Date();
		return formater.format(date);
	}

	public static boolean clickOnElement(WebElement element) throws Throwable {
		int repeat = 1;
		while (repeat<=5){
			try {
				element.click();
				Reporter.log("Successfully Clicked On Element in Utils Class on Iteration[" + repeat+"]" + "<br>");
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				Reporter.log("Failed to Click On Element in Utils Class on Iteration[" + repeat+"]" + "<br>");
			}
			repeat++;
			Thread.sleep(2000);
		}
		Reporter.log("All click re-try are completed. Failed to Click On Element in Utils Class. Hence returning false" + "<br>");
		return false;
	}

	public static boolean ScrollToElementWithoutDelay(WebElement element) throws Throwable {
		int FirstLocation = element.getLocation().getY();
		((JavascriptExecutor) BrowserInits.getDriver())
				.executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(250);
		int NewLocation = element.getLocation().getY();
		while (FirstLocation != NewLocation) {
			((JavascriptExecutor) BrowserInits.getDriver())
					.executeScript("arguments[0].scrollIntoView(true);", element);
			Thread.sleep(250);
			FirstLocation = NewLocation;
			NewLocation = element.getLocation().getY();
		}
		return true;
	}

	public static boolean clearTextByBackSpace(WebElement element) {
		try {
			String elementLength = element.getAttribute("value");
			Reporter.log("Element String is " + elementLength + "<br>");
			int elementCharLength = elementLength.length();
			Reporter.log("Length of the String is " + elementCharLength + "<br>");
			for (int i = 0; i <= elementCharLength; i++) {
				element.sendKeys(Keys.BACK_SPACE);
				Reporter.log("Completed Element Clear by Backspace Key for Char Length" + i + "<br>");
			}
			Reporter.log("Element Clear by Backspace Key is [true]" + "<br>");
			return true;
		} catch (Exception e) {
			Reporter.log("Some other error while clearing by BACKSPACE. In catch block. Hence Returning false" + "<br>");
			return false;
		}
	}
}
