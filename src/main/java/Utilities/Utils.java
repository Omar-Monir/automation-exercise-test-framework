package Utilities;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

public class Utils {

    public static final int IMPLICIT_WAIT_TIME = 10;
    public static final int PAGE_LOAD_TIME = 5;



    public static class Constants {

        //  Test Data - My User
        public static final String MY_EMAIL = "softwaretester@example.com";
        //  Test Data - Valid User
        public static final String VALID_NAME = "Software";
        public static final String VALID_EMAIL = "software.test@example.com";
        public static final String VALID_PASSWORD = "Test@12345";

        // Test Data - Invalid User
        public static final String INVALID_EMAIL = "wrongemail@example.com";
        public static final String INVALID_PASSWORD = "WrongPassword123";

        // Registration Data
        public static final String FIRST_NAME = "Software";
        public static final String LAST_NAME = "Tester";
        public static final String DAY = "5";
        public static final String MONTH ="10" ;
        public static final String YEAR = "2001";
        public static final String COMPANY = "Test Company";
        public static final String ADDRESS1 = "123 Street";
        public static final String ADDRESS2 = "Apartment 4";
        public static final String COUNTRY = "Canada";
        public static final String STATE = "Ontario";
        public static final String CITY = "Toronto";
        public static final String ZIPCODE = "M5V3L9";
        public static final String MOBILE_NUMBER = "1234567890";
        public static final String NAME_ON_CARD = "SoftwareTester";
        public static final String CARD_NUMBER = "4111 1111 1111 1111";
        public static final String CVC = "123";
        public static final String EXPIRATION_MONTH = "12";
        public static final String EXPIRATION_YEAR = "2030";

        //Browser & URL
        public static final String BROWSER = "brave";
        public static final String BASE_URL = "https://www.automationexercise.com/";
        public static final String SIGNUP_LOGIN_URL = "https://www.automationexercise.com/login";
        public static final String TEST_CASES_PAGE_URL = "https://www.automationexercise.com/test_cases";
        public static final String ALL_PRODUCTS_PAGE_URL = "https://www.automationexercise.com/products";
        public static final String FIRST_PRODUCT_DETAILS_PAGE_URL = "https://www.automationexercise.com/product_details/1";
        public static final String CART_PAGE_URL = "https://www.automationexercise.com/view_cart";
        public static final String CATEGORY_PAGE_URL = "category_products";
        public static final String BRAND_PAGE_URL = "brand_products";

        //Contact Us
        public static final String SUBJECT = "Test Message";
        public static final String MESSAGE = "This is a test message from automation";
        public static final String FILE_PATH = System.getProperty("user.dir")+"/file/file.txt";

        //Check Messages
        public static final String HOME_PAGE_LOGGED_IN_AS_MESSAGE
                = "Logged in as ";
        public static final String HOME_PAGE_SUBSCRIPTION_MESSAGE
                = "SUBSCRIPTION";
        public static final String HOME_PAGE_SUCCESSFUL_SUBSCRIPTION_MESSAGE
                = "You have been successfully subscribed!";
        public static final String CART_PAGE_SUBSCRIPTION_MESSAGE
                = "SUBSCRIPTION";
        public static final String CART_PAGE_SUCCESSFUL_SUBSCRIPTION_MESSAGE
                = "You have been successfully subscribed!";
        public static final String SIGNUP_LOGIN_PAGE_NEW_USER_SIGNUP_MESSAGE
                = "New User Signup!";
        public static final String SIGNUP_LOGIN_PAGE_LOGIN_TO_YOUR_ACCOUNT_MESSAGE
                ="Login to your account";
        public static final String SIGNUP_LOGIN_PAGE_EMAIL_ADDRESS_IS_EXIST_MESSAGE
                = "Email Address already exist!";
        public static final String SIGNUP_LOGIN_PAGE_YOUR_EMAIL_PASSWORD_IS_NOT_CORRECT_MESSAGE
                = "Your email or password is incorrect!";
        public static final String ENTER_ACCOUNT_INFORMATION_PAGE_ENTER_ACCOUNT_INFORMATION
                ="ENTER ACCOUNT INFORMATION";
        public static final String ACCOUNT_CREATED_PAGE_ACCOUNT_CREATED_MESSAGE
                = "ACCOUNT CREATED!";
        public static final String ACCOUNT_DELETED_PAGE_ACCOUNT_DELETED_MESSAGE
                = "ACCOUNT DELETED!";
        public static final String GET_IN_TOUCH_PAGE_GET_IN_TOUCH_MESSAGE
                ="GET IN TOUCH";
        public static final String GET_IN_TOUCH_PAGE_SUBMIT_SUCCESSFULLY_MESSAGE
                = "Success! Your details have been submitted successfully.";
        public static final String PRODUCT_PAGE_SEARCHED_PRODUCTS_MESSAGE
                = "SEARCHED PRODUCTS";
        public static final String ORDER_PLACED_SUCCESSFULLY_MASSAGE
                = "Your order has been placed successfully!";

        //Assertion fail messages
        public static final String HOME_PAGE_IS_NOT_VISIBLE_SUCCESSFULLY
                ="HOME PAGE IS NOT VISIBLE SUCCESSFULLY";
        public static final String NEW_USER_SIGNUP_MASSAGE_IS_NOT_VISIBLE
                ="NEW USER SIGNUP MASSAGE IS NOT VISIBLE ";
        public static final String ACCOUNT_CREATED_MASSAGE_IS_NOT_VISIBLE_SUCCESSFULLY
                ="ACCOUNT CREATED MASSAGE IS NOT VISIBLE SUCCESSFULLY";
        public static final String ENTER_ACCOUNT_INFORMATION_IS_NOT_VISIBLE_SUCCESSFULLY
                ="ENTER ACCOUNT INFORMATION IS NOT VISIBLE SUCCESSFULLY";
        public static final String DELETE_ACCOUNT_MASSAGE_IS_NOT_VISIBLE_SUCCESSFULLY
                ="DELETE ACCOUNT MASSAGE IS NOT VISIBLE SUCCESSFULLY";
        public static final String LOGGING_TO_YOUR_ACCOUNT_MASSAGE_IS_NOT_VISIBLE_SUCCESSFULLY
                ="LOGGING TO YOUR ACCOUNT MASSAGE IS NOT VISIBLE SUCCESSFULLY";
        public static final String LOGGED_IN_AS_USER_NAME_IS_NOT_VISIBLE_SUCCESSFULLY
                ="LOGGED IN AS USER NAME IS NOT VISIBLE SUCCESSFULLY";
        public static final String EMAIL_OR_PASSWORD_IS_NOT_CORRECT_MASSAGE_IS_NOT_VISIBLE_SUCCESSFULLY
                ="EMAIL OR PASSWORD IS NOT CORRECT MASSAGE IS NOT VISIBLE SUCCESSFULLY";
        public static final String SIGNUP_LOGIN_PAGE_IS_NOT_VISIBLE_SUCCESSFULLY
                ="SIGNUP_LOGIN PAGE IS NOT VISIBLE SUCCESSFULLY";
        public static final String EMAIL_ADDRESS_ALREADY_EXIST_MASSAGE_IS_NOT_VISIBLE_SUCCESSFULLY
                ="EMAIL ADDRESS ALREADY EXIST MASSAGE IS NOT VISIBLE SUCCESSFULLY";
        public static final String GET_IN_TOUCHE_MASSAGE_IS_NOT_VISIBLE_SUCCESSFULLY
                ="GET IN TOUCHE MASSAGE IS NOT VISIBLE SUCCESSFULLY";
        public static final String SUBMIT_SUCCESSFULLY_MASSAGE_IS_NOT_VISIBLE_SUCCESSFULLY
                ="SUBMIT SUCCESSFULLY MASSAGE IS NOT VISIBLE SUCCESSFULLY";
        public static final String TEST_CASE_PAGE_IS_NOT_VISIBLE_SUCCESSFULLY
                ="TEST CASE PAGE IS NOT VISIBLE SUCCESSFULLY";
        public static final String ALL_PRODUCTS_PAGE_IS_NOT_VISIBLE_SUCCESSFULLY
                ="ALL PRODUCTS PAGE IS NOT VISIBLE SUCCESSFULLY";
        public static final String PRODUCTS_DETAILS_PAGE_IS_NOT_VISIBLE_SUCCESSFULLY
                ="PRODUCTS DETAILS PAGE IS NOT VISIBLE SUCCESSFULLY";
        public static final String DETAILS_IS_NOT_VISIBLE_SUCCESSFULLY
                ="DETAILS IS NOT VISIBLE SUCCESSFULLY";
        public static final String SEARCH_PRODUCTS_MESSAGE_IS_NOT_VISIBLE_SUCCESSFULLY
                ="SEARCH PRODUCTS MESSAGE IS NOT VISIBLE SUCCESSFULLY";
        public static final String HOME_SUBSCRIPTION_MESSAGE_IS_NOT_VISIBLE_SUCCESSFULLY
                ="HOME SUBSCRIPTION MESSAGE IS NOT VISIBLE SUCCESSFULLY";
        public static final String HOME_SUCCESSFUL_SUBSCRIPTION_MESSAGE_IS_NOT_VISIBLE_SUCCESSFULLY
                ="HOME SUCCESSFUL SUBSCRIPTION MESSAGE IS NOT VISIBLE SUCCESSFULLY";
        public static final String CART_SUBSCRIPTION_MESSAGE_IS_NOT_VISIBLE_SUCCESSFULLY
                ="CART SUBSCRIPTION MESSAGE IS NOT VISIBLE SUCCESSFULLY";
        public static final String CART_SUCCESSFUL_SUBSCRIPTION_MESSAGE_IS_NOT_VISIBLE_SUCCESSFULLY
                ="CART SUCCESSFUL SUBSCRIPTION MESSAGE IS NOT VISIBLE SUCCESSFULLY";
        public static final String FIRST_PRODUCT_IS_NOT_ADDED_SUCCESSFULLY
                ="FIRST PRODUCT IS NOT ADDED SUCCESSFULLY";
        public static final String SECOND_PRODUCT_IS_NOT_ADDED_SUCCESSFULLY
                ="SECOND PRODUCT IS NOT ADDED SUCCESSFULLY";
        public static final String QUANTITY_OF_PRODUCT_IS_NOT_RIGHT
                ="QUANTITY OF PRODUCT IS NOT RIGHT";
        public static final String CART_PAGE_IS_NOT_VISIBLE_SUCCESSFULLY
                ="CART PAGE IS NOT VISIBLE SUCCESSFULLY";
        public static final String YOUR_ORDER_HAS_PLACED_SUCCESSFULLY_MESSAGE_IS_NOT_VISIBLE_SUCCESSFULLY
                ="YOUR ORDER HAS PLACED SUCCESSFULLY MESSAGE IS NOT VISIBLE SUCCESSFULLY";
        public static final String PRODUCT_IS_NOT_DELETED_SUCCESSFULLY
                ="PRODUCT IS NOT DELETED SUCCESSFULLY";
        public static final String CATEGORIES_IS_NOT_VISIBLE_SUCCESSFULLY
                ="CATEGORIES IS NOT VISIBLE SUCCESSFULLY";
        public static final String CATEGORY_PAGE_IS_NOT_VISIBLE_SUCCESSFULLY
                ="CATEGORY PAGE IS NOT VISIBLE SUCCESSFULLY";
        public static final String BRAND_IS_NOT_VISIBLE_SUCCESSFULLY
                ="BRAND IS NOT VISIBLE SUCCESSFULLY";
        public static final String BRAND_PAGE_IS_NOT_VISIBLE_SUCCESSFULLY
                ="BRAND PAGE IS NOT VISIBLE SUCCESSFULLY";



