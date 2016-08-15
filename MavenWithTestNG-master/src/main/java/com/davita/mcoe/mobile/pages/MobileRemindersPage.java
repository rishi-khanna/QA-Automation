package com.davita.mcoe.mobile.pages;

import org.openqa.selenium.Point;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import static com.davita.mcoe.mobile.locators.MobileRemindersPageLocators.*;

import java.util.List;

import com.davita.mcoe.exceptions.WaitException;
import com.davita.mcoe.extendable.locator.AppConfig;

import io.appium.java_client.MobileBy;

/*  
 * @author Rishi Khanna
 * @version 2.0
 * @Team:DaVita MCOE
 * @Email:rishi.khanna@davita.com
 * @Company:CitiusTech
 */
public class MobileRemindersPage extends MobileNavigationBarPage {

	public MobileRemindersPage(WebDriver driver) throws WaitException {
		super(driver);
		// Assert.assertTrue(mobileActions.getVisibiltyOfElementLocatedBy(BYNAVIGATIONUP));
		try {
			Thread.sleep(8000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public List<String> getTitleOfReminder() throws WaitException {
		return mobileActions.getWebElementsTextInList(PRESENCE, BYREMINDERSTITLE);
	}

	public List<String> getDescriptionOfReminder() throws WaitException {
		return mobileActions.getWebElementsTextInList(PRESENCE, BYREMINDERSDESC);
	}

	public MobileRemindersPage refreshRemindersPage() {
		int xstart = mobileActions.getHorizontalScrollStartPoint(0.50);

		int ystart = mobileActions.getVerticalScrollStartPoint(0.30);
		int yend = mobileActions.getVerticalScrollEndPoint(0.80);

		mobileActions.swipeOnScreen(xstart, ystart, xstart, yend, 3000);
		return this;
	}

	public boolean validateEmptyRemindersListMessage(String emptyRemindersListMessage) throws WaitException {
		return mobileActions.getVisibiltyOfElementLocatedBy(VISIBILITY,
				MobileBy.AccessibilityId(emptyRemindersListMessage));
	}

	public String getEmptyRemindersListFirstMessage() throws WaitException {
		return mobileActions.getText(VISIBILITY, BYNOREMINDERSFIRSTTEXT);
	}

	public String getEmptyRemindersListSecondMessage() throws WaitException {
		return mobileActions.getText(VISIBILITY, BYNOREMINDERSSECONDTEXT);
	}

	public Point scrollToText(String text) throws WaitException {
		Point location;
		if ("Android".equals(AppConfig.getPlatform())) {
			location = mobileActions.androidScrollToText(VISIBILITY,text);
		} else {
			location = mobileActions.iOSScrollToText(VISIBILITY,text);
		}
		return location;
	}

	public MobileRemindersPage tapToAcknowledgeReminder() throws TimeoutException, WaitException {
		mobileActions.tap(VISIBILITY, BYACKNOWLEDGEREMINDER);
		return this;
	}

	public MobileRemindersPage waitFor5secsForReminderToBeAcknowledged() throws InterruptedException {
		Thread.sleep(5000);
		return this;
	}

	public MobileRemindersPage tapRemindersCompletedTab() throws TimeoutException, WaitException, InterruptedException {
		mobileActions.tap(VISIBILITY, BYIOSREMINDERSCOMPLETEDTAB);
		return this;
	}

	public MobileRemindersPage tapToUndoAcknowledgeReminder() throws TimeoutException, WaitException {
		mobileActions.tap(VISIBILITY, BYACKNOWLEDGEREMINDER);
		return this;
	}

	public MobileRemindersPage acceptUndoCompleteReminderDialogue() throws WaitException {
		mobileActions.switchToAcceptAlert(VISIBILITY);
		return this;
	}

	public MobileRemindersPage tapReminderToDoTab() throws TimeoutException, WaitException, InterruptedException {
		mobileActions.tap(VISIBILITY, BYIOSREMINDERSTODOTAB);
		return this;
	}
}
