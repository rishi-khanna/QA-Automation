package com.davita.mcoe.executionagent;

import org.openqa.selenium.Platform;

/*  
 * @author Rishi Khanna
 * @version 2.0
 * @Team:DaVita MCOE
 * @Email:rishi.khanna@davita.com
 * @Company:CitiusTech
 */
public class BrowserAgent extends ExecutionAgent {

	private String name;
	private String version;
	private Platform platform;

	public BrowserAgent(String name, String version, Platform platform) {
		this.name = name;
		this.platform = platform;
		this.version = version;
	}

	public String getName() {
		return name;
	}

	public Platform getPlatform() {
		return platform;
	}

	public String getVersion() {
		return version;
	}
}
