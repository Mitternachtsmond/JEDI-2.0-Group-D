/**
 * 
 */
package com.flipkart.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import com.flipkart.bean.EnrolledStudent;
import com.flipkart.constant.NotificationTypeConstant;
import com.flipkart.constant.PaymentModeConstant;
import com.flipkart.constant.SQLQueriesConstant;
import com.flipkart.dao.NotificationDaoInterface;
import com.flipkart.dao.NotificationDaoOperation;
import com.flipkart.utils.DBUtils;

/**
 * @author siddartha.c
 *
 */
public class NotificationOperation implements NotificationInterface{
	
	private static volatile NotificationOperation instance=null;
	NotificationDaoInterface notificationDaoInterface=NotificationDaoOperation.getInstance();
	private NotificationOperation() {}
	
	/**
	 * Method to make NotificationDaoOperation Singleton
	 * @return
	 */
	public static NotificationOperation getInstance()
	{
		if(instance==null)
		{
			// This is a synchronized block, when multiple threads will access this instance
			synchronized(NotificationOperation.class){
				instance=new NotificationOperation();
			}
		}
		return instance;
	}
	
	/**
	 * Method to send notification
	 * @param type: type of the notification to be sent
	 * @param studentId: student to be notified
	 * @param modeOfPayment: payment mode used
	 * @return notification id for the record added in the database
	 */
	
	@Override
	public int sendNotification(String studentId, String modeOfPayment, int amount, String cardNumber,String cardType,String IFSCcode,String accountNumber,String chequeNumber) {
		// TODO Auto-generated method stub
		
		Connection connection=DBUtils.getConnection();
		
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(SQLQueriesConstant.NOTIFICATION);

			ResultSet results=statement.executeQuery();
 
			results.next();
			
			int ans=results.getInt(1);
			
			statement = connection.prepareStatement(SQLQueriesConstant.INSERT_NOTIFICATION);
			
			statement.setInt(1, ans+1);
			statement.setString(2, studentId);
			statement.setString(3, "FEES");
			
			statement.executeUpdate();	
			
			statement = connection.prepareStatement(SQLQueriesConstant.INSERT_PAYMENT);
			
			statement.setInt(1, ans+1);
			statement.setString(2, studentId);
			statement.setString(3, modeOfPayment);
			statement.setInt(4, amount);
			
			statement.executeUpdate();	
			
			if(modeOfPayment.equals("CHEQUE")) {
				statement = connection.prepareStatement(SQLQueriesConstant.CHEQUE);
				
				statement.setInt(1, ans+1);
				statement.setString(2, chequeNumber);
				
				statement.executeUpdate();
			}
			
			if(modeOfPayment.equals("CARD")) {
				statement = connection.prepareStatement(SQLQueriesConstant.CARD);
				
				statement.setInt(1, ans+1);
				statement.setString(2, cardNumber);
				statement.setString(3, cardType);
				
				statement.executeUpdate();
			}
			
			if(modeOfPayment.equals("NETBANKING")) {
				statement = connection.prepareStatement(SQLQueriesConstant.NETBANKING);
				
				statement.setInt(1, ans+1);
				statement.setString(2, IFSCcode);
				statement.setString(3, accountNumber);
				
				statement.executeUpdate();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
						
		return 0;
	}

	@Override
	public UUID getReferenceId(int notificationId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Method to return UUID for a transaction
	 * @param notificationId: notification id added in the database
	 * @return transaction id of the payment
	 */
	
}