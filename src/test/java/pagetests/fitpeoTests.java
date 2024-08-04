package pagetests;

import configs.BrowserInits;
import configs.CommonUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageclasses.revenueCalculatorPage;
import utils.parseJson;

public class fitpeoTests extends BrowserInits {

    revenueCalculatorPage revenueCalculatorPage;

    @BeforeClass(alwaysRun = true)
    public void launchApplication() throws Exception {
        revenueCalculatorPage = new revenueCalculatorPage();

    }

    // 1. Navigate to the FitPeo Homepage:

    @Test(enabled = true,priority = 1, groups = {"smoke"})
    public void NavigateToFitPeoPage() throws Exception {
        String getBrowserURL = CommonUtils.getCurrentURL();
        Assert.assertNotNull(getBrowserURL, "Browser URL is Null");
        Assert.assertTrue(getBrowserURL.contains(".fitpeo.com"), "Browser URL is not having fitpeo texts");
    }

    // 2. Navigate to the Revenue Calculator Page:

    @Test(enabled = true, priority = 2, groups = {"smoke"}, dependsOnMethods = "NavigateToFitPeoPage")
    public void NavigateToRevenueCalculator() throws Throwable {
        String loadNewURL = (String) parseJson.getLoginDetailsFromEnvJson("base_url") + (String) parseJson.getTestInputDataFromJson("revenue-calculator-path", "home_page", "test_input_data");
        BrowserInits.getDriver().get(loadNewURL);
        String getBrowserURL = CommonUtils.getCurrentURL();
        Assert.assertNotNull(getBrowserURL, "Browser URL is Null");
        Assert.assertEquals(getBrowserURL, loadNewURL, "Browser URL is not having revenue calculator texts");
    }

    // 3. Scroll Down to the Slider section:

    @Test(enabled = true, priority = 3, groups = {"smoke"}, dependsOnMethods = "NavigateToRevenueCalculator")
    public void MoveToSlider() throws Throwable {
        boolean clickOnElement = revenueCalculatorPage.moveToSlider();
        Assert.assertTrue(clickOnElement,"move to slider failed");
    }

    // 4. Adjust the Slider:

    @Test(enabled = true, priority = 4, groups = {"smoke"}, dependsOnMethods = "NavigateToRevenueCalculator")
    public void MoveSliderTo820() throws Throwable {
        boolean clickOnElement = revenueCalculatorPage.moveSlider(820);
        Assert.assertTrue(clickOnElement,"Unable to slide to 820");
    }

    @Test(enabled = true, priority = 5, groups = {"smoke"}, dependsOnMethods = "MoveSliderTo820")
    public void CheckSliderValueIs820() throws Throwable {
        String clickOnElement = revenueCalculatorPage.checkSliderValueAttribute();
        Assert.assertEquals(clickOnElement,"820", "unable to slide to 820");
    }

    @Test(enabled = true, priority = 6, groups = {"smoke"}, dependsOnMethods = "MoveSliderTo820")
    public void CheckSliderTextBoxValueIs820() throws Throwable {
        String clickOnElement = revenueCalculatorPage.getSliderTextBoxValue();
        Assert.assertEquals(clickOnElement,"820", "text box value is not 820");
    }

    // 5. Update the Text Field:

    @Test(enabled = true, priority = 7, groups = {"smoke"}, dependsOnMethods = "NavigateToRevenueCalculator")
    public void ClearTextBoxAndEnterNewValue() throws Throwable {
        boolean clickOnElement = revenueCalculatorPage.isSliderTextBoxClicked();
        boolean clearText = revenueCalculatorPage.clearEnteredText();
        boolean enterText = revenueCalculatorPage.sendTextToElement("560");
        Assert.assertTrue(clickOnElement,"click on text box failed");
        Assert.assertTrue(clearText,"clear text failed");
        Assert.assertTrue(enterText,"entering new text failed");
    }

    // 6. Validate Slider Value:

    @Test(enabled = true, priority = 8, groups = {"smoke"}, dependsOnMethods = "ClearTextBoxAndEnterNewValue")
    public void CheckSliderValueIs560() throws Throwable {
        String clickOnElement = revenueCalculatorPage.checkSliderValueAttribute();
        Assert.assertEquals(clickOnElement,"560", "slider value is not 560");
    }

    // 7. Select CPT Codes:

