import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class t02_baiduTest {

    private static SimpleDateFormat ymdhms = new SimpleDateFormat("yyyyy-MM-dd--HH_mm_ss");
    //新添加的用户名与每次截图存放的文件夹名称相同
    private String name = ymdhms.format(new Date());

    @Test
    public void chromeBaidu() {
        System.setProperty("webdriver.chrome.driver", "D:\\selenium\\chromedriver_win32\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();

        //设置浏览器最大化
        driver.manage().window().maximize();

        //打开首页
        driver.get("https://www.baidu.com/");

        //总共等待10秒， 如果10秒后，元素还不存在，就会抛出异常  org.openqa.selenium.NoSuchElementException
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //获取搜索输入框
        WebElement searchInput = driver.findElement(By.name("wd"));
        //获取搜索按钮
        WebElement searchButton = driver.findElement(By.id("su"));

        //输入内容
        searchInput.sendKeys("自动化测试");

        snapShot(driver, name, "01首页.png");

        //点击按钮
        searchButton.click();

        //关闭浏览器
        //driver.quit();
    }

    /**
     * 截图方法
     *
     * @param driver
     * @param folderName 保存文件夹名
     * @param fileName   文件名
     */
    public void snapShot(ChromeDriver driver, String folderName, String fileName) {
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(srcFile, new File("d:" + folderName + "\\" + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}