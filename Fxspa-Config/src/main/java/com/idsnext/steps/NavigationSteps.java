package com.idsnext.steps;

import org.openqa.selenium.WebDriver;

import com.idsnext.enums.ModuleName;
import com.idsnext.pages.NavigationPage;

public class NavigationSteps {

    private NavigationPage navigationPage;

    public NavigationSteps(WebDriver driver) {

        navigationPage = new NavigationPage(driver);
    }

    // Common navigation for all modules
    public void navigateToModule(ModuleName moduleName) {

        navigationPage.clickRandom();
        navigationPage.clickModule(moduleName);
        navigationPage.clickRandom();
    }

    // Common navigation + add button
    public void navigateToAddPage(ModuleName moduleName) {

        navigateToModule(moduleName);
        navigationPage.clickAdd();
    }
}