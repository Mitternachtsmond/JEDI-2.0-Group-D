package com.flipkart.constant;

/**
 * 
 * @author siddartha.c
 * Enumeration class for Mode of Payments
 *
 */
public enum PaymentModeConstant {
	
	CARD,NET_BANKING,CHEQUE,CASH;
	
	/**
	 * Method to get Mode of Payment
	 * @param value
	 * @return Mode of Payment
	 */
	public static String getPaymentMode(int value)
	{
		switch(value)
		{
			case 1:
				return "CARD";
			case 2:
				return "NETBANKING";
			case 3:
				return "CHEQUE";
			case 4:
				return "CASH";
			default:
				return null;
				
		}
			
	}
	
}