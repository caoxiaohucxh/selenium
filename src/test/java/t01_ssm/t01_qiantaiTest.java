package t01_ssm;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

/**
 *注释
 */
public class t01_qiantaiTest {
    private static SimpleDateFormat ymdhms = new SimpleDateFormat("yyyy-MM-dd--HH_mm_ss");
    //每次截图存放的文件夹名称相同
    private String uniqueName = ymdhms.format(new Date());

    @Test
    public void chromeQiantai() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:\\selenium\\chromedriver_win32\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();

        //设置浏览器最大化
        driver.manage().window().maximize();

        //打开登录页
        driver.get("http://localhost:8080/ssm_zxxqyrlzyglrj/index/preLogin.action");

        //总共等待10秒， 如果10秒后，元素还不存在，就会抛出异常  org.openqa.selenium.NoSuchElementException
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement user = driver.findElement(By.xpath("/html/body/table[4]/tbody/tr/td/form/table/tbody/tr[2]/td[2]/input"));
        WebElement passwd = driver.findElement(By.xpath("/html/body/table[4]/tbody/tr/td/form/table/tbody/tr[3]/td[2]/input"));
        WebElement loginButton = driver.findElement(By.xpath("/html/body/table[4]/tbody/tr/td/form/table/tbody/tr[4]/td/label/input[1]"));

        //输入内容
        user.sendKeys("zhangsan");
        passwd.sendKeys("123");

        //截图
        snapShot(driver, uniqueName, "01登录.png");

        //点击按钮
        loginButton.click();

        //判断出现“用户菜单”，为成功登录
        WebElement welcomeA = driver.findElement(By.xpath("/html/body/table[2]/tbody/tr/td/table/tbody/tr/td[6]/li/table/tbody/tr/td/a"));

        String textValue = welcomeA.getText();

        if (!textValue.equals("用户菜单")) {
            System.err.println("首页显示异常！！！");
            assertEquals(true, false);
        }

        //02 "查看新闻公告详情"
        driver.findElement(By.xpath("/html/body/table[2]/tbody/tr/td/table/tbody/tr/td[2]/li/table/tbody/tr/td/a")).click();

        Thread.sleep(2000);
        //判断显示正常
        String tableValue = driver.findElement(By.xpath("/html/body/table[4]/tbody/tr/td[1]/table[1]/tbody/tr/td[2]/span")).getText();

        if (!tableValue.equals("信息导航")) {
            System.err.println("02查看新闻公告详情显示异常！！！");
            assertEquals(true, false);
        }

        //截图
        snapShot(driver, uniqueName, "02查看新闻公告详情.png");

        //03 查看职位招聘详情
        driver.findElement(By.xpath("/html/body/table[2]/tbody/tr/td/table/tbody/tr/td[4]/table/tbody/tr/td/a")).click();
        Thread.sleep(2000);
        //判断出现“职位招聘信息”，为成功
        String detailValue = driver.findElement(By.xpath("/html/body/table[4]/tbody/tr/td/table[3]/tbody/tr/td[1]")).getText();

        if (!detailValue.equals("职位招聘信息")) {
            System.err.println("03查看职位招聘详情显示异常！！！");
            assertEquals(true, false);
        }
        //截图
        snapShot(driver, uniqueName, "03查看职位招聘详情.png");

        //04 留言板发表留言
        driver.findElement(By.xpath("/html/body/table[2]/tbody/tr/td/table/tbody/tr/td[5]/table/tbody/tr/td/a")).click();
        //输入内容

        driver.findElement(By.xpath("/html/body/table[4]/tbody/tr/td/form/div[1]/table/tbody[2]/tr/th[2]/input")).sendKeys("标题-自动化测试"+uniqueName);
        //点击提交按钮
        driver.findElement(By.xpath("/html/body/table[4]/tbody/tr/td/form/div[2]/input")).click();

        Thread.sleep(2000);
        //判断出现“发表帖子”，为成功
        String jobValue = driver.findElement(By.xpath("/html/body/table[4]/tbody/tr/td/form/div[1]/table/tbody[1]/tr/td/b")).getText();

        if (!jobValue.equals("发表帖子")) {
            System.err.println("04留言板发表留言显示异常！！！");
            assertEquals(true, false);
        }
        //截图
        snapShot(driver, uniqueName, "04留言板发表留言.png");

