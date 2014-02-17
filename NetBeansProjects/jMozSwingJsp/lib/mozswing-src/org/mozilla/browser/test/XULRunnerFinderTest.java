package org.mozilla.browser.test;

import java.io.File;

import junit.framework.TestCase;

import org.mozilla.browser.common.XULRunnerFinder;

public class XULRunnerFinderTest extends TestCase {
	
	public void testJavaPreferencesBadVersion()
	{
		File xulHome = 
			XULRunnerFinder.findXULRunner(new XULRunnerFinder.FindMethod[]{
				new XULRunnerFinder.JavaPreferencesFinder()},
				"Bogus version"); //$NON-NLS-1$
		
		assertNull(xulHome);
	}

	/**
	 * This will only work if xul runner has been installed by the JNLPInstaller
	 */
	public void testJavaPreferencesCorrectVersion()
	{
		File xulHome = 
			XULRunnerFinder.findXULRunner(new XULRunnerFinder.FindMethod[]{
				new XULRunnerFinder.JavaPreferencesFinder()},
				"Mozilla XULRunner 1.9_0000000000"); //$NON-NLS-1$
		
		assertNotNull(xulHome);
	}
}
