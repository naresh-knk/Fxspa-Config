package utils;


import org.testng.Assert;

public class AssertionUtils {

    public static void assertTrueWithMessage(boolean condition,
                                             String successMsg,
                                             String failureMsg) {
        if (condition) {
            System.out.println("Test Passed - " + successMsg);
        } else {
            Assert.fail("Test Failed - " + failureMsg);
        }
    }

    public static void assertEqualsWithMessage(String actual,
                                               String expected,
                                               String successMsg,
                                               String failureMsg) {
        if (actual.equals(expected)) {
            System.out.println("Test Passed - " + successMsg);
        } else {
            Assert.fail("Test Failed - " + failureMsg +
                    " | Expected: " + expected +
                    " | Actual: " + actual);
        }
    }
}