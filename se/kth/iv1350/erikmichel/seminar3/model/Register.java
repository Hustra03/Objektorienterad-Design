package se.kth.iv1350.erikmichel.seminar3.model;

import se.kth.iv1350.erikmichel.seminar3.intergration.ItemDescriptionDTO;

import java.util.ArrayList;
import java.util.List;

public class Register {

	private Sale sale;
	private double registerBalance;

	/*
	 * Creates a new empty object of the Register class
	 * 
	 */
	public Register() {
		List<Item> soldItems = new ArrayList<Item>();
		Sale testSale = new Sale();
		testSale.setSoldItems(soldItems);
		this.sale = testSale;
		this.registerBalance=0;
	}

	/*
	 * Registers ,or adds information about, a specified item to a sale
	 * 
	 * @param foundItem is the ItemDescriptionDTO fetched from
	 * ExternalInventoryDatabase by DatabaseHandler, in turn fetched by Controller
	 * 
	 * @param quantity specifies how many of this item should be added to the sale
	 * 
	 * @param saleIn is the sale the item should be added to
	 * 
	 * @return sale is the sale the item has been added to
	 */
	public Sale registerItem(ItemDescriptionDTO foundItem, int quantity, Sale saleIn) {

		this.sale = saleIn;
		if ((quantity > 0) && (foundItem != null)) {
			Item foundActualItem = new Item(foundItem, quantity);
			this.sale.addItemInfo(foundActualItem);
			this.sale.itemMerger(foundActualItem);
			this.sale.calculateRunningTotal();
		}
		return this.sale;
	}

	/*
	 * Returns sale attribute
	 * 
	 * @param this.sale is the attribute sale, representing the sale currently being
	 * operated on by register
	 */
	public Sale getSale() {
		return this.sale;
	}
	/* Increases registerBalance by the specified amount
	 * @param increase the amount registerBalance is increased by 
	 */
	public void increaseTotalRevenue(double increase)
	{
		this.registerBalance+=increase;
	}

}
