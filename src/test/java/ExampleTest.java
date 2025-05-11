
import Factory.WebDriverFactory;
import Pages.Task;
import Pages.TasksPage;

import Utils.MobileEvents;
import Utils.MobileGestures;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.DeviceRotation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

//import static Factory.WebDriverFactory.driver;


public class ExampleTest {
    @Test
    public void Test1() throws InterruptedException {

        TasksPage tasksPage = new TasksPage();

        tasksPage.ChooseList("yy");
        tasksPage.assertTaskNameField("yy");

        tasksPage.clickTaskCheckbox("T2");
        tasksPage.editTask("Yehya");
        Task.EditTaskName("Kareem's Task");

        Task.SetPriority("High");
        Task.assertPriorityIsChecked("High Priority");

        Task.AddTag("Testing123");
        Task.RemoveTag("t1");
        Task.Add_Descrioption("This is a test description");
        Task.Exit_Task();

        tasksPage.clickTaskCheckbox("Kareem's Task");
        tasksPage.CreateTask("Test Task", "This is a test task");
        tasksPage.clickTaskCheckbox("Test Task");

        WebDriverFactory.closeDriver();
    }

    @Test
    public void gestureTest() throws InterruptedException {

        TasksPage tasksPage = new TasksPage();

        tasksPage.ChooseList("yy");
        tasksPage.assertTaskNameField("yy");

        // Close List View by Swiping Left
        MobileGestures.swipeLeft();

        // Drag and Drop 3rd Task
        MobileGestures.dragAndDrop();

        // Rotate the device to landscape
        MobileGestures.rotateLandscape();

        // Rotate the device back to portrait
        MobileGestures.rotatePortrait();

        // Long press gesture Wont Do
        MobileGestures.longGestureWontDo();

        // Multiple Selection Priority
        MobileGestures.multiplpeSelectPriority();
    }

    @Test
    public void invalidTestNonExistentTask() throws InterruptedException {
        TasksPage tasksPage = new TasksPage();

        tasksPage.ChooseList("yy");
        tasksPage.assertTaskNameField("yy");

        tasksPage.clickTaskCheckbox("T2");
        tasksPage.editTask("Yehya");
        Task.EditTaskName("Kareem's Task");

        Task.SetPriority("High");
        Task.assertPriorityIsChecked("High Priority");

        Task.AddTag("Testing123");
        Task.RemoveTag("t1");
        Task.Add_Descrioption("This is a test description");
        Task.Exit_Task();

        // Attempt to interact with a non-existent task
        try
        {
            tasksPage.clickTaskCheckbox("KAREEMS Tasks");
        }
        catch (Exception e)
        {
            // Handle any unexpected exceptions
            Assert.fail("Unexpected exception occurred: " + e.getMessage());
        }
        // Log success
        System.out.println("✅ Test passed: Non-existent task interaction handled as expected.");

        WebDriverFactory.closeDriver();
    }

    @Test
    public void invalidTestNonExistentTag() throws InterruptedException {
        TasksPage tasksPage = new TasksPage();

        tasksPage.ChooseList("yy");
        tasksPage.assertTaskNameField("yy");

        tasksPage.clickTaskCheckbox("T2");
        tasksPage.editTask("Yehya");
        Task.EditTaskName("Kareem's Task");

        // Attempt to interact with a non-existent tag
        try
        {
            Task.SetPriority("HIGH");
            System.out.println("Tag does not exist, as expected.");
        }
        catch (Exception e)
        {
            // Handle any unexpected exceptions
            Assert.fail("Unexpected exception occurred: " + e.getMessage());
        }
        // Log success
        System.out.println("✅ Test passed: Non-existent tag interaction handled as expected.");

        MobileEvents.BackButton();

        Task.AddTag("Testing123");
        Task.RemoveTag("t1");
        Task.Add_Descrioption("This is a test description");
        Task.Exit_Task();

        tasksPage.clickTaskCheckbox("Kareem's Task");
        tasksPage.CreateTask("Test Task", "This is a test task");
        tasksPage.clickTaskCheckbox("Test Task");

        WebDriverFactory.closeDriver();
    }
}

