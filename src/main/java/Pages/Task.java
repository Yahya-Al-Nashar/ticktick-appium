package Pages;

import Factory.WebDriverFactory;
import Interactions.Button;
import Interactions.Textbox;
import Utils.MobileEvents;
import Utils.WaitUtils;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;

import java.util.Date;
import java.util.List;

import static Utils.MobileEvents.BackButton;
import static java.awt.SystemColor.text;

public class Task {
    private static final Textbox EditName = new Textbox(AppiumBy.id("com.ticktick.task:id/edit_text") , "Edit task name");
    private static final Button EditName_Button = new Button(AppiumBy.id("com.ticktick.task:id/list_item_title"), "Edit Name Button");
    private static final Button Priority_Button = new Button(AppiumBy.id("com.ticktick.task:id/priority_toggle_btn") , "Change Priority");
    private static final Button High_Pr = new Button(AppiumBy.androidUIAutomator("new UiSelector().text(\"High Priority\")"),"Set High Priority");
    private static final Button Med_Pr = new Button(AppiumBy.androidUIAutomator("new UiSelector().text(\"Medium Priority\")"),"Set High Priority");
    private static final Button Low_Pr = new Button(AppiumBy.androidUIAutomator("new UiSelector().text(\"Low Priority\")"),"Set High Priority");
    private static final Button No_Pr = new Button(AppiumBy.androidUIAutomator("new UiSelector().text(\"No Priority\")"),"Set High Priority");
    private static final Button Add_Tag = new Button(AppiumBy.id("com.ticktick.task:id/input_tag"),"Add a tag");
    private static final Button Save_Tags_Button = new Button(AppiumBy.id("android:id/button1"));
    private static final Textbox Tag_Name_Field = new Textbox(AppiumBy.id("com.ticktick.task:id/input_tag_et"), "Add the Tag");
    private static final Button Descp_Button = new Button(AppiumBy.id("com.ticktick.task:id/task_editor_composite"));
    private static final Textbox Descp_TexBox = new Textbox(AppiumBy.id("com.ticktick.task:id/task_editor_composite"),"Description TexBox");
    //private static final String Check_Priority = "/android.widget.ImageView[@resource-id=\"com.ticktick.task:id/iv_checked\"]"

    public static void EditTaskName(String Name){
        EditName_Button.click();

        EditName.ClearText();
        EditName.setText(Name);

        MobileEvents.PressEnter();
        MobileEvents.HideKeyboard();
    }

    public static void SetPriority(String Priority){
        Priority_Button.click();
        switch (Priority){
            case "High":
                High_Pr.click();
                break;
            case "Medium":
                Med_Pr.click();
                break;
            case "Low":
                Low_Pr.click();
                break;
            case "No":
                No_Pr.click();
                break;
            default:
                break;
        }
    }

    public static void RemoveTag(String TagName) throws InterruptedException {
        WebElement Tag = WebDriverFactory.getDriver().findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\""+TagName+"\")"));
        if(Tag.isEnabled()){
            Tag.click();
            WaitUtils.waitForElementToBeVisible(WebDriverFactory.getDriver(),AppiumBy.androidUIAutomator("new UiSelector().text(\""+TagName+"\")"));
            WebElement TagCheckBox = WebDriverFactory.getDriver().findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\""+TagName+"\")"));
            if(TagCheckBox.isEnabled()){
                TagCheckBox.click();
                Save_Tags_Button.click();
            }
            else
                System.out.println("Something Went Wrong");
        }
        else{
            System.out.println("Tag doesn't exsits");
        }
    }

    public static void AddTag(String TagName){
        Add_Tag.click();
        Tag_Name_Field.setText(TagName);
        MobileEvents.PressEnter();
        MobileEvents.HideKeyboard();
    }
    public static void Exit_Task(){
        BackButton();
    }

    public static void Add_Descrioption(String Desc){
        Descp_Button.click();
        MobileEvents.HideKeyboard();
        Descp_TexBox.setText(Desc);
        MobileEvents.PressEnter();
    }
    public static void SetTaskDate(String Date){
        String Text =EditName.GetText();
        EditName_Button.click();
        EditName.setText(Text+" @"+Date);
        MobileEvents.PressEnter();
        MobileEvents.HideKeyboard();
    }
    public static void SetNameAndDate(String Name , String Date){
        EditTaskName(Name+" @"+Date);
    }



    public static void assertPriorityIsChecked(String priorityToCheck)
    {
        WebDriverFactory.getDriver().executeScript("mobile: clickGesture", ImmutableMap.of("x", 1350, "y" , 325));

        // Store the list of priorities
        List<WebElement> priorities = WebDriverFactory.getDriver()
                .findElements(AppiumBy.className("android.widget.LinearLayout"));

        Boolean flag = false;

        // Loop through all priorities
        for (WebElement priority : priorities)
        {
            // Get the title of the priority
            WebElement title = priority.findElement(AppiumBy.id("com.ticktick.task:id/tv_title"));

            // Get the type of the priority
            String text = title.getText();

            // Check if this priority is checked
            boolean check = !priority
                    .findElements(AppiumBy.id("com.ticktick.task:id/iv_checked"))
                    .isEmpty();

            // Check if this is the right one
            if (text.equals(priorityToCheck) && check)
            {
                //Assert.assertEquals(priorityToCheck, text);
                System.out.println("Expected " + priorityToCheck + " is checked");
                flag = true;
            }
        }
        if(!flag)
        {
            throw new AssertionError("Expected " + priorityToCheck + " , but it was not");
        }
        MobileEvents.BackButton();
    }




}
