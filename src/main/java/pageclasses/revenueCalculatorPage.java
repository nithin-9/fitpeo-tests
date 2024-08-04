package pageclasses;

import configs.BrowserInits;
import configs.CommonUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import java.util.List;

public class revenueCalculatorPage extends CommonUtils {

    public revenueCalculatorPage() {
        BrowserInits.getInstance();
        PageFactory.initElements(BrowserInits.getDriver(), this);
    }

    @FindBy(xpath = "//body/div/div/div[1]/div[2]/div/div/span[1]")
    WebElement slider;

    @FindBy(xpath = "//body/div/div/div[1]/div[2]/div/div/span[1]//input")
    WebElement slider_input;

    @FindBy(xpath = "(/html/body/div/div/div/div[2]//input)[2]")
    WebElement slider_text_box;

    @FindBy(xpath = "//html/body/div/div/header/div/p[4]/p")
    WebElement total_recurring_reimbursement_cost;

    @FindBy(xpath = "/html/body/div[1]/div[1]/div[1]/div[2]/div/h4")
    WebElement move_here;

    @FindBy(xpath = "//html/body/div/div/div[2]//p[1]")
    List<WebElement> cpt_codes;

    public boolean moveToSlider() throws Throwable {
        return ScrollToElementWithoutDelay(move_here);
    }

    public boolean moveSlider(int userValue) {
        try {
            WebElement slider = BrowserInits.getDriver().findElement(By.xpath("/html/body/div[1]/div[1]/div[1]/div[2]/div/div/span[1]/span[3]/input"));
            int defaultValue = Integer.parseInt(slider_input.getAttribute("value"));
            for (int i = 1; i <= userValue-defaultValue ; i++) {
                slider.sendKeys(Keys.ARROW_RIGHT);
            }
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public String checkSliderValueAttribute(){
        return slider_input.getAttribute("value");
    }

    public String getSliderTextBoxValue(){
        return slider_text_box.getAttribute("value");
    }

    public boolean isSliderTextBoxClicked(){
        try {
            slider_text_box.click();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean clearEnteredText() {
        try {
            WebElement element = BrowserInits.getDriver().findElement(By.xpath("(/html/body/div/div/div/div[2]//input)[2]"));
            clearTextByBackSpace(element);
            Reporter.log("Text Retrieved after Clear Text by BackSpace is " + clearTextByBackSpace(element) + "<br>");
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            Reporter.log("Failed to Clear Section text in page class - in catch block. Hence returning false" + "<br>");
            return false;
        }
    }

    public boolean sendTextToElement(String text) {
        try {
            WebElement element = BrowserInits.getDriver().findElement(By.xpath("(/html/body/div/div/div/div[2]//input)[2]"));

            element.sendKeys(text);
            element.sendKeys(Keys.ENTER);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public int getLengthOfCPTCodes() {
        return cpt_codes.size();
    }

    public String getCPTCodesText(int i) {
        try {
            String Value = String.valueOf(BrowserInits.getDriver().findElement(By.xpath("//html/body/div/div/div[2]/div["+i+"]/p[1]")).getText());
            return Value;
        } catch (Exception e) {
            e.printStackTrace();
            Reporter.log("Failed to show Text Empty For objects list in page class - in catch block. Hence returning null" + "<br>");
            return null;
        }
    }

    public boolean clickOnRequiredCheckBox(int i) {
        try {
            WebElement element = BrowserInits.getDriver().findElement(By.xpath("//html/body/div/div/div[2]/div["+i+"]//input"));
            Reporter.log("Data retrieved from this method is : " + element + "<br>");
            return clickOnElement(element);} catch (Throwable e) {
            e.printStackTrace();
            Reporter.log("Unable to click on selected object" + "<br>");
            return false;
        }
    }

    public String getTotalReimbursementValue(){
       return total_recurring_reimbursement_cost.getAttribute("textContent");
    }

}
