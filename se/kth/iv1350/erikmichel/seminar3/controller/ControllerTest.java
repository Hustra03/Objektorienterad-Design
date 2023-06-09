package se.kth.iv1350.erikmichel.seminar3.controller;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.After;

import se.kth.iv1350.erikmichel.seminar3.intergration.DatabaseHandler;
import se.kth.iv1350.erikmichel.seminar3.intergration.ItemLookUpException;
import se.kth.iv1350.erikmichel.seminar3.intergration.SaleDTO;
import se.kth.iv1350.erikmichel.seminar3.intergration.SaleInfoDTO;
import se.kth.iv1350.erikmichel.seminar3.model.Sale;

public class ControllerTest {

	private Controller contr;

	@Before
	public void setUp() {
		DatabaseHandler dbHandler = DatabaseHandler.getDatabaseHandler();
		contr = new Controller(dbHandler);
	}

	@After
	public void tearDown() {
		contr = null;
	}

	@Test
	public void ControllerCreateTest() {
		assertNotNull("Controller Not Created", contr);
	}

	@Test
	public void startSaleTest() {
		assertNull("Sale Not Started", contr.GetSale());
		contr.startSale();
		assertNotNull("Sale Not Started", new Sale(contr.GetSale()));
	}

	/*
	 * Below test needs an item in databaseHandler in order to function
	 */
	@Test
	public void registerItemTest() {
		int itemId=1;
		int quantity = 1;
		contr.startSale();
		assertTrue("Sold Items has item before registration ", contr.GetSale().getSoldItems().isEmpty());
		Sale saleTemp = new Sale();
		SaleDTO sale= new SaleDTO(saleTemp);
		try {
			sale = contr.registerItem(itemId, quantity);
		} 
		catch (RegisterItemException e) {fail("Could not register item with id which exists");
		}
		try {
			sale = contr.registerItem(123123123, quantity);
			fail("Did not throw an exception");
		} 
		catch (RegisterItemException e) {
		}
		try {
			sale = contr.registerItem(420, quantity);
			fail("Did not throw an exception");
		} 
		catch (RegisterItemException e) {
		}

		assertNotNull("Item Not Registered", sale.getSoldItems());
		int registeredId=sale.getSoldItems().get(0).getItemDescriptionDTO().getItemId();
		assertTrue("Item id incorrectly registered",0!=registeredId);
		assertTrue("Wrong item id",itemId==registeredId);

	}

	@Test
	public void endSaleTest() {
		contr.startSale();
		Sale beforeSale= new Sale(contr.GetSale());
		SaleInfoDTO saleInfo = contr.endSale();
		int itemId=1;
		int quantity = 1;
		assertNotNull("Sale Not Ended", saleInfo.getSale());
		assertEquals("Sale Changed By Being Ended",saleInfo.getSale().getSoldItems(),beforeSale.getSoldItems());
		contr.startSale();
		try {
			contr.registerItem(itemId, quantity);
		} catch (RegisterItemException e) {
			fail("Could Not Resiter Item With Known ID");
		}
		SaleDTO beforeWrongItemRegisterAttemptSale = contr.GetSale();
		//New Part Of Test
		try {
			contr.registerItem(123123132, 123123);
			fail("Could Register Item With Invalid ID");
		} catch (RegisterItemException e) {
			assertTrue("Sale Incorrectly Updated Following Failed Item Registration", beforeWrongItemRegisterAttemptSale.getSoldItems().equals(contr.GetSale().getSoldItems()));
		}
		//
		saleInfo = contr.endSale();
		assertNotEquals("Sale returned after end incorrect",saleInfo.getSale(),beforeSale);
	}

	@Test
	public void getDiscountTest() {
		contr.startSale();
		int customerId=0;
		contr.endSale();
		SaleInfoDTO saleInfo = contr.getDiscount(customerId);
		assertNotNull("Discount Not Found",saleInfo.getRecordedDiscounts());

	}

	@Test
	public void recivePaymentTest() {
		
		contr.startSale();
		double amountPayment = 0;
		SaleInfoDTO saleInfo = contr.endSale();
		saleInfo = contr.recivePayment(amountPayment);
		assertNotNull("Payment Not Recived",saleInfo.getCustomerPaymentDTO());
	}

	@Test
	public void sendSaleInfoTest() {

		contr.startSale();
		double amountPayment=0;
		contr.endSale();
		contr.recivePayment(amountPayment);
		contr.sendSaleInfo();
	}

}
