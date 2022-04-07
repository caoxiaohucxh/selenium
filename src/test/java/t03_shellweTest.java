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
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class t03_shellweTest {
    private static SimpleDateFormat ymdhms = new SimpleDateFormat("yyyy-MM-dd--HH_mm_ss");
    //每次截图存放的文件夹名称相同
    private String name = ymdhms.format(new Date());

    @Test
    public void chromeZhaopinManage() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:\\selenium\\chromedriver_win32\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();

        //设置浏览器最大化
        driver.manage().window().maximize();

        //打开首页
        driver.get("http://localhost:8089");

        //总共等待10秒， 如果10秒后，元素还不存在，就会抛出异常  org.openqa.selenium.NoSuchElementException
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement user = driver.findElement(By.xpath("/html/body/div/div/div[2]/form/div[2]/div[1]/div/div[1]/input"));
        WebElement passwd = driver.findElement(By.xpath("/html/body/div/div/div[2]/form/div[2]/div[2]/div/div/input"));
        WebElement loginButton = driver.findElement(By.xpath("/html/body/div/div/div[2]/form/div[3]/button[1]"));

        //输入内容
        user.sendKeys("admin");
        passwd.sendKeys("123456");

        //截图
        snapShot(driver, name, "01首页.png");

        //点击按钮
        loginButton.click();

        //判断出现“Welcome!! admin 202200”，为成功登录
        WebElement welcomeA = driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div[3]/div[2]/div/div[2]/div[1]/div/div[2]/h1"));

        String textValue = welcomeA.getText();

        if (!textValue.equals("Welcome!! admin 202200")) {
            System.err.println("首页显示异常！！！");
            assertEquals(true, false);
        }

        //02 打开菜单    "简历筛选录用"
        driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div[1]/div/ul/span/div[2]/li/div/span")).click();
        driver.findElement(By.xpath("/html/body/div/div/div[1]/div/div[1]/div/ul/span/div[2]/li/ul/div[1]/li/div/span")).click();
        driver.findElement(By.xpath("/html/body/div/div/div[1]/div/div[1]/div/ul/span/div[2]/li/ul/div[1]/li/ul/div[1]/li/div/span")).click();
        driver.findElement(By.xpath("/html/body/div/div/div[1]/div/div[1]/div/ul/span/div[2]/li/ul/div[1]/li/ul/div[1]/li/ul/div[3]/a/li/span")).click();

        Thread.sleep(2000);
        //判断简历投递表显示正常
        WebElement table = driver.findElement(By.xpath("/html/body/div/div/div[1]/div/div[3]/div[2]/div/div[2]/div[1]"));

        String tableValue = table.getText();

        if (!tableValue.equals("简历投递表")) {
            System.err.println("02简历筛选录用表显示异常！！！");
            assertEquals(true, false);
        }

        //截图
        snapShot(driver, name, "02简历筛选录用表.png");

        //03 打开简历详情
        driver.findElement(By.xpath("/html/body/div/div/div[1]/div/div[3]/div[2]/div/div[2]/div[2]/div[3]/table/tbody/tr[1]/td[13]/div/button")).click();
        Thread.sleep(2000);
        //判断出现“简历详情”，为成功
        WebElement detail = driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div[3]/div[2]/div/div[3]/div/div[1]/span"));

        String detailValue = detail.getText();

        if (!detailValue.equals("简历详情")) {
            System.err.println("03简历详情显示异常！！！");
            assertEquals(true, false);
        }
        //截图
        snapShot(driver, name, "03简历详情.png");

        //关闭， 简历详情  页面
        driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div[3]/div[2]/div/div[3]/div/div[3]/span/button[1]")).click();

        //04 打开菜单    "职位空缺发布"
        driver.findElement(By.xpath("/html/body/div/div/div[1]/div/div[1]/div/ul/span/div[2]/li/ul/div[1]/li/ul/div[1]/li/ul/div[1]/a/li/span")).click();

        Thread.sleep(2000);
        //判断出现“职位空缺管理”，为成功
        WebElement job = driver.findElement(By.xpath("/html/body/div/div/div[1]/div/div[3]/div[2]/div/div[1]/div[1]/div[1]"));

        String jobValue = job.getText();

        if (!jobValue.equals("职位空缺管理")) {
            System.err.println("04职位空缺发布显示异常！！！");
            assertEquals(true, false);
        }
        //截图
        snapShot(driver, name, "04职位空缺发布-列表.png");

        //04-1 打开菜单    "职位空缺发布"->空缺信息调整栏
        driver.findElement(By.xpath("/html/body/div/div/div[1]/div/div[3]/div[2]/div/div[2]/div/div/button[3]")).click();

        Thread.sleep(2000);
        //输入内容
        //空缺职位名称
        driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div[1]/div[3]/div[2]/div/div[5]/div[2]/div[2]/div[1]/form/div/div[1]/div[1]/div/div/div/input")).sendKeys("test");
        //空缺职位部门
        driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div[1]/div[3]/div[2]/div/div[5]/div[2]/div[2]/div[1]/form/div/div[1]/div[2]/div/div/div/div/input")).sendKeys("1");
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[1]/ul/li")).click();
        //招聘性质
        driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div[1]/div[3]/div[2]/div/div[5]/div[2]/div[2]/div[1]/form/div/div[3]/div/div[1]/label[1]/span[2]")).click();

        //截图
        snapShot(driver, name, "04职位空缺发布-发布.png");

        //发布
        driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div[1]/div[3]/div[2]/div/div[5]/div[2]/div[2]/div[1]/form/div/div[5]/div/button[1]")).click();

        Thread.sleep(2000);
        //调用alert对象的accept()方法，模拟鼠标单击alert弹窗上的“确定”按钮
        //以便关闭alert窗  accept 确认 dismiss 取消
        driver.switchTo().alert().accept();

        Thread.sleep(2000);
        //05 打开菜单    "招聘面试管理"
        driver.findElement(By.xpath("/html/body/div/div/div[1]/div/div[1]/div/ul/span/div[2]/li/ul/div[1]/li/ul/div[1]/li/ul/div[2]/a/li/span")).click();
        Thread.sleep(2000);
        //判断出现“招聘流程管理”，为成功
        WebElement flow = driver.findElement(By.xpath("/html/body/div/div/div[1]/div/div[3]/div[2]/div/div[1]/div[1]/div[1]"));

        String flowValue = flow.getText();

        if (!flowValue.equals("招聘流程管理")) {
            System.err.println("05招聘面试管理显示异常！！！");
            assertEquals(true, false);
        }
        //截图
        snapShot(driver, name, "05招聘面试管理.png");

        //渠道管理
        driver.findElement(By.xpath("/html/body/div/div/div[1]/div/div[1]/div/ul/span/div[2]/li/ul/div[1]/li/ul/div[2]/li/div/span")).click();
        //06企业背景调查管理
        driver.findElement(By.xpath("/html/body/div/div/div[1]/div/div[1]/div/ul/span/div[2]/li/ul/div[1]/li/ul/div[2]/li/ul/div[2]/a/li/span")).click();
        Thread.sleep(2000);
        //判断出现“背景调查管理”，为成功
        String backValue = driver.findElement(By.xpath("/html/body/div/div/div[1]/div/div[1]/div/ul/span/div[2]/li/ul/div[1]/li/ul/div[2]/li/ul/div[2]/a/li/span")).getText();
        if (!backValue.equals("背景调查管理")) {
            System.err.println("06企业背景调查管理显示异常！！！");
            assertEquals(true, false);
        }
        //截图
        snapShot(driver, name, "06企业背景调查管理.png");

        //07统计报表
        driver.findElement(By.xpath("/html/body/div/div/div[1]/div/div[1]/div/ul/span/div[2]/li/ul/div[1]/li/ul/div[3]/a/li/span")).click();
        Thread.sleep(2000);
        //判断出现“统计报表”，为成功
        String reportValue = driver.findElement(By.xpath("/html/body/div/div/div[1]/div/div[3]/div[2]/div/div[1]/div[1]/div[1]")).getText();
        if (!reportValue.equals("统计报表")) {
            System.err.println("07统计报表显示异常！！！");
            assertEquals(true, false);
        }
        //截图
        snapShot(driver, name, "07统计报表.png");

        //08新增数据字典
        //基础功能
        driver.findElement(By.xpath("/html/body/div/div/div[1]/div/div[1]/div/ul/span/div[1]/li/div/span")).click();
        driver.findElement(By.xpath("/html/body/div/div/div[1]/div/div[1]/div/ul/span/div[1]/li/ul/div[1]/li/div/span")).click();
        driver.findElement(By.xpath("/html/body/div/div/div[1]/div/div[1]/div/ul/span/div[1]/li/ul/div[1]/li/ul/div[1]/a/li/span")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div[3]/div[2]/div/div[2]/div/div[2]/button[2]")).click();
        //判断出现“创建一条新的字典规则”，为成功
        String paramCreatValue = driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div[3]/div[2]/div/div[5]/div/div[1]/span")).getText();
        if (!paramCreatValue.equals("创建一条新的字典规则")) {
            System.err.println("08新增数据字典显示异常！！！");
            assertEquals(true, false);
        }
        //输入内容
        driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div[3]/div[2]/div/div[5]/div/div[2]/form/div/div[1]/div/div/input")).sendKeys("test");
        driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div[3]/div[2]/div/div[5]/div/div[2]/form/div/div[2]/div/div/input")).sendKeys("test");
        driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div[3]/div[2]/div/div[5]/div/div[2]/form/div/div[3]/div/div/input")).sendKeys("test");
        driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div[3]/div[2]/div/div[5]/div/div[2]/form/div/div[5]/div/div/input")).sendKeys("test");
        driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div[3]/div[2]/div/div[5]/div/div[2]/form/div/div[6]/div/div/input")).sendKeys("test");
        driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div[3]/div[2]/div/div[5]/div/div[2]/form/div/div[8]/div/div/input")).sendKeys("test");
        //截图
        snapShot(driver, name, "08新增数据字典.png");
        Thread.sleep(2000);
        //点击创建按钮
        driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div[3]/div[2]/div/div[5]/div/div[3]/span/button[2]")).click();
        //关闭窗口
        driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div[3]/div[2]/div/div[5]/div/div[1]/button/i")).click();

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
            FileUtils.copyFile(srcFile, new File(".\\snapshot\\测试" + folderName + "\\" + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}