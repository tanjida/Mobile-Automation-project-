package tests;

import main.MainAPI;
import main.MainPage;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MainPageTest extends MainAPI {
    MainPage mainPage;

    @BeforeMethod
    public void initialize(){
        mainPage = PageFactory.initElements(this.ad, MainPage.class);
    }

    @Test
    public void clickBurgerButton() throws Exception{
        mainPage.clickBurger();
    }

    @Test
    public void clickAlerts() throws Exception {
        mainPage.checkAlerts();
    }

    @Test
    public void clickSearch() throws Exception {
        mainPage.clickSearch();
    }


    @Test
    public void clickLSButton() throws Exception{
        mainPage.clickLS();
    }

    @Test
    public void clickNewsButton() throws Exception{
        mainPage.clickNews();
    }

    @Test
    public void clickMetroButton() throws Exception{
        mainPage.clickMetro();
    }

    @Test
    public void clickPSButton() throws Exception{
        mainPage.clickPS();
    }

    @Test
    public void clickSportsButton() throws Exception{
        mainPage.clickSports();
    }

    @Test
    public void clickBusinessButton() throws Exception{
        mainPage.clickBusiness();
    }

    @Test
    public void clickOpinionButton() throws Exception {
        mainPage.clickOpinion();
    }

    @Test
    public void clickEntertaninmetButton() throws Exception{
        mainPage.clickEntertainment();
    }

    @Test
    public void clickFashionButton() throws Exception{
        mainPage.clickFashion();
    }

    @Test
    public void clickLivingButton() throws Exception{
        mainPage.clickLiving();
    }

    @Test
    public void clickMediaButton() throws Exception{
        mainPage.clickMedia();
    }

    @Test
    public void clickTechButton() throws Exception{
        mainPage.clickTech();
    }

    @Test
    public void clickREButton() throws Exception{
        mainPage.clickRE();
    }

    @Test
    public void clickVideoButton() throws Exception{
        mainPage.clickVideo();
    }

    @Test
    public void clickPhotosButton() throws Exception{
        mainPage.clickPhotos();
    }

    @Test
    public void clickPSTVButton() throws Exception{
        mainPage.clickPSTV();
    }

    @Test
    public void clickSAButton() throws Exception{
        mainPage.clickSA();
    }

}