        // Other
        public static final String SCREENSHOT_PATH = System.getProperty("user.dir") + "/screenshots/";
        public static final String REPORT_PATH = System.getProperty("user.dir") + "/reports/";
        public static final String SEARCH_PRODUCT = "DRESS";
        public static final String QUANTITY = "4";
        public static final String CHECK_OUT_COMMENT = "This is a gift, kindly wrap it nicely.";
    }

    // Make time dependant E-mail
    public static String generateEmailWithTimeStamp() {
        Date date = new Date();
        String timeStamp = date.toString().replace(" ","_").replace(":","_");
        return "software_tester" + timeStamp + "@gmail.com";
    }

    // In case of using Excel file to get data from
    public static Object[][] getTestDataFromExcel(String sheetName) throws IOException {

        File excelFile = new File(System.getProperty("user.dir")
                + File.separator + "src"
                + File.separator + "main"
                + File.separator + "java"
                + File.separator + "testData"
                + File.separator + "testData.xlsx");

        FileInputStream file =new FileInputStream(excelFile);
        XSSFWorkbook workbook =new XSSFWorkbook(file);
        XSSFSheet sheet= workbook.getSheet(sheetName);
        if (sheet == null) {
            throw new RuntimeException("Sheet '" + sheetName + "' not found in Excel file");
        }

        int rows = sheet.getLastRowNum();
        int cols = sheet.getRow(0).getLastCellNum();
        Object [] [] data =new Object[rows][cols];

        for (int i=0;i<rows;i++){

            XSSFRow row = sheet.getRow(i+1);

            for (int j=0;j<cols;j++){

                XSSFCell cell = row.getCell(j);
                CellType cellType =cell.getCellType();

                switch(cellType){

                    case STRING -> data[i][j]=cell.getStringCellValue();
                    case NUMERIC -> data[i][j]=Integer.toString((int)cell.getNumericCellValue());
                    case BOOLEAN -> data[i][j]=cell.getBooleanCellValue();

                }

            }

        }

        return data;

    }

}
