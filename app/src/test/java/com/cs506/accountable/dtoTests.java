package com.cs506.accountable;

import com.cs506.accountable.dto.Account;
import com.cs506.accountable.dto.Bill;
import com.cs506.accountable.dto.Goal;
import com.cs506.accountable.dto.Income;
import com.cs506.accountable.dto.Purchase;
import com.cs506.accountable.dto.User;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class dtoTests {

    /*
    Test the getters and setters for an empty account constructor
     */
    @Test
    public void accountEmptyConstructor_isCorrect() throws Exception {

        //Create empty account
        Account account = new Account();

        // Verify Values
        assertEquals(-1, account.getAccountId());
        assertEquals(-1, account.getUserId());
        assertEquals("", account.getAccountName());
        assertEquals(-1.00, account.getBalance(), 0);
    }

    /*
    Test the getters and setters for an empty account constructor
    */
    @Test
    public void accountCustomConstructor_isCorrect() throws Exception {

        int accountId = 100;
        int userId = 101;
        String accountName = "account Test Name";
        double balance = 1000.00;

        // Create custom account
        Account account = new Account(accountId, userId, accountName, balance);

        // Verify values
        assertEquals(accountId, account.getAccountId());
        assertEquals(userId, account.getUserId());
        assertEquals(accountName, account.getAccountName());
        assertEquals(balance, account.getBalance(), 0);
    }

    /*
    Test the getters and setters for an empty account constructor
    */
    @Test
    public void accountEmptyConstructorCustomSetters_isCorrect() throws Exception {

        int accountId = 100;
        int userId = 101;
        String accountName = "account Test Name";
        double balance = 1000.00;

        // Create empty account
        Account account = new Account();

        //Set the values
        account.setAccountId(accountId);
        account.setUserId(userId);
        account.setAccountName(accountName);
        account.setBalance(balance);

        //Confirm values
        assertEquals(accountId, account.getAccountId());
        assertEquals(userId, account.getUserId());
        assertEquals(accountName, account.getAccountName());
        assertEquals(balance, account.getBalance(), 0);
    }

    /*
    Test the getters and setters for an empty bill constructor
    */
    @Test
    public void billEmptyConstructor_isCorrect() throws Exception {

        //Create empty bill
        Bill bill = new Bill();

        // Verify Values
        assertEquals(-1, bill.getBillId());
        assertEquals("", bill.getBillName());
        assertEquals(-1, bill.getUserId());
        assertEquals(-1, bill.getAccountId());
        assertEquals(-1.00, bill.getBillAmount(), 0);
        assertEquals("", bill.getDueDate());
        assertEquals(-1, bill.getOccurrenceRte());
    }

    /*
    Test the getters and setters for an empty bill constructor
    */
    @Test
    public void billCustomConstructor_isCorrect() throws Exception {

        int billId = 100;
        String billName = "test Bill";
        int userId = 101;
        int accountId = 202;
        double billAmount = 300.25;
        String dueDate = "02/02/2018";
        int occurrenceRte = 1;

        //Create empty bill
        Bill bill = new Bill(billId,userId,accountId,billName,billAmount,dueDate,occurrenceRte);

        // Verify Values
        assertEquals(billId, bill.getBillId());
        assertEquals(billName, bill.getBillName());
        assertEquals(userId, bill.getUserId());
        assertEquals(accountId, bill.getAccountId());
        assertEquals(billAmount, bill.getBillAmount(), 0);
        assertEquals(dueDate, bill.getDueDate());
        assertEquals(occurrenceRte, bill.getOccurrenceRte());
    }

    /*
    Test the getters and setters for an empty bill constructor
    */
    @Test
    public void billEmptyConstructorCustomSetters_isCorrect() throws Exception {

        int billId = 100;
        String billName = "test Bill";
        int userId = 101;
        int accountId = 202;
        double billAmount = 300.25;
        String dueDate = "02/02/2018";
        int occurrenceRte = 1;

        //Create empty bill
        Bill bill = new Bill();

        //Set the values
        bill.setBillId(billId);
        bill.setBillName(billName);
        bill.setUserId(userId);
        bill.setAccountId(accountId);
        bill.setBillAmount(billAmount);
        bill.setDueDate(dueDate);
        bill.setOccurrenceRte(occurrenceRte);

        // Verify Values
        assertEquals(billId, bill.getBillId());
        assertEquals(billName, bill.getBillName());
        assertEquals(userId, bill.getUserId());
        assertEquals(accountId, bill.getAccountId());
        assertEquals(billAmount, bill.getBillAmount(), 0);
        assertEquals(dueDate, bill.getDueDate());
        assertEquals(occurrenceRte, bill.getOccurrenceRte());
    }

    /*
Test the getters and setters for an empty income constructor
*/
    @Test
    public void incomeEmptyConstructor_isCorrect() throws Exception {

        //Create empty Income
        Income income = new Income();

        // Verify Values
        assertEquals(-1, income.getIncomeId());
        assertEquals(-1, income.getUserId());
        assertEquals(-1, income.getAccountId());
        assertEquals("", income.getIncomeName());
        assertEquals(-1.00, income.getAmount(), 0);
        assertEquals("", income.getDate());
        assertEquals("", income.getPayPeriod());
        assertEquals(-1.00, income.getHours(), 0);
    }

    /*
    Test the getters and setters for an empty income constructor
    */
    @Test
    public void incomeCustomConstructor_isCorrect() throws Exception {

        int incomeId = 100;
        int userId = 502;
        int accountId = 300;
        String incomeName = "test income name";
        double amount = 520.35;
        String date = "02/02/2091";
        String payPeriod = "Monthly";
        int hours = 1;

        //Create empty Income
        Income income = new Income(incomeId,userId,accountId,incomeName,amount,date,payPeriod,hours);

        // Verify Values
        assertEquals(incomeId, income.getIncomeId());
        assertEquals(userId, income.getUserId());
        assertEquals(accountId, income.getAccountId());
        assertEquals(incomeName, income.getIncomeName());
        assertEquals(amount, income.getAmount(), 0);
        assertEquals(date, income.getDate());
        assertEquals(payPeriod, income.getPayPeriod());
        assertEquals(hours, income.getHours(), 0);
    }

    /*
    Test the getters and setters for an empty income constructor
    */
    @Test
    public void incomeEmptyConstructorCustomSetters_isCorrect() throws Exception {

        int incomeId = 100;
        int userId = 502;
        int accountId = 300;
        String incomeName = "test income name";
        double amount = 520.35;
        String date = "02/02/2091";
        String payPeriod = "Monthly";
        int hours = 1;

        //Create empty Income
        Income income = new Income();

        income.setIncomeId(incomeId);
        income.setUserId(userId);
        income.setAccountId(accountId);
        income.setIncomeName(incomeName);
        income.setAmount(amount);
        income.setDate(date);
        income.setPayPeriod(payPeriod);
        income.setHours(hours);

        // Verify Values
        assertEquals(incomeId, income.getIncomeId());
        assertEquals(userId, income.getUserId());
        assertEquals(accountId, income.getAccountId());
        assertEquals(incomeName, income.getIncomeName());
        assertEquals(amount, income.getAmount(), 0);
        assertEquals(date, income.getDate());
        assertEquals(payPeriod, income.getPayPeriod());
        assertEquals(hours, income.getHours(), 0);
    }

    /*
Test the getters and setters for an empty purchase constructor
*/
    @Test
    public void purchaseEmptyConstructor_isCorrect() throws Exception {

        //Create empty purchase
        Purchase purchase = new Purchase();

        // Verify Values
        assertEquals(-1, purchase.getPurchaseId());
        assertEquals(-1, purchase.getUserId());
        assertEquals(-1, purchase.getAccountId());
        assertEquals(-1.00, purchase.getPrice(), 0);
        assertEquals("", purchase.getDate());
        assertEquals("", purchase.getTime());
        assertEquals("", purchase.getCategory());
        assertEquals("", purchase.getLocation());
        assertEquals("", purchase.getComment());
    }

    /*
    Test the getters and setters for an empty purchase constructor
    */
    @Test
    public void purchaseCustomConstructor_isCorrect() throws Exception {

        int purchaseId = 41;
        int userId = 99;
        int accountId = 6541;
        double price = 99.99;
        String date = "02/19/2019";
        String time = "12:12";
        String category = "test category";
        String location = "test location";
        String comment = "test comment";

        //Create empty purchase
        Purchase purchase = new Purchase(purchaseId, userId, accountId, price, date, time, category, location, comment);

        // Verify Values
        assertEquals(purchaseId, purchase.getPurchaseId());
        assertEquals(userId, purchase.getUserId());
        assertEquals(accountId, purchase.getAccountId());
        assertEquals(price, purchase.getPrice(), 0);
        assertEquals(date, purchase.getDate());
        assertEquals(time, purchase.getTime());
        assertEquals(category, purchase.getCategory());
        assertEquals(location, purchase.getLocation());
        assertEquals(comment, purchase.getComment());
    }

    /*
    Test the getters and setters for an empty purchase constructor
    */
    @Test
    public void purchaseEmptyConstructorCustomSetters_isCorrect() throws Exception {

        int purchaseId = 41;
        int userId = 99;
        int accountId = 6541;
        double price = 99.99;
        String date = "02/19/2019";
        String time = "12:12";
        String category = "test category";
        String location = "test location";
        String comment = "test comment";

        //Create empty purchase
        Purchase purchase = new Purchase();

        //Use setter to set values
        purchase.setPurchaseId(purchaseId);
        purchase.setUserId(userId);
        purchase.setAccountId(accountId);
        purchase.setPrice(price);
        purchase.setDate(date);
        purchase.setTime(time);
        purchase.setCategory(category);
        purchase.setLocation(location);
        purchase.setComment(comment);

        // Verify Values
        assertEquals(purchaseId, purchase.getPurchaseId());
        assertEquals(userId, purchase.getUserId());
        assertEquals(accountId, purchase.getAccountId());
        assertEquals(price, purchase.getPrice(), 0);
        assertEquals(date, purchase.getDate());
        assertEquals(time, purchase.getTime());
        assertEquals(category, purchase.getCategory());
        assertEquals(location, purchase.getLocation());
        assertEquals(comment, purchase.getComment());
    }

    /*
Test the getters and setters for an empty user constructor
*/
    @Test
    public void userEmptyConstructor_isCorrect() throws Exception {

        //Create empty user
        User user = new User();

        // Verify Values
        assertEquals(-1, user.getId());
        assertEquals(-1, user.getPin_hash());
        assertEquals(-1, user.getPin());
        assertEquals("", user.getSalt());
        assertEquals("", user.getUsername());
        assertEquals(1, user.getFirstTime());
        assertEquals("", user.getBudget());
        assertEquals(0, user.getHasPin());

    }

    /*
    Test the getters and setters for an empty user constructor
    */
    @Test
    public void userCustomConstructor_isCorrect() throws Exception {

        int id = 123456;
        int pin_hash = 98745;
        int pin = 9876;
        String salt = "test salt";
        String username = "testUsername";
        int firstTime = 0;
        String budget = "test";
        int hasPin = 1;

        //Create empty user
        User user = new User(id, username, pin_hash, pin, salt, firstTime, budget, hasPin);

        // Verify Values
        assertEquals(id, user.getId());
        assertEquals(pin_hash, user.getPin_hash());
        assertEquals(pin, user.getPin());
        assertEquals(salt, user.getSalt());
        assertEquals(username, user.getUsername());
        assertEquals(firstTime, user.getFirstTime());
        assertEquals(budget, user.getBudget());
        assertEquals(hasPin, user.getHasPin());
    }

    /*
    Test the getters and setters for an empty user constructor
    */
    @Test
    public void userEmptyConstructorCustomSetters_isCorrect() throws Exception {

        int id = 123456;
        int pin_hash = 98745;
        int pin = 9876;
        String salt = "test salt";
        String username = "testUsername";
        int firstTime = 0;
        String budget = "test";
        int hasPin = 1;

        //Create empty user
        User user = new User();

        //Use setters to set values
        user.setId(id);
        user.setPin_hash(pin_hash);
        user.setPin(pin);
        user.setSalt(salt);
        user.setUsername(username);
        user.setFirstTime(firstTime);
        user.setBudget(budget);
        user.setHasPin(hasPin);

        // Verify Values
        assertEquals(id, user.getId());
        assertEquals(pin_hash, user.getPin_hash());
        assertEquals(pin, user.getPin());
        assertEquals(salt, user.getSalt());
        assertEquals(username, user.getUsername());
        assertEquals(firstTime, user.getFirstTime());
        assertEquals(budget, user.getBudget());
        assertEquals(hasPin, user.getHasPin());
    }

    /*
Test the getters and setters for an empty goal constructor
*/
    @Test
    public void goalEmptyConstructor_isCorrect() throws Exception {

        //Create empty goal
        Goal goal = new Goal();

        // Verify Values
        assertEquals(-1, goal.getUserId());
        assertEquals("", goal.getGoalName());
        assertEquals(-1, goal.getTimePeriod());
        assertEquals(-1, goal.getUnit());
        assertEquals(-1.00, goal.getAmount(),0);
    }

    /*
    Test the getters and setters for an empty goal constructor
    */
    @Test
    public void goalCustomConstructor_isCorrect() throws Exception {

        int userId = 1152;
        String name = "Test Name";
        int timePeriod = 1;
        int unit = 2;
        double amount = 1000.52;


        //Create goal
        Goal goal = new Goal(userId, name, timePeriod, unit, amount);

        // Verify Values
        assertEquals(userId, goal.getUserId());
        assertEquals(name, goal.getGoalName());
        assertEquals(timePeriod, goal.getTimePeriod());
        assertEquals(unit, goal.getUnit());
        assertEquals(amount, goal.getAmount(),0);
    }

    /*
    Test the getters and setters for an empty goal constructor
    */
    @Test
    public void goalEmptyConstructorCustomSetters_isCorrect() throws Exception {

        int userId = 1152;
        String name = "Test Name";
        int timePeriod = 1;
        int unit = 2;
        double amount = 1000.52;

        //Create goal
        Goal goal = new Goal();

        goal.setUserId(userId);
        goal.setGoalName(name);
        goal.setTimePeriod(timePeriod);
        goal.setUnit(unit);
        goal.setAmount(amount);

        // Verify Values
        assertEquals(userId, goal.getUserId());
        assertEquals(name, goal.getGoalName());
        assertEquals(timePeriod, goal.getTimePeriod());
        assertEquals(unit, goal.getUnit());
        assertEquals(amount, goal.getAmount(),0);
    }

}