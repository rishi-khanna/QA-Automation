package com.davita.mcoe.page.base;

import org.apache.log4j.Logger;

import com.davita.mcoe.comparator.Comparator;
import com.davita.mcoe.utilities.Logg;
import com.davita.mcoe.utilities.Utilities;

/*  
 * @author Rishi Khanna
 * @version 2.0
 * @Team:DaVita MCOE
 * @Email:rishi.khanna@davita.com
 * @Company:CitiusTech
 */
public class PageBase {

	protected static final String VISIBILITY = "visibility";
	protected static final String PRESENCE = "presence";
	protected static final String CLICKABILITY = "clickability";
	protected static final String NOTREQUIRED = "notrequired";
	protected static final Utilities UTIL = new Utilities();
	protected static final Comparator COMPARE = new Comparator();
	private static final Logger LOGGER = Logg.createLogger();
}