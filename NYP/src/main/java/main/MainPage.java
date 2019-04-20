package main;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends MainAPI{

    @FindBy(xpath = "//android.widget.ImageButton[@content-desc=\"Open menu\"]\n")
    WebElement Burger;

    public void clickBurger() throws Exception{
        Burger.click();
    }

    @FindBy(xpath = "//android.widget.TextView[@content-desc=\"Turn On Notifications\"]\n")
    WebElement Alerts;

    public void checkAlerts() throws Exception{
        Alerts.click();
    }

    @FindBy(xpath = "//android.widget.TextView[@text='Search Articles']")
    WebElement Search;

    public void clickSearch() throws Exception{
        Burger.click();
        Search.sendKeys("Brooklyn news");
        Thread.sleep(3000);
    }

    @FindBy(xpath = "//android.widget.CheckedTextView[@text='Latest Stories']")
    WebElement LatestStories;

    public void clickLS() throws Exception{
        Burger.click();
        LatestStories.click();
        Thread.sleep(3000);
        scrollDown();
    }

    @FindBy(xpath = "//android.widget.CheckedTextView[@text='News']")
    WebElement News;

    public void clickNews() throws Exception{
        Burger.click();
        News.click();
        Thread.sleep(3000);
        scrollDown();
    }

    @FindBy(xpath = "//android.widget.CheckedTextView[@text='Metro']")
    WebElement Metro;

    public void clickMetro() throws Exception{
        Burger.click();
        Metro.click();
        Thread.sleep(3000);
        scrollDown();
    }

    @FindBy(xpath = "//android.widget.CheckedTextView[@text='Page Six']")
    WebElement PageSix;

    public void clickPS() throws Exception{
        Burger.click();
        PageSix.click();
        Thread.sleep(3000);
        scrollDown();
    }

    @FindBy(xpath = "//android.widget.CheckedTextView[@text='Sports']")
    WebElement Sports;

    public void clickSports() throws Exception{
        Burger.click();
        Sports.click();
        Thread.sleep(3000);
        scrollDown();
    }

    @FindBy(xpath = "//android.widget.CheckedTextView[@text='Business']")
    WebElement Business;

    public void clickBusiness() throws Exception{
        Burger.click();
        Business.click();
        Thread.sleep(3000);
        scrollDown();
    }

    @FindBy(xpath = "//android.widget.CheckedTextView[@text='Opinion']")
    WebElement Opinion;

    public void clickOpinion() throws Exception{
        Burger.click();
        Opinion.click();
        Thread.sleep(35000);
        scrollDown();
    }

    @FindBy(xpath = "//android.widget.CheckedTextView[@text='Entertainment']")
    WebElement Entertainment;

    public void clickEntertainment() throws Exception{
        Burger.click();
        Entertainment.click();
        Thread.sleep(3000);
        scrollDown();
    }

    @FindBy(xpath = "//android.widget.CheckedTextView[@text='Fashion']")
    WebElement Fashion;

    public void clickFashion() throws Exception{
        Burger.click();
        Fashion.click();
        Thread.sleep(3000);
        scrollDown();
    }

    @FindBy(xpath = "//android.widget.CheckedTextView[@text='Living']")
    WebElement Living;

    public void clickLiving() throws Exception{
        Burger.click();
        Living.click();
        Thread.sleep(3000);
        scrollDown();
    }

    @FindBy(xpath = "//android.widget.CheckedTextView[@text='Media']")
    WebElement Media;

    public void clickMedia() throws Exception{
        Burger.click();
        Media.click();
        Thread.sleep(3000);
        scrollDown();
    }

    @FindBy(xpath = "//android.widget.CheckedTextView[@text='Tech']")
    WebElement Tech;

    public void clickTech() throws Exception{
        Burger.click();
        Tech.click();
        Thread.sleep(3000);
        scrollDown();
    }

    @FindBy(xpath = "//android.widget.CheckedTextView[@text='Real Estate']")
    WebElement RealEstate;

    public void clickRE() throws Exception{
        Burger.click();
        RealEstate.click();
        Thread.sleep(3000);
        scrollDown();
    }

    @FindBy(xpath = "//android.widget.CheckedTextView[@text='Video']")
    WebElement Video;

    public void clickVideo() throws Exception{
        Burger.click();
        Video.click();
        Thread.sleep(3000);
        scrollDown();
    }

    @FindBy(xpath = "//android.widget.CheckedTextView[@text='Photos']")
    WebElement Photos;

    public void clickPhotos() throws Exception{
        Burger.click();
        Photos.click();
        Thread.sleep(3000);
        scrollDown();
    }

    @FindBy(xpath = "//android.widget.CheckedTextView[@text='Page Six TV']")
    WebElement PageSixTV;

    public void clickPSTV() throws Exception{
        Burger.click();
        PageSixTV.click();
        Thread.sleep(3000);
        scrollDown();
    }

    @FindBy(xpath = "//android.widget.CheckedTextView[@text='Saved Articles']")
    WebElement SavedArticals;

    public void clickSA() throws Exception{
        Burger.click();
        SavedArticals.click();
        Thread.sleep(2000);
        scrollDown();
    }

    public void scrollDown() {
        Dimension dimensions = ad.manage().window().getSize();
        int Startpoint = (int) (dimensions.getHeight() * 0.8);
        int scrollEnd = (int) (dimensions.getHeight() * 0.1);
        ad.swipe(200, Startpoint,200,scrollEnd,2000);
    }



}