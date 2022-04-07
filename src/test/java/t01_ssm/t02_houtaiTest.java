package t01_ssm;

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

public class t02_houtaiTest {
    private static SimpleDateFormat ymdhms = new SimpleDateFormat("yyyy-MM-dd--HH_mm_ss");
    //每次截图存放的文件夹名称相同
    private String name = ymdhms.format(new Date());

    @Test
    public void chromeHoutai1() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:\\selenium\\chromedriver_win32\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        //设置浏览器最大化
        driver.manage().window().maximize();

        //打开登录页
        driver.get("http://localhost:8080/ssm_zxxqyrlzyglrj/admin/index.jsp");
        //总共等待10秒， 如果10秒后，元素还不存在，就会抛出异常  org.openqa.selenium.NoSuchElementException
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement user = driver.findElement(By.xpath("/html/body/div[2]/div/form/div[1]/div/input"));
        WebElement passwd = driver.findElement(By.xpath("/html/body/div[2]/div/form/div[2]/div/input"));
        WebElement loginButton = driver.findElement(By.xpath("/html/body/div[2]/div/form/div[3]/div/input[1]"));
        //输入内容
        user.sendKeys("admin");
        passwd.sendKeys("admin");
        //截图
        snapShot(driver, name, "01登录.png");
        //点击按钮
        loginButton.click();

        //判断出现“欢迎访问人力资源管理系统”，为成功登录
        String textValue = driver.findElement(By.xpath("/html/body/header/div/div/a")).getText();
        if (!textValue.equals("欢迎访问人力资源管理系统")) {
            System.err.println("首页显示异常！！！");
            assertEquals(true, false);
        }

        //02 "新增管理员信息"
        driver.findElement(By.xpath("/html/body/aside/div/dl[1]/dt")).click();
        //新增管理员信息
        driver.findElement(By.xpath("/html/body/aside/div/dl[1]/dd/ul/li[1]/a")).click();
        Thread.sleep(2000);

        //进入到iframe标签
        driver.switchTo().frame(1);

        WebElement userName = driver.findElement(By.xpath("/html/body/article/form/div[1]/div/input"));
        WebElement password = driver.findElement(By.xpath("/html/body/article/form/div[2]/div/input"));
        WebElement realName = driver.findElement(By.xpath("/html/body/article/form/div[3]/div/input"));
        WebElement phone = driver.findElement(By.xpath("/html/body/article/form/div[4]/div/input"));
        String uName = "zdh"+name;
        userName.sendKeys(uName);
        password.sendKeys("zdh");
        realName.sendKeys("自动化测试");
        phone.sendKeys("111111");

        //截图
        snapShot(driver, name, "02新增管理员信息.png");

        driver.findElement(By.xpath("/html/body/article/form/div[5]/div/button[1]")).click();
        Thread.sleep(2000);

        //切回到最外层，即主html中
        driver.switchTo().defaultContent();
        //判断显示正常
        String tableValue = driver.findElement(By.xpath("/html/body/section/div[1]/div[1]/ul/li[2]/span")).getText();

        if (!tableValue.equals("新增管理员")) {
            System.err.println("02新增管理员异常！！！");
            assertEquals(true, false);
        }

        //03 管理员列表
        driver.findElement(By.xpath("/html/body/aside/div/dl[1]/dd/ul/li[2]/a")).click();
        Thread.sleep(2000);

        //判断出现“管理员列表”，为成功
        String detailValue = driver.findElement(By.xpath("/html/body/section/div[1]/div[1]/ul/li[3]/span")).getText();

        //进入到iframe标签
        driver.switchTo().frame(2);
        //判断新增的用户名被查出来
        String userValue = driver.findElement(By.xpath("/html/body/div/table/tbody/tr[1]/td[1]")).getText();

        if (!detailValue.equals("管理员列表") && userValue.equals(uName)) {
            System.err.println("03管理员列表显示异常！！！");
            assertEquals(true, false);
        }
        //截图
        snapShot(driver, name, "03管理员列表.png");

        //切回到最外层，即主html中
        driver.switchTo().defaultContent();

        //04 "新增部门"
        driver.findElement(By.xpath("/html/body/aside/div/dl[3]/dt")).click();
        //新增部门
        driver.findElement(By.xpath("/html/body/aside/div/dl[3]/dd/ul/li[1]/a")).click();
        Thread.sleep(2000);

        //进入到iframe标签
        driver.switchTo().frame(3);
        WebElement deptName = driver.findElement(By.xpath("/html/body/article/form/div[1]/div/input"));
        WebElement people = driver.findElement(By.xpath("/html/body/article/form/div[2]/div/input"));
        WebElement range = driver.findElement(By.xpath("/html/body/article/form/div[3]/div/input"));
        WebElement addr = driver.findElement(By.xpath("/html/body/article/form/div[4]/div/input"));
        WebElement tel = driver.findElement(By.xpath("/html/body/article/form/div[5]/div/input"));
        WebElement date = driver.findElement(By.xpath("/html/body/article/form/div[6]/div/input"));
        WebElement re = driver.findElement(By.xpath("/html/body/article/form/div[7]/div/input"));
        String dName = "zdh"+name;
        deptName.sendKeys(dName);
        people.sendKeys("自动化");
        range.sendKeys("自动化测试");
        addr.sendKeys("自动化测试");
        tel.sendKeys("111111");
        //执行js
        driver.executeScript("document.getElementById('addtime').removeAttribute('readonly')");
        date.sendKeys("2022-03-29");
        re.sendKeys("自动化测试备注");

        //截图
        snapShot(driver, name, "04新增部门.png");

        driver.findElement(By.xpath("/html/body/article/form/div[8]/div/button[1]")).click();
        Thread.sleep(2000);

        //切回到最外层，即主html中
        driver.switchTo().defaultContent();
        //判断显示正常
        String dValue = driver.findElement(By.xpath("/html/body/section/div[1]/div[1]/ul/li[4]/span")).getText();

        if (!dValue.equals("新增部门")) {
            System.err.println("04新增部门异常！！！");
            assertEquals(true, false);
        }

        //05 部门列表
        driver.findElement(By.xpath("/html/body/aside/div/dl[3]/dd/ul/li[2]/a")).click();
        Thread.sleep(2000);
        //判断出现“部门列表”，为成功
        String deptValue = driver.findElement(By.xpath("/html/body/section/div[1]/div[1]/ul/li[5]/span")).getText();
        //进入到iframe标签
        driver.switchTo().frame(4);
        //判断新增的用户名被查出来
        String depValue = driver.findElement(By.xpath("/html/body/div/table/tbody/tr[1]/td[1]")).getText();

        if (!deptValue.equals("部门列表") && depValue.equals(dName)) {
            System.err.println("05部门列表显示异常！！！");
            assertEquals(true, false);
        }
        //截图
        snapShot(driver, name, "05部门列表.png");

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
            FileUtils.copyFile(srcFile, new File(".\\snapshot\\后台1_" + folderName + "\\" + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}