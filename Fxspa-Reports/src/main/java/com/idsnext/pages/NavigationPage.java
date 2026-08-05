package com.idsnext.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.idsnext.enums.ModuleName;

public class NavigationPage extends BasePage {

    public NavigationPage(WebDriver driver) {
        super(driver);
    }

    // ===== Common Locators =====

    private By fxOperationIcon =
            By.xpath("//div[contains(@title,'FX SPA (FX SPA)')]");

    private By randomIcon =
            By.xpath("(//span[@class='icon-shuffle shuffle-ico'])[2]");

    private By expandedMenu =
            By.cssSelector("(//span[@class='icon-shuffle shuffle-ico'])[2]");

    // ===== Module Locators =====

    private By appointmentReport =
            By.xpath("//span[normalize-space()='Appointment Report']");
    private By auditorsReport =
            By.xpath("(//span[normalize-space()='Auditors Report'])[2]");
    private By billPrintAuditReport =
            By.xpath("(//span[normalize-space()='Bill Print Audit Report'])[2]");
    private By businessRefusalReport =
            By.xpath("(//span[normalize-space()='Business Refusal Report'])");
    private By cancelBillReport =
            By.xpath("(//span[normalize-space()='Cancel Bill/Void Bill Details Report'])");
    private By cashierReport =
            By.xpath("(//span[normalize-space()='Cashier Report(SPA)'])");
    private By collectionReport =
            By.xpath("(//span[normalize-space()='Collection Report'])[2]");
    private By collectionSummary =
            By.xpath("(//span[normalize-space()='Collection Summary'])");
    private By complimentaryReport =
            By.xpath("(//span[normalize-space()='Complimentary Report'])[2]");
    private By customerRevenueAnalysis =
            By.xpath("(//span[normalize-space()='Customer Revenue Analysis'])");
    private By discountBillReport =
            By.xpath("(//span[normalize-space()='Discount Bill Report'])");
    private By guestListReport =
            By.xpath("(//span[normalize-space()='Guest List Report'])");
    private By guestProfileReport =
            By.xpath("(//span[normalize-space()='Guest Profile Report'])");
    private By itemSalesAnalysis =
            By.xpath("(//span[normalize-space()='Item Sale Analysis'])");
    private By listBillReport =
            By.xpath("(//span[normalize-space()='List Bill Report'])");
    private By masterExportReport =
            By.xpath("(//span[normalize-space()='Master Export Report'])");
    private By residentsAndNonResidentReport =
            By.xpath("(//span[normalize-space()='Residents & Non Residents Report'])");
    private By revenueByMembershipTypeReport =
            By.xpath("(//span[normalize-space()='Revenue By Membership Type Report'])[2]");
    private By roomUtilization =
            By.xpath("(//span[normalize-space()='Room Utilization'])");
    private By securityShellReport =
            By.xpath("(//span[normalize-space()='Security Shell Report'])");
    private By settlementByBill =
            By.xpath("(//span[normalize-space()='Settlement By Bill'])");
    private By settlementReport =
            By.xpath("(//span[normalize-space()='Settlement Report'])[2]");
    private By sotAuditReport =
            By.xpath("(//span[normalize-space()='SOT Audit Report'])");
    private By spaAnalysisReport =
            By.xpath("(//span[normalize-space()='Spa Analysis Report'])");
    private By spaCaptureReport =
            By.xpath("(//span[normalize-space()='Spa Capture Report'])");
    private By spaTaxRegister =
            By.xpath("(//span[normalize-space()='SPA Tax Register'])");
    private By staffProductivity =
            By.xpath("(//span[normalize-space()='Staff Productivity'])");
    private By staffUtilization =
            By.xpath("//span[normalize-space()='Staff Utilization']");
    private By topNCustomersReport =
            By.xpath("(//span[normalize-space()='Top N Customers Report'])");
    private By topNServicesSalesReport =
            By.xpath("(//span[normalize-space()='Top N Service/Product Sale Report'])");
    private By userManagementListReport =
            By.xpath("(//span[normalize-space()='User Management List Report'])");
    

    public void clickFXOperations() {

        waitForAngularIdle();

        WebElement fx =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                fxOperationIcon));

        jsClick(fx);

        waitForAngularIdle();
    }



    public void clickRandom() {

        waitForAngularIdle();

        WebElement random =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                randomIcon));

        jsClick(random);

        waitForAngularIdle();
    }

    private void ensureMenuExpanded() {

        try {

            if (!driver.findElement(expandedMenu).isDisplayed()) {

                clickRandom();
            }

        } catch (Exception e) {

            clickRandom();
        }
        }

        // ===== Dynamic Navigation =====

        public void clickModule(ModuleName moduleName) {

                // ensureMenuExpanded();

                switch (moduleName) {

                case APPOINTMENT_REPORT:
        click(appointmentReport);
        break;

        case AUDITORS_REPORT:
        click(auditorsReport);
        break;

        case BILL_PRINT_AUDIT_REPORT:
        click(billPrintAuditReport);
        break;

        case BUSINESS_REFUSAL_REPORT:
        click(businessRefusalReport);
        break;

        case CANCEL_BILL_REPORT:
        click(cancelBillReport);
        break;

        case CASHIER_REPORT:
        click(cashierReport);
        break;

        case COLLECTION_REPORT:
        click(collectionReport);
        break;

        case COLLECTION_SUMMARY:
        click(collectionSummary);
        break;

        case COMPLIMENTARY_REPORT:
        click(complimentaryReport);
        break;

        case DISCOUNT_BILL_REPORT:
        click(discountBillReport);
        break;

        case GUEST_LIST_REPORT:
        click(guestListReport);
        break;

        case GUEST_PROFILE_REPORT:
        click(guestProfileReport);
        break;

        case ITEM_SALES_ANALYSIS:
        click(itemSalesAnalysis);
        break;

        case LIST_BILL_REPORT:
        click(listBillReport);
        break;

        case MASTER_EXPORT_REPORT:
        click(masterExportReport);
        break;

        case RESIDENTS_AND_NON_RESIDENT_REPORT:
        click(residentsAndNonResidentReport);
        break;

        case REVENUE_BY_MEMBERSHIP_TYPE_REPORT:
        click(revenueByMembershipTypeReport);
        break;

        case ROOM_UTILIZATION_REPORT:
        click(roomUtilization);
        break;

        case SECURITY_SHELL_REPORT:
        click(securityShellReport);
        break;

        case SETTLEMENT_BY_BILL:
        click(settlementByBill);
        break;

        case SETTLEMENT_REPORT:
        click(settlementReport);
        break;

        case SOT_AUDIT_REPORT:
        click(sotAuditReport);
        break;

        case SPA_ANALYSIS_REPORT:
        click(spaAnalysisReport);
        break;

        case CUSTOMER_REVENUE_ANALYSIS:
        click(customerRevenueAnalysis);
        break;

        case SPA_CAPTURE_REPORT:
        click(spaCaptureReport);
        break;

        case SPA_TAX_REGISTER:
        click(spaTaxRegister);
        break;

        case STAFF_PRODUCTIVITY:
        click(staffProductivity);
        break;

        case STAFF_UTILIZATION:
        click(staffUtilization);
        break;

        case TOP_N_CUSTOMERS_REPORT:
        click(topNCustomersReport);
        break;

        case TOP_N_SERVICES_SALES_REPORT:
        click(topNServicesSalesReport);
        break;

        case USER_MANAGEMENT_LIST_REPORT:
        click(userManagementListReport);
        break;

        default:
        throw new RuntimeException("Module not found: " + moduleName);
                }
        }
        }