        //05 查看我的考勤
        WebElement searchBtn = driver.findElement(By.xpath("/html/body/table[2]/tbody/tr/td/table/tbody/tr/td[6]/li/table/tbody/tr/td/a"));
        Actions actionProvider = new Actions(driver);
        // Perform click-and-hold action on the element
        actionProvider.clickAndHold(searchBtn).build().perform();
        //我的考勤 菜单
        driver.findElement(By.xpath("/html/body/table[2]/tbody/tr/td/table/tbody/tr/td[6]/li/div/table/tbody/tr[3]/td/table/tbody/tr/td/a/font")).click();
        Thread.sleep(2000);
        //判断出现“我的考勤”，为成功
        String kValue = driver.findElement(By.xpath("/html/body/table[4]/tbody/tr/td/table[2]/tbody/tr/td/div/table/tbody/tr[1]/td")).getText();
        if (!kValue.equals("我的考勤")) {
            System.err.println("05查看我的考勤显示异常！！！");
            assertEquals(true, false);
        }
        //截图
        snapShot(driver, uniqueName, "05查看我的考勤.png");

        //06 查看我的奖惩
        WebElement jiangliBtn = driver.findElement(By.xpath("/html/body/table[2]/tbody/tr/td/table/tbody/tr/td[6]/li/table/tbody/tr/td/a"));
        actionProvider = new Actions(driver);
        // Perform click-and-hold action on the element
        actionProvider.clickAndHold(jiangliBtn).build().perform();
        //点击 我的奖惩 菜单
        driver.findElement(By.xpath("/html/body/table[2]/tbody/tr/td/table/tbody/tr/td[6]/li/div/table/tbody/tr[4]/td/table/tbody/tr/td/a/font")).click();
        Thread.sleep(2000);
        //判断出现“我的奖惩”，为成功
        String jValue = driver.findElement(By.xpath("/html/body/table[4]/tbody/tr/td/table[2]/tbody/tr/td/div/table/tbody/tr[1]/td")).getText();
        if (!jValue.equals("我的奖惩")) {
            System.err.println("06查看我的奖惩显示异常！！！");
            assertEquals(true, false);
        }
        //截图
        snapShot(driver, uniqueName, "06查看我的奖惩.png");

        //07 查看我的培训
        WebElement peixunBtn = driver.findElement(By.xpath("/html/body/table[2]/tbody/tr/td/table/tbody/tr/td[6]/li/table/tbody/tr/td/a"));
        actionProvider = new Actions(driver);
        // Perform click-and-hold action on the element
        actionProvider.clickAndHold(peixunBtn).build().perform();
        //点击 我的培训 菜单
        driver.findElement(By.xpath("/html/body/table[2]/tbody/tr/td/table/tbody/tr/td[6]/li/div/table/tbody/tr[5]/td/table/tbody/tr/td/a/font")).click();
        Thread.sleep(2000);
        //判断出现“我的培训”，为成功
        String pValue = driver.findElement(By.xpath("/html/body/table[4]/tbody/tr/td/table[2]/tbody/tr/td/div/table/tbody/tr[1]/td")).getText();
        if (!pValue.equals("我的培训")) {
            System.err.println("07查看我的培训显示异常！！！");
            assertEquals(true, false);
        }
        //截图
        snapShot(driver, uniqueName, "07查看我的培训.png");

        //08 查看我的薪资
        WebElement xinziBtn = driver.findElement(By.xpath("/html/body/table[2]/tbody/tr/td/table/tbody/tr/td[6]/li/table/tbody/tr/td/a"));
        actionProvider = new Actions(driver);
        // Perform click-and-hold action on the element
        actionProvider.clickAndHold(xinziBtn).build().perform();
        //点击 我的薪资 菜单
        driver.findElement(By.xpath("/html/body/table[2]/tbody/tr/td/table/tbody/tr/td[6]/li/div/table/tbody/tr[6]/td/table/tbody/tr/td/a/font")).click();
        Thread.sleep(2000);
        //判断出现“我的薪资”，为成功
        String xValue = driver.findElement(By.xpath("/html/body/table[4]/tbody/tr/td/table[2]/tbody/tr/td/div/table/tbody/tr[1]/td")).getText();
        if (!xValue.equals("我的薪资")) {
            System.err.println("08查看我的薪资显示异常！！！");
            assertEquals(true, false);
        }
        //截图
        snapShot(driver, uniqueName, "08查看我的薪资.png");
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
            FileUtils.copyFile(srcFile, new File(".\\snapshot\\前台_" + folderName + "\\" + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}