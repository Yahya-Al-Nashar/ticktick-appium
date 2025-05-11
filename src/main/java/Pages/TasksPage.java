package Pages;

import Factory.WebDriverFactory;
import Interactions.Button;
import Interactions.Textbox;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

//import static Factory.WebDriverFactory.driver;

public class TasksPage {
    Button AddTask = new Button(AppiumBy.id("com.ticktick.task:id/add_task_btn"));
    Textbox TaskName = new Textbox(AppiumBy.id("com.ticktick.task:id/et_title"), "Task Name");
    Textbox TaskDescription = new Textbox(AppiumBy.id("com.ticktick.task:id/et_content"), "Task Description");
    Button ConfirmTask = new Button(AppiumBy.id("com.ticktick.task:id/iv_save"));
    Button Listmenu = new Button(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.ImageButton\").instance(0)"));
    public static Button ListButton = new Button(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.ImageButton\").instance(0)"));

    //Textbox TaskNameField = new Textbox(AppiumBy.androidUIAutomator("new UiSelector().text(\"yy\")"));

    public WebElement FindTask(String taskName) {
        // Get all task containers
        List<WebElement> containers = WebDriverFactory.getDriver().findElements(AppiumBy.xpath(
                "//androidx.recyclerview.widget.RecyclerView[@resource-id='com.ticktick.task:id/list']" +
                        "/android.view.ViewGroup"
        ));

        for (WebElement container : containers) {

                WebElement nameElement = container.findElement(AppiumBy.id("com.ticktick.task:id/title"));
                String name = nameElement.getText();

                if (name.equals(taskName)) {
                   return container;
                }

        }
        return null;
    }
    public void clickTaskCheckbox(String taskName) {
        WebElement task = FindTask(taskName);
        if (task != null) {
            WebElement checkbox = task.findElement(AppiumBy.id("com.ticktick.task:id/checkbox"));
            checkbox.click();
        } else {
            System.out.println("Task not found: " + taskName);
        }
    }
    public void editTask(String taskName) {
        WebElement task = FindTask(taskName);
        if (task != null) {
            task.click();
        } else {
            System.out.println("Task not found: " + taskName);
        }
    }
    public void CreateTask(String Name , String Description){
        AddTask.click();
        TaskName.setText(Name);
        TaskDescription.setText(Description);
        ConfirmTask.click();
    }
    public void ChooseList(String ListName) throws InterruptedException {
        Listmenu.click();
        List<WebElement> containers = WebDriverFactory.getDriver().findElements(AppiumBy.xpath(
                "//androidx.recyclerview.widget.RecyclerView[@resource-id='com.ticktick.task:id/recyclerView']/android.widget.RelativeLayout"
        ));

        for (WebElement container : containers) {
            WebElement nameElement = container.findElement(AppiumBy.id("com.ticktick.task:id/name"));
            String name = nameElement.getText();
            if (name.equals(ListName)) {
                nameElement.click();
                break;
            }
        }
    }

    public void assertTaskNameField(String taskName)
    {
        String actualName = WebDriverFactory.getDriver().findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"" + taskName + "\")")).getText();
        Assert.assertEquals(actualName, taskName, "Task name does not match the expected value.");
    }

//    /**
//     * Checks the given task and asserts it moves under the "COMPLETED" separator.
//     *
//     * @param taskName exact text of the task, e.g. "T2"
//     */
//    public void assertTaskMovedUnderCompleted(String taskName) {
//        // 1) click the checkbox
//        //clickTaskCheckbox(taskName);
//
//        // 2) wait until the task shows up under the "COMPLETED" separator
//        WebDriverWait wait = new WebDriverWait(WebDriverFactory.getDriver(), Duration.ofSeconds(5));
//        boolean moved = wait.until((ExpectedCondition<Boolean>) d -> {
//            // 2a) find all items in the RecyclerView
//            List<WebElement> allItems = d.findElements(AppiumBy.xpath(
//                    "//androidx.recyclerview.widget.RecyclerView[@resource-id='com.ticktick.task:id/list']" +
//                            "/android.view.ViewGroup"
//            ));
//
//            // 2b) find the index of the "COMPLETED" separator
//            int sepIndex = -1;
//            for (int i = 0; i < allItems.size(); i++) {
//                List<WebElement> labels = allItems.get(i)
//                        .findElements(AppiumBy.id("com.ticktick.task:id/listSeparator_label"));
//                if (!labels.isEmpty() && "COMPLETED".equals(labels.get(0).getText())) {
//                    sepIndex = i;
//                    break;
//                }
//            }
//
//            if (sepIndex < 0) {
//                // separator not found yet
//                return false;
//            }
//
//            // 2c) look through items after sepIndex for our task title
//            for (int i = sepIndex + 1; i < allItems.size(); i++) {
//                WebElement title = allItems.get(i)
//                        .findElement(AppiumBy.id("com.ticktick.task:id/title"));
//                if (taskName.equals(title.getText())) {
//                    return true;
//                }
//            }
//            return false;
//        });
//
//        if (!moved) {
//            throw new AssertionError("❌ Task '" + taskName + "' did not move under the COMPLETED bar.");
//        }
//
//        System.out.println("✅ Task '" + taskName + "' successfully moved under COMPLETED.");
//    }
}