    @Test(enabled = true, priority = 9, groups = {"smoke"}, dependsOnMethods = "NavigateToRevenueCalculator")
    public void ClickOnFirstCPT() throws Throwable {

        boolean requiredTextStatus = false;
        int i;

        for (i = 1; i <= revenueCalculatorPage.getLengthOfCPTCodes(); i++) {
            String CPTCode = revenueCalculatorPage.getCPTCodesText(i);
            requiredTextStatus = CPTCode.equalsIgnoreCase("CPT-99091");

            if (requiredTextStatus == true) {
                boolean clickOnElement = revenueCalculatorPage.clickOnRequiredCheckBox(i);
                Assert.assertTrue(clickOnElement, "Unable to Click on Option from displayed cpt List");
                break;
            } else {
            }
        }
        Assert.assertTrue(requiredTextStatus, "cpt Text is not available in the cpt List");
    }

    @Test(enabled = true, priority = 10, groups = {"smoke"}, dependsOnMethods = "NavigateToRevenueCalculator")
    public void ClickOnSecondCPT() throws Throwable {

        boolean requiredTextStatus = false;
        int i;

        for (i = 1; i <= revenueCalculatorPage.getLengthOfCPTCodes(); i++) {
            String CPTCode = revenueCalculatorPage.getCPTCodesText(i);
            requiredTextStatus = CPTCode.equalsIgnoreCase("CPT-99453");

            if (requiredTextStatus == true) {
                boolean clickOnElement = revenueCalculatorPage.clickOnRequiredCheckBox(i);
                Assert.assertTrue(clickOnElement, "Unable to Click on Option from displayed cpt List");
                break;
            } else {
            }
        }
        Assert.assertTrue(requiredTextStatus, "cpt Text is not available in the cpt List");
    }

    @Test(enabled = true, priority = 11, groups = {"smoke"}, dependsOnMethods = "NavigateToRevenueCalculator")
    public void ClickOnThirdCPT() throws Throwable {

        boolean requiredTextStatus = false;
        int i;

        for (i = 1; i <= revenueCalculatorPage.getLengthOfCPTCodes(); i++) {
            String CPTCode = revenueCalculatorPage.getCPTCodesText(i);
            requiredTextStatus = CPTCode.equalsIgnoreCase("CPT-99454");

            if (requiredTextStatus == true) {
                boolean clickOnElement = revenueCalculatorPage.clickOnRequiredCheckBox(i);
                Assert.assertTrue(clickOnElement, "Unable to Click on Option from displayed cpt List");
                break;
            } else {
            }
        }
        Assert.assertTrue(requiredTextStatus, "cpt Text is not available in the cpt List");
    }

    @Test(enabled = true, priority = 12, groups = {"smoke"}, dependsOnMethods = "NavigateToRevenueCalculator")
    public void ClickOnFourthCPT() throws Throwable {

        boolean requiredTextStatus = false;
        int i;

        for (i = 1; i <= revenueCalculatorPage.getLengthOfCPTCodes(); i++) {
            String CPTCode = revenueCalculatorPage.getCPTCodesText(i);
            requiredTextStatus = CPTCode.equalsIgnoreCase("CPT-99474");

            if (requiredTextStatus == true) {
                boolean clickOnElement = revenueCalculatorPage.clickOnRequiredCheckBox(i);
                Assert.assertTrue(clickOnElement, "Unable to Click on Option from displayed cpt List");
                break;
            } else {
            }
        }
        Assert.assertTrue(requiredTextStatus, "cpt Text is not available in the cpt List");
    }

    @Test(enabled = true, priority = 13, groups = {"smoke"}, dependsOnMethods = "NavigateToRevenueCalculator")
    public void Entering820InTextBox() throws Throwable {
        boolean clickOnElement = revenueCalculatorPage.isSliderTextBoxClicked();
        boolean clearText = revenueCalculatorPage.clearEnteredText();
        boolean enterText = revenueCalculatorPage.sendTextToElement("820");
        Assert.assertTrue(clearText,"clear text failed");
        Assert.assertTrue(enterText,"entering new text failed");
    }

    // 8. Validate Total Recurring Reimbursement

    // 9. Verify that the header displaying Total Recurring Reimbursement for
    // all Patients Per Month: shows the value $110700.

    @Test(enabled = true, priority = 14, groups = {"smoke"}, dependsOnMethods = "NavigateToRevenueCalculator")
    public void CheckingTotalReimbursementValue() throws Throwable {
        String clickOnElement = revenueCalculatorPage.getTotalReimbursementValue();
        Assert.assertEquals(clickOnElement,"$110700", "total cost is not as expected");
    }
}

