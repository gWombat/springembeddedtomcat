package fr.gwombat.springembeddedtomcat;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by gWombat.
 *
 * @since 07/02/2018
 */
public class MainApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(MainApplication.class);

	private static final int	DEFAULT_SERVER_PORT		= 8080;
	private static final String	DEFAULT_CONTEXT_PATH	= "";

	private static final String	SERVER_PORT_PROPERTY	= "server.port";
	private static final String	CONTEXT_PATH_PROPERTY	= "server.context-path";

	public static void main(String[] args) throws ServletException, LifecycleException, InterruptedException {
		final Tomcat tomcat = new Tomcat();

		final int port = resolveServerPort();

		tomcat.setBaseDir(createTempDir(port));
		tomcat.setPort(port);
		tomcat.getHost().setAppBase(".");
		tomcat.addWebapp(resolveContextPath(), ".");

		tomcat.start();
		LOGGER.debug("Starting server on port: {}", port);
		tomcat.getServer().await();
	}

	private static String createTempDir(int serverPort) {
		try {
			File tempDir = File.createTempFile("tomcat.", "." + serverPort);
			tempDir.delete();
			tempDir.mkdir();
			tempDir.deleteOnExit();
			return tempDir.getAbsolutePath();
		} catch (IOException ex) {
			throw new RuntimeException("Unable to create tempDir. java.io.tmpdir is set to " + System.getProperty("java.io.tmpdir"), ex);
		}
	}

	private static int resolveServerPort() {
		int serverPort = DEFAULT_SERVER_PORT;
		String argServerPort = System.getProperty(SERVER_PORT_PROPERTY);
		if (argServerPort != null)
			serverPort = Integer.parseInt(System.getProperty(SERVER_PORT_PROPERTY));
		return serverPort;
	}

	private static String resolveContextPath() {
		String contextPath = DEFAULT_CONTEXT_PATH;
		String argContextPath = System.getProperty(CONTEXT_PATH_PROPERTY);
		if (argContextPath != null && !argContextPath.equalsIgnoreCase("/")) {
			contextPath = argContextPath;
		}
		return contextPath;
	}
}
