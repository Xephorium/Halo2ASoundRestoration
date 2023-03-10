package io.utility;

/* Halo 2A Sound Restoration                 Chris Cruzen
 * OperatingSystemUtility                      03.10.2023
 *
 * OperatingSystemUtility provides convenience methods to
 * identify the OS of the current system.
 */


public class OperatingSystemUtility {
	
	
	/*--- OS Check Methods ---*/
	
	public static boolean isWindows() {
		String operatingSystem = System.getProperty("os.name").toLowerCase();
		return (operatingSystem.contains("win"));
	}
	
	public static boolean isMac() {
		String operatingSystem = System.getProperty("os.name").toLowerCase();
		return (operatingSystem.contains("mac"));
	}
	
	public static boolean isUnix() {
		String operatingSystem = System.getProperty("os.name").toLowerCase();
		return (operatingSystem.contains("nix"));
	}
}
