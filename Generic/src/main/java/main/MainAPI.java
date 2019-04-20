package main;


import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import reporting.ExtentManager;
import reporting.ExtentTestManager;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainAPI {
    public static AppiumDriver ad = null;
    public String OS = null;
    public String deviceName = null;
    public String deviceType = null;
    public String appType = null;
    public String version = null;
    public File appDirectory = null;
    public File findApp = null;
    public DesiredCapabilities cap = null;
    //Extent Report Listener
    public static ExtentReports extent;

    @BeforeSuite
    public void extentSetup(ITestContext context) {
        ExtentManager.setOutputDirectory(context);
        extent = ExtentManager.getInstance();
    }

    @BeforeMethod
    public void startExtent(Method method) {
        String className = method.getDeclaringClass().getSimpleName();
        String methodName = method.getName().toLowerCase();
        ExtentTestManager.startTest(method.getName());
        ExtentTestManager.getTest().assignCategory(className);
    }

    protected String getStackTrace(Throwable t) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        t.printStackTrace(pw);
        return sw.toString();
    }

    @AfterMethod
    public void afterEachTestMethod(ITestResult result) {
        ExtentTestManager.getTest().getTest().setStartedTime(getTime(result.getStartMillis()));
        ExtentTestManager.getTest().getTest().setEndedTime(getTime(result.getEndMillis()));

        for (String group : result.getMethod().getGroups()) {
            ExtentTestManager.getTest().assignCategory(group);
        }

        if (result.getStatus() == 1) {
            ExtentTestManager.getTest().log(LogStatus.PASS, "Test Passed");
        } else if (result.getStatus() == 2) {
            ExtentTestManager.getTest().log(LogStatus.FAIL, result.getThrowable().getMessage());
        } else if (result.getStatus() == 3) {
            ExtentTestManager.getTest().log(LogStatus.SKIP, result.getThrowable().getMessage());
        }
        ExtentTestManager.endTest();
        extent.flush();
        //if (result.getStatus() == ITestResult.FAILURE) {
        // captureScreenshot(driver, result.getName());
        //}
        ad.quit();
    }

    @AfterSuite
    public void generateReport() {
        extent.close();
    }

    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }


    @Parameters({"OS", "appType", "deviceType", "deviceName", "version", "appDir", "appName"})
    @BeforeMethod
    public void setUp( @Optional("Android") String OS, @Optional("Phone") String appType, @Optional("Emulator") String deviceType,  @Optional("Android Emulator") String deviceName,
                       @Optional("9")String version, @Optional("app")String appDir, @Optional("abc.apk") String appName) throws IOException, InterruptedException {

        if (OS.equalsIgnoreCase("ios")) {
            if (appType.contains("iPhone")) {
                appDirectory = new File("/Users/muhtasimmahir/IdeaProjects/Mobile_Automation/UICatalog/src/app/UICatalog6.1.app.zip");
                findApp = new File(appDirectory, "UICatalog6.1.app.zip");
                if (deviceType.equalsIgnoreCase("RealDevice")) {
                    cap = new DesiredCapabilities();
                    cap.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
                    cap.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.IOS);
                    cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, version);
                    cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
                    cap.setCapability(MobileCapabilityType.NO_RESET, true);
                    cap.setCapability(MobileCapabilityType.APP, findApp.getAbsolutePath());
                    ad = new IOSDriver(new URL("http://0.0.0.0:4723/wd/hub"), cap);
                    ad.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

                } else if (deviceType.equalsIgnoreCase("Simulator")) {
                    cap = new DesiredCapabilities();
                    cap.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
                    cap.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.IOS);
                    cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, version);
                    cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
                    cap.setCapability(MobileCapabilityType.NO_RESET, true);
                    cap.setCapability(MobileCapabilityType.APP, appDirectory);
                    ad = new IOSDriver(new URL("http://0.0.0.0:4723/wd/hub"), cap);
                    ad.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                }

            } else if (appType.equalsIgnoreCase("iPad 2")) {
                appDirectory = new File(appDir);
                findApp = new File(appDirectory, appName);
                if (deviceType.contains("RealDevice")) {
                    cap = new DesiredCapabilities();
                    cap.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
                    cap.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.IOS);
                    cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, version);
                    cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
                    cap.setCapability(MobileCapabilityType.NO_RESET, true);
                    cap.setCapability(MobileCapabilityType.APP, findApp.getAbsolutePath());
                    ad = new IOSDriver(new URL("http://0.0.0.0:4723/wd/hub"), cap);
                    ad.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

                } else if (deviceType.equalsIgnoreCase("Simulator")) {
                    cap = new DesiredCapabilities();
                    cap.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
                    cap.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.IOS);
                    cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, version);
                    cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
                    cap.setCapability(MobileCapabilityType.NO_RESET, true);
                    cap.setCapability(MobileCapabilityType.APP, findApp.getAbsolutePath());
                    ad = new IOSDriver(new URL("http://0.0.0.0:4723/wd/hub"), cap);
                    ad.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

                }
            }

        } else if (OS.contains("Android")) {
            if (appType.contains("Phone")) {
                appDirectory = new File(appDir);
                findApp = new File(appDirectory, appName);
                if (deviceType.equalsIgnoreCase("RealDevice")) {
                    cap = new DesiredCapabilities();
                    cap.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
                    cap.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
                    cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, version);
                    cap.setCapability(MobileCapabilityType.NO_RESET, true);
                    cap.setCapability(MobileCapabilityType.APP, findApp.getAbsolutePath());
                    ad = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), cap);
                    ad.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

                } else if (deviceType.equalsIgnoreCase("Emulator")) {

                    cap = new DesiredCapabilities();
                    cap.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
                    cap.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
                    cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, version);
                    cap.setCapability(MobileCapabilityType.NO_RESET, true);
                    cap.setCapability(MobileCapabilityType.APP, findApp.getAbsolutePath());
                    ad = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), cap);
                    ad.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                }
            } else if (OS.equalsIgnoreCase("Tablets")) {
                if (deviceType.equalsIgnoreCase("RealDevice")) {
                    cap = new DesiredCapabilities();
                    cap.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
                    cap.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
                    cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, version);
                    cap.setCapability(MobileCapabilityType.NO_RESET, true);
                    cap.setCapability(MobileCapabilityType.APP, findApp.getAbsolutePath());
                    ad = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), cap);
                    ad.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

                } else if (deviceType.equalsIgnoreCase("Emulator")) {

                    cap = new DesiredCapabilities();
                    cap.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
                    cap.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
                    cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, version);
                    cap.setCapability(MobileCapabilityType.NO_RESET, true);
                    cap.setCapability(MobileCapabilityType.APP, findApp.getAbsolutePath());
                    ad = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), cap);
                    ad.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                }
            }
        }
    }

    @AfterMethod
    public void cleanUpApp(){
        ad.quit();
    }

    public void clickByXpath(String locator){
        ad.findElement(By.xpath(locator)).click();
    }
    public void clickByXpathWebElement(WebElement locator){
        locator.click();
    }
    public void sleep(int sec)throws InterruptedException{
        Thread.sleep(1000 * sec);
    }

    public void typeByXpath(String locator, String value, Keys key){
        ad.findElement(By.xpath(locator)).sendKeys(value);
    }
    public void typeByXpath(String locator, String value){
        ad.findElement(By.xpath(locator)).sendKeys(value);
    }
    public List<String> getTexts(List<WebElement> elements){
        List<String> text = new ArrayList<String>();
        for(WebElement element:elements){
            text.add(element.getText());
        }

        return text;
    }
    public static void scrollKeys(AppiumDriver driver, String[] list, String parent) {
        System.out.println("Starting the process");
        for (int i = 0; i < list.length; i++) {
            MobileElement we = (MobileElement) driver.findElementByXPath(parent+"/UIAPickerWheel["+(i+1)+"]");
            we.sendKeys(list[i]);
        }
        System.out.println("Ending Process");
    }
//    public void scrollToElement(AppiumDriver driver, String text){
//        MobileElement we = (MobileElement) driver.findElementByXPath(text);
//        driver.scrollTo(we.getText());
//    }
//    public void alertAccept(WebDriver driver){
//        WebDriverWait wait = new WebDriverWait(driver,5);
//        try{
//            wait.until(ExpectedConditions.alertIsPresent());
//            driver.switchTo().alert().accept();
//        }catch (Exception e){
//            System.err.println("No alert visible in 5 seconds");
//        }
//    }
//    public void scrollAndClickByName(String locator){
//        ad.scrollTo(locator).click();
//    }
}

