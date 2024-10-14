package DriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverManager {
    private static volatile DriverManager instance;
    private static ThreadLocal<WebDriver> tDriver = new ThreadLocal<WebDriver>();

    private DriverManager() {

    }

    private void initDriver(String browser) {
        switch (browser) {
            case "chrome":
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--remote-allow-origins=*");
                tDriver.set(new ChromeDriver(options));
                break;
            case "firefox":
                tDriver.set(new FirefoxDriver());
                break;
            case "edge":
                tDriver.set(new EdgeDriver());
                break;

            default:
                throw new IllegalArgumentException("Unsupported Browser:" + browser);
        }
    }

    public static DriverManager getInstance(String browser) {
        if (instance == null) {
            synchronized (DriverManager.class) {
                if (instance == null) {
                    instance = new DriverManager();
                }
            }
        }
        if (tDriver.get() == null) {
            instance.initDriver(browser);
        }
        return instance;
    }

    public WebDriver getDriver() {
        return tDriver.get();
    }

    public static void quitBrowser()
    {
        if(tDriver.get()!=null) {
            tDriver.get().quit();
            tDriver.remove();
        }
    }

}

