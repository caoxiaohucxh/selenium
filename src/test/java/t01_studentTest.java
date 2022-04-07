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

public class t01_studentTest {

    private static SimpleDateFormat ymdhms = new SimpleDateFormat("yyyyy-MM-dd--HH_mm_ss");
    //新添加的用户名与每次截图存放的文件夹名称相同
    private String name = ymdhms.format(new Date());

    @Test
    public void chromeSession() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:\\selenium\\chromedriver_win32\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();

        //设置浏览器最大化
        driver.manage().window().maximize();

        //打开系统登录页
        driver.get("http://localhost:8080/AttendanceManager");

        //暂停1秒，等待页面加载
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
        Thread.sleep(3000);

        //获取用户名输入框
        WebElement username = driver.findElement(By.name("username"));
        //获取密码输入框
        WebElement password = driver.findElement(By.name("password"));
        //获取登录按钮
        WebElement SubmitButton = driver.findElement(By.name("Submit"));

        //输入用户名、密码
        username.sendKeys("admin");
        password.sendKeys("admin");

        snapShot(driver, name, "01登录.png");

        //点击登录按钮
        SubmitButton.click();

        //暂停3秒，等待页面加载
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
        Thread.sleep(3000);

        //获取“您好,admin”的连接
        WebElement welcomeA = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div/ul[1]/li[1]/a"));

        String value = welcomeA.getAttribute("href");
        String textValue = welcomeA.getText();

        if (textValue.equals("您好,admin")) {
            System.out.println("用户登录成功，首页显示正常！！！");
        }

        snapShot(driver, name, "02首页.png");

        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div[2]/div[3]/h2")).click();
        Thread.sleep(2000);
        snapShot(driver, name, "03菜单.png");
        //暂停3秒，等待页面加载
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
        Thread.sleep(3000);

        //获取“用户管理”的菜单
        WebElement userMenu = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div[2]/div[4]/ul/li/ul/li/div/a"));

        //点击“用户管理”的菜单
        userMenu.click();

        //暂停3秒，等待页面加载
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
        Thread.sleep(3000);
        snapShot(driver, name, "04用户管理列表.png");

        //获取“添加”按钮
        WebElement addBtn = driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div[2]/div[2]/div[2]/div[1]/ul/li[1]/a"));

        addBtn.click();

        //暂停3秒，等待页面加载
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
        Thread.sleep(3000);
        snapShot(driver, name, "05用户管理添加.png");

        //获取用户名输入框
        WebElement usernameAdd = driver.findElement(By.name("username"));
        //获取密码输入框
        WebElement passwordAdd = driver.findElement(By.name("password"));
        //获取联系方式输入框
        WebElement phone = driver.findElement(By.name("phone"));
        //获取所在宿舍输入框
        WebElement room = driver.findElement(By.name("room"));
        //获取家庭住址输入框
        WebElement address = driver.findElement(By.name("address"));

        //输入数据，每次数据按秒生成
        usernameAdd.sendKeys("AutoTest " + name);
        passwordAdd.sendKeys("AutoTest " + name);
        phone.sendKeys("AutoTest " + name);
        room.sendKeys("AutoTest " + name);
        address.sendKeys("AutoTest " + name);

        //暂停3秒，等待页面加载
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
        Thread.sleep(3000);

        //获取“提交”按钮
        WebElement submitBtn = driver.findElement(By.xpath("/html/body/div[12]/div[2]/div/form/div[2]/ul/li[1]/div/div/button"));
        submitBtn.click();
        Thread.sleep(3000);
        snapShot(driver, name, "06添加成功.png");

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