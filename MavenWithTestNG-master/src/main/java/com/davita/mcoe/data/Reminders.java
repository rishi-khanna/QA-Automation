package com.davita.mcoe.data;

import com.davita.mcoe.utilities.DateAndTime;
/*  
 * @author Rishi Khanna
 * @version 2.0
 * @Team:DaVita MCOE
 * @Email:rishi.khanna@davita.com
 * @Company:CitiusTech
 */
public enum Reminders {
	
	Appointment("3","This is the Title of the Appointment Reminder",
			"This is the Description of the Appointment reminder",
			DateAndTime.getFormattedModifiedDateAndTime("yyyy-MM-dd'T'hh:mm:ss'Z'", 3)),
	
	Nutrition("6","This is the Title of the Nutrition Reminder",
			"This is the Description of the Nutrition reminder",
			DateAndTime.getFormattedModifiedDateAndTime("yyyy-MM-dd'T'hh:mm:ss'Z'", 3)),
	
	Meds("1","This is the Title of the Meds Reminder",
			"This is the Description of the Meds reminder",
			DateAndTime.getFormattedModifiedDateAndTime("yyyy-MM-dd'T'hh:mm:ss'Z'", 3)),
	
	SelfCare("4","This is the Title of the SelfCare Reminder",
			"This is the Description of the SelfCare reminder",
			DateAndTime.getFormattedModifiedDateAndTime("yyyy-MM-dd'T'hh:mm:ss'Z'", 3)),
	
	Labs("2","This is the Title of the Labs Reminder",
			"This is the Description of the Labs reminder",
			DateAndTime.getFormattedModifiedDateAndTime("yyyy-MM-dd'T'hh:mm:ss'Z'", 3));
	
	private String title;
    private String description;
    private String dueDate;
    private String reminderId;

    Reminders(String reminderId, String title, String description, String dueDate) {
        this.reminderId = reminderId;
    	this.title = title;
        this.description = description;
        this.dueDate = dueDate;
    }

    public String getReminderId() {
        return reminderId;
    }
    
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDueDate() {
        return dueDate;
    }
	
	/*public static HashMap<String, String> AppointmentsReminder = new HashMap<String, String>();
	public static HashMap<String, String> NutritionReminder = new HashMap<String, String>();
	public static HashMap<String, String> MedsReminder = new HashMap<String, String>();
	public static HashMap<String, String> SelfCareReminder = new HashMap<String, String>();
	public static HashMap<String, String> LabsReminder = new HashMap<String, String>();

	static {
		AppointmentsReminder.put("Title", "This is the Title of the Appointment Reminder");
		AppointmentsReminder.put("Description", "This is the Description of the Appointment reminder");
		AppointmentsReminder.put("DueDate", DateAndTime.getFormattedModifiedDateAndTime("yyyy-MM-dd'T'hh:mm:ss'Z'", 3));
	}

	static {
		NutritionReminder.put("Title", "This is the Title of the Nutrition Reminder");
		NutritionReminder.put("Description", "This is the Description of the Nutrition reminder");
		NutritionReminder.put("DueDate", DateAndTime.getFormattedModifiedDateAndTime("yyyy-MM-dd'T'hh:mm:ss'Z'", 3));
	}

	static {
		MedsReminder.put("Title", "This is the Title of the Meds Reminder");
		MedsReminder.put("Description", "This is the Description of the Meds reminder");
		MedsReminder.put("DueDate", DateAndTime.getFormattedModifiedDateAndTime("yyyy-MM-dd'T'hh:mm:ss'Z'", 3));
	}

	static {
		SelfCareReminder.put("Title", "This is the Title of the SelfCare Reminder");
		SelfCareReminder.put("Description", "This is the Description of the SelfCare reminder");
		SelfCareReminder.put("DueDate", DateAndTime.getFormattedModifiedDateAndTime("yyyy-MM-dd'T'hh:mm:ss'Z'", 3));
	}

	static {
		LabsReminder.put("Title", "This is the Title of the Labs Reminder");
		LabsReminder.put("Description", "This is the Description of the Labs reminder");
		LabsReminder.put("DueDate", DateAndTime.getFormattedModifiedDateAndTime("yyyy-MM-dd'T'hh:mm:ss'Z'", 3));
	}*/
}
