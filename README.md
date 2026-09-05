# TickTick Appium Framework

![Java](https://img.shields.io/badge/Java-23-red)
![Appium](https://img.shields.io/badge/Appium_Java_Client-9.4.0-purple)
![TestNG](https://img.shields.io/badge/TestNG-7.11.0-orange)
![Allure](https://img.shields.io/badge/Reporting-AllureReports-blue)
![Log4j2](https://img.shields.io/badge/Logging-Log4j2-yellow)
![Maven](https://img.shields.io/badge/Build-Maven-C71A36)

End-to-end **mobile test automation framework** for testing the **TickTick** Android application — a cross-platform task management and productivity app — built with Appium and a Page Object Model architecture.

> 📱 Application Under Test: [TickTick - To Do List & Planner](https://play.google.com/store/apps/details?id=com.ticktick.task)

---

## 🚀 Key Features

- **End-to-End Mobile UI Coverage** for TickTick core user flows:
  - User Login & Authentication
  - Task Creation, Editing, and Deletion
  - List & Project Management
  - Task Completion & Status Updates
- **Page Object Model (POM)** for maintainable and scalable test structure
- **Modular Framework Architecture** with clear separation of concerns
- **Robust Reporting**:
  - Allure Reports with detailed step-by-step test results
  - Log4j2 structured logging to file
- **Appium UiAutomator2** driver for native Android automation
- **Maven Surefire Plugin** configured for seamless test execution

---

## 🛠️ Technologies Used

| Component            | Technology              |
| -------------------- | ----------------------- |
| Language             | Java 23                 |
| Mobile Test Library  | Appium Java Client 9.4.0 |
| Test Framework       | TestNG 7.11.0           |
| Reporting            | Allure 2.29.0           |
| Logging              | Log4j2 2.20.0           |
| Build Tool           | Maven                   |
| Automation Driver    | UiAutomator2 (Android)  |

---

## 📂 Project Structure

```
ticktick-appium/
├── src/
│   ├── main/
│   │   └── java/
│   │       ├── pages/            # Page Object classes (POM)
│   │       └── utils/            # Driver setup, helpers, utilities
│   └── test/
│       └── java/
│           └── tests/            # Test classes and test suites
├── allure-results/               # Raw Allure output — auto-generated on test run
├── logs/                         # Log4j2 log files
├── .idea/                        # IntelliJ IDEA project configuration
├── .gitignore
└── pom.xml                       # Maven build descriptor
```

---

## ⚙️ Prerequisites

Before running the project, ensure you have the following installed:

- **Java 23** JDK
- **Apache Maven 3.x**
- **Node.js & npm** — required by Appium
- **Appium Server 2.x** — install globally via npm:
  ```bash
  npm install -g appium
  appium driver install uiautomator2
  ```
- **Android SDK** with a configured emulator or a real Android device connected via USB
- **Allure CLI** — for generating reports ([Installation Guide](https://allurereport.org/docs/install/))
- **TickTick APK** installed on the target device or emulator

---

## 🔧 Installation & Execution

```bash
# 1. Clone the repository
git clone https://github.com/Yahya-Al-Nashar/ticktick-appium.git

# 2. Navigate into the project directory
cd ticktick-appium

# 3. Start the Appium server (in a separate terminal)
appium

# 4. Connect your Android device or start an emulator

# 5. Run all tests
mvn clean test

# 6. Run a specific test class
mvn clean test -Dtest=<ClassName>
```

---

## 📊 Allure Reporting

After running the tests, an `allure-results/` folder is generated automatically.

```bash
# Serve an interactive live report in your browser
allure serve allure-results

# Or generate a static HTML report
allure generate allure-results --clean -o allure-report
allure open allure-report
```

---

## 👤 Author

**Yahya Al Nashar**
Software Test Engineer
[GitHub Profile](https://github.com/Yahya-Al-Nashar)
