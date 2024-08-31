
## Maven

To create a Maven project using the Command Line Interface (CLI) in Visual Studio Code, follow these steps:

### 1. **Install Maven (if not already installed)**

First, ensure that Maven is installed on your system:

- **macOS**: Maven might already be installed. You can check by running:
  ```bash
  mvn -v
  ```
  If Maven isn't installed, you can install it via Homebrew:
  ```bash
  brew install maven
  ```

- **Windows**: You can download and install Maven from the [Apache Maven website](https://maven.apache.org/download.cgi).

- **Linux**: Install Maven using your package manager:
  ```bash
  sudo apt-get install maven
  ```

### 2. **Open Terminal in Visual Studio Code**

1. Open Visual Studio Code.
2. Open the integrated terminal by navigating to `View` > `Terminal` or using the shortcut `` Ctrl + ` ``.

### 3. **Create a Maven Project**

In the terminal, use the following command to create a new Maven project:

```bash
mvn archetype:generate -DgroupId=com.example -DartifactId=my-app -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
```

- **`-DgroupId=com.example`**: Specifies the group ID for your project (usually a domain name in reverse).
- **`-DartifactId=my-app`**: Specifies the artifact ID, which is the name of your project.
- **`-DarchetypeArtifactId=maven-archetype-quickstart`**: Specifies the archetype to use for generating the project (in this case, a simple Java project).
- **`-DinteractiveMode=false`**: Runs the command non-interactively.

### 4. **Navigate to the Project Directory**

After running the command, a new directory with the name of your project (`my-app`) will be created. Navigate into this directory:

```bash
cd my-app
```

### 5. **Open the Project in Visual Studio Code**

You can open the newly created Maven project in Visual Studio Code with the following command:

```bash
code .
```

This command opens the current directory as a project in Visual Studio Code.

### 6. **Build the Project**

To build the project, you can run:

```bash
mvn clean install
```

This command will compile the project, run any tests, and package the application.

### 7. **Run the Project**

To run the generated project (if it has a `main` method), use:

```bash
mvn exec:java -Dexec.mainClass="com.example.App"
```

Replace `"com.example.App"` with the correct package and class name that contains the `main` method.

### 8. **Edit and Manage the Project in VS Code**

Now you can edit your project files, manage dependencies in the `pom.xml`, and use the integrated terminal in VS Code for Maven commands.

This setup allows you to create and manage Maven projects directly from the CLI in Visual Studio Code.

## Upgrade Junit 5

The POM file

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
  <modelVersion>4.0.0</modelVersion>
  <groupId>fcu</groupId>
  <artifactId>my-junit01</artifactId>
  <packaging>jar</packaging>
  <version>1.0-SNAPSHOT</version>
  <name>my-junit01</name>
  <url>http://maven.apache.org</url>
  <dependencies>
    <!-- JUnit 5 dependency -->
    <dependency>
      <groupId>org.junit.jupiter</groupId>
      <artifactId>junit-jupiter</artifactId>
      <version>5.9.0</version>
      <scope>test</scope>
    </dependency>
  </dependencies>
</project>

```

The testing code
```java
package fcu;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest {

    /**
     * Rigorous Test :-)
     */
    @Test
    public void testApp() {
        assertTrue(true);
    }
}
```

## Compile partial files

In a Maven project, if you want to temporarily exclude certain files or directories from being compiled, you can do so by configuring the `maven-compiler-plugin` in your `pom.xml`. Here's how you can set it up:

### 1. Exclude Specific Files or Directories
To exclude specific files or directories from being compiled, you can add an `excludes` section to the `maven-compiler-plugin` configuration.

### Example Configuration:

```xml
<project>
    <!-- Other configurations -->
    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.8.1</version>
                <configuration>
                    <excludes>
                        <!-- Exclude specific files -->
                        <exclude>**/path/to/excluded/File1.java</exclude>
                        <exclude>**/path/to/excluded/File2.java</exclude>
                        <!-- Exclude entire directories -->
                        <exclude>**/path/to/excluded/directory/**</exclude>
                    </excludes>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```

### Explanation:

- **`<exclude>`**: Defines the files or directories to be excluded. The patterns are relative to the `src/main/java` or `src/test/java` directories by default.
- **Wildcard usage**:
  - `**/` matches directories at any level.
  - `*.java` matches any Java file.
  - `**/directory/**` matches all files within the specified directory.

### 2. Temporarily Exclude Files

If you want to temporarily exclude files, you can simply comment out or remove the `excludes` section when you want to include those files again.

### 3. Excluding Test Files

If you want to exclude test files in `src/test/java`, you can configure the `maven-surefire-plugin` in a similar way:

```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-surefire-plugin</artifactId>
            <version>2.22.2</version>
            <configuration>
                <excludes>
                    <exclude>**/path/to/excluded/TestFile1.java</exclude>
                    <exclude>**/path/to/excluded/TestFile2.java</exclude>
                </excludes>
            </configuration>
        </plugin>
    </plugins>
</build>
```

### 4. **Alternative: Moving Files Temporarily**

Another approach is to temporarily move the files you don't want to compile to a different directory outside of the `src/main/java` or `src/test/java` structure. Maven will not compile files that are not within these directories.

### 5. **Reverting the Changes**

To revert the exclusion, simply remove or comment out the `excludes` configuration in the `pom.xml`, or move the files back to their original location if you chose to move them.

By using the `excludes` section in your Maven configuration, you can easily manage which files are included in the build process, allowing you to focus on specific parts of your project without permanently altering your codebase.

## Coverage

To show testing coverage and generate a report in your Maven project, you can use tools like JaCoCo, which is a popular code coverage library for Java. Here’s how to integrate JaCoCo into your Maven project:

### 1. Add JaCoCo Plugin to `pom.xml`
First, you need to add the JaCoCo Maven plugin to your `pom.xml` file. Add the following configuration under the `<build>` section:

```xml
<build>
    <plugins>
        <!-- JaCoCo plugin -->
        <plugin>
            <groupId>org.jacoco</groupId>
            <artifactId>jacoco-maven-plugin</artifactId>
            <version>0.8.8</version> <!-- Use the latest version if available -->
            <executions>
                <execution>
                    <id>prepare-agent</id>
                    <goals>
                        <goal>prepare-agent</goal>
                    </goals>
                </execution>
                <execution>
                    <id>report</id>
                    <phase>prepare-package</phase>
                    <goals>
                        <goal>report</goal>
                    </goals>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>
```

### 2. Run Tests and Generate Coverage Report
Once you have added the JaCoCo plugin, you can run your tests and generate the coverage report with the following Maven command:

```bash
mvn clean test
```

This command runs your tests and generates a code coverage report. The report is usually generated in the `target/site/jacoco` directory.

### 3. View the Coverage Report
After running the tests, you can find the HTML report at:

```
target/site/jacoco/index.html
```

Open this file in a web browser to view the code coverage report.

### Summary
1. Add the JaCoCo plugin to your `pom.xml`.
2. Run `mvn clean test` to execute tests and generate coverage.
3. Open the report at `target/site/jacoco/index.html`.

This setup will help you track code coverage and assess how well your tests cover your codebase.

## Junit jupiter API/Engine

In JUnit 5, the terms "API" and "Engine" refer to different components that serve distinct purposes within the testing framework:

### 1. **JUnit Jupiter API (`junit-jupiter-api`)**
- **Purpose**: This module provides the public API for writing tests. It includes annotations, assertions, and other utilities that you use when writing test cases.
- **Contents**: 
  - **Annotations**: Like `@Test`, `@BeforeEach`, `@AfterEach`, `@BeforeAll`, `@AfterAll`, etc.
  - **Assertions**: Like `assertEquals`, `assertTrue`, `assertThrows`, etc.
  - **Other Utilities**: Classes and interfaces to support parameterized tests, test lifecycle management, and more.
- **Usage**: This is the library you interact with directly when creating test cases in JUnit 5. For instance, when you write a test method annotated with `@Test`, you're using the JUnit Jupiter API.

### 2. **JUnit Jupiter Engine (`junit-jupiter-engine`)**
- **Purpose**: This module is the runtime component that discovers and executes tests written using the JUnit Jupiter API. It integrates with test runners or build tools to actually run the tests.
- **Contents**: 
  - **Test Engine Implementation**: The code that runs the tests, manages test discovery, and handles execution logic.
  - **Integration with Build Tools**: The engine ensures that tools like Maven, Gradle, or IDEs (like IntelliJ IDEA or VS Code) can run JUnit 5 tests.
- **Usage**: The engine is not something you interact with directly. Instead, it's used behind the scenes when you run your tests, ensuring they are executed according to the rules defined in the JUnit Jupiter API.

### Summary
- **JUnit Jupiter API (`junit-jupiter-api`)**: The "what" of testing. It defines the structure, annotations, and assertions you use to write tests.
- **JUnit Jupiter Engine (`junit-jupiter-engine`)**: The "how" of testing. It finds and runs the tests you wrote using the API.

Both components are necessary for running JUnit 5 tests: the API allows you to write them, and the engine makes sure they are executed correctly.