# Automated Test Suite project

This repository contains an automated test suite project designed to demonstrate proficiency in software testing using Java SE8, Selenium WebDriver, Maven, and TestNG.

The project showcases best practices in test automation, including structure, execution, and reporting.

![Image depicting a robotic arm in front of a computer screen](https://visconttig.com/images/automated-testing2.png)

## Table of Contents

- [Introduction](#introduction)
- [Technologies Used](#technologies-used)
- [Getting Started](#getting-started)
- [Project Structure](#project-structure)
- [Running the Tests](#running-the-tests)
- [Reporting](#reporting)
- [Contributing](#contributing)
- [License](#license)

## Introduction

The purpose of this project is to provide a comprehensive example of how to set up and implement an automated test suite using popular Java testing technologies. This test suite includes test cases for different features of a demo web application, ensuring reliability and coverage.

## Technologies Used

- **Java SE8**: The programming language used to write the test scripts.
- **Selenium WebDriver**: A tool for automating web application testing.
- **Maven**: A build automation tool used to manage project dependencies and build configuration.
- **TestNG**: A testing framework that facilitates test execution, management, and reporting.

## Getting Started

To get a local copy up and running, follow these simple steps.

### Prerequisites

- **Java JDK 8**: Ensure you have Java JDK 8 installed.
- **Maven**: Download and install Maven.
- **Browser and WebDriver**: Download and configure the appropriate WebDriver for your browser (e.g., ChromeDriver for Google Chrome).
- **IDE**: An IDE like IntelliJ IDEA or Eclipse for writing and running tests.

### Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/visconttig/Selenium-Testing.git
   ```

2. Navigate to the project directory:

   ```bash
   cd Selenium-Testing
   ```

3. Install the dependencies using Maven:

   ```bash
   mvn clean install
   ```

## Project Structure

- **src/main/java/**: Contains application code if needed.
- **src/main/java/pruebas**: Contains test scripts.
- **pom.xml**: Maven configuration file for managing dependencies and build settings.

## Running the Tests

To execute the test suite, use the following Maven command:

```bash
mvn test
```

This command will compile the test suite and run all the tests using TestNG.

## Reporting

TestNG generates a default report upon test execution. After running the tests, you can find the test reports in the `target/surefire-reports` directory. Additionally, you might configure a more advanced reporting tool like Allure or ExtentReports for enhanced test reporting.

## Contributing

Contributions are welcome! If you'd like to contribute to this project, please fork the repository and create a pull request with your changes. Make sure your code adheres to the established coding standards and includes appropriate tests.

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---

Feel free to contact me with any questions, comments, or suggestions. Happy testing!
