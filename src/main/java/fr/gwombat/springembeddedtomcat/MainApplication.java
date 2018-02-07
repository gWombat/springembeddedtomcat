package fr.gwombat.springembeddedtomcat;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import javax.servlet.ServletException;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by guillaume.
 *
 * @since 07/02/2018
 */
public class MainApplication {

    public static void main(String[] args) throws ServletException, LifecycleException, InterruptedException {
         final Tomcat tomcat = new Tomcat();

        tomcat.setBaseDir(createTempDir());
        tomcat.setPort(8888);
        tomcat.getHost().setAppBase(".");
        tomcat.addWebapp("", ".");

        tomcat.start();
        tomcat.getServer().await();
    }

    private static String createTempDir() {
        try {
            File tempDir = File.createTempFile("tomcat.", "." + 8888);
            tempDir.delete();
            tempDir.mkdir();
            tempDir.deleteOnExit();
            return tempDir.getAbsolutePath();
        } catch(IOException ex) {
            throw new RuntimeException(
                    "Unable to create tempDir. java.io.tmpdir is set to " + System.getProperty("java.io.tmpdir"),
                    ex
            );
        }
    }
}
