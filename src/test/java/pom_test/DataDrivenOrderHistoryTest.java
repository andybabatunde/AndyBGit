
package pom_test;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pom_pages.OrderHistoryPOM;

//The DataDriven Junit Runner Class
//Test runner class - @RunWith annotation

	   @RunWith (value = Parameterized.class)	

	    public class DataDrivenOrderHistoryTest {

		//Property to store the driver instance
		
		static WebDriver driver;
		static String baseURL = "http://automationpractice.com";
		static StringBuffer verificationErrors = new StringBuffer();
		
		//Fields to store the csv file data
		//Fields to hold the test data for use in the test
	
		private String email;
		private String password;
		
		// Accepts a set of data for each test run from the parameterised runner
		// Declare a constructor with parameters to be loaded with our data
		
		public DataDrivenOrderHistoryTest (String email, String password) {
			
			this.email = email;
			this.password = password;
		}
		
		//Used to invoke method to open csv file & retrieve data as list object
		//Declare a parameter method using the fixture @Parameters
		 
		@Parameters 
		//(name = "Test{index}, Expect To :{1}:  email ({0}) = password ({1})") 
		public static Collection <String []> testData () throws IOException {
			
			//return getTestData ("C:\\Users\\Babs\\Documents\\exercisedata.csv");
		
			return Arrays.asList(new String [][] {
				{"SeleniumTesting@gmail.com",  "Selenium123"},
			});
		}
		
	@Before
	public void setUp() throws Exception {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Babs\\Selenium - Nov 2018\\chromedriver.exe");
		driver = new ChromeDriver ();
		driver.get(baseURL);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	@After
	public void tearDown() throws Exception {
		
		//driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail ("verificationErrorString");
		}
}
	@Test
	public void test() throws InterruptedException {
		
		System.out.println("Starting Login Test");
		System.out.println("email is: " +  email + "\t password is: " + password);
		
		//Instantiate a new OrderHistoryPOM object
		OrderHistoryPOM login = new OrderHistoryPOM (driver);
		
		//Method calls to perform the login process here
		login.clickSignIn();
		login.clearemail();
		login.setemail("SeleniumTesting@gmail.com");
		login.clearpassword();
		login.setpassword("Selenium123");
		login.clickSubmitLogin();
		login.clickTShirts();
		login.clickQuickView();
		login.clickAddToCart();
		login.clickCheckOutOne();
		login.clickCheckOutTwo();
		login.clickProcessAddress();
		login.clickTermsOfService();
		login.clickProcessCarrier();
		login.clickBankWire();
		login.clickOrderConfirmation();
		login.clickCustomerAccount();
		login.clickOrderHistory();
		
	
		// handle the alert box that may be presented
		if(isAlertPresent()){
			driver.switchTo().alert();
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
			}
}
	
	// Utility method to read the CSV file
	public static Collection<String[]> getTestData(String fileName)
			throws IOException {
		List<String[]> records = new ArrayList<String[]>();
		String record;
		BufferedReader file = new BufferedReader(new FileReader(fileName));
			
		while ((record = file.readLine()) != null) {
			String fields[] = record.split(",");
			records.add(fields);
		}
		file.close();
		return records;
	}
	
	private boolean isAlertPresent(){
		try{
			driver.switchTo().alert();
			return true;
		}
		catch(Exception e){
			return false;
}
	}
	}

	