package se.kth.iv1350.erikmichel.seminar3.intergration;

import java.util.List;

public class DiscountDTO {

	private int discountPercent;

	private List<Integer> applicableIds;

	private List<Integer> applicableItemsId;

	private int minimumItemAmount;

	private int minimumTotalPrice;

	/*
	 * Creates a discount with specified attributes
	 * 
	 * @param discountPercent represents the percentage this discount reduced the
	 * sale by
	 * 
	 * @param applicableId represents which customerIds this sale is valid for
	 * 
	 * @param applicableItems represents which items this discount if valid for
	 * 
	 * @param minimumItemAmount represents the minimum amount of items a sale
	 * requires for this discount to be valid
	 * 
	 * @param minimumTotalPrice represents the minimum total price a sale requires
	 * for this discount to be valid
	 * 
	 */
	public DiscountDTO(int discountPercent, List<Integer> applicableId, List<Integer> applicableItemsId, int minimumItemAmount,
			int minimumTotalPrice) {
		this.discountPercent = discountPercent;
		this.applicableIds = applicableId;
		this.applicableItemsId = applicableItemsId;
		this.minimumItemAmount = minimumItemAmount;
		this.minimumTotalPrice = minimumTotalPrice;
	}

	/*
	 * Creates a discount of 0 percent and with null for any other attribute
	 * 
	 */
	public DiscountDTO() {
		this.discountPercent = 0;
	}

	/*
	 * Returns the discount percent
	 * 
	 * @return discountPercent percentage discount of this discount
	 */
	public int getDiscountPercent() {
		return this.discountPercent;
	}

	/*
	 * Returns the list of applicable ids for this discount
	 * 
	 * @return applicableIds is the list of applicable ids
	 */
	public List<Integer> getApplicableId() {
		return this.applicableIds;
	}

	/*
	 * Returns the items this discount is valid for
	 * 
	 * @return applicableItems contains information about this discounts applicable
	 * items
	 */
	public List<Integer> getApplicableItemId() {
		return this.applicableItemsId;
	}

	/*
	 * Returns the minimum amount of items in a sale this discount is valid for
	 * 
	 */
	public int getMinimumItemAmount() {
		return this.minimumItemAmount;
	}

	/*
	 * Returns the minimum total price for a sale this discount would be valid for
	 * 
	 */
	public int getMinimumTotalPrice() {
		return this.minimumTotalPrice;
	}

}
