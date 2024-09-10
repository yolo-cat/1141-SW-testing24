

## Plugin configuration

### Setting Plugin Execution in a Maven Lifecycle

In Maven, you can control when a plugin is executed during the build lifecycle by configuring the plugin in the `pom.xml`. For example, if you want to integrate JaCoCo, a popular code coverage tool, and ensure it runs during the `test` phase of the build lifecycle, you'll specify this in your `pom.xml`.

### Extending the POM Introduction: Plugin Configuration

To set the execution of a plugin in a specific lifecycle phase, you need to add the plugin to the `<build>` section of your `pom.xml` and specify the desired execution phase.

#### Example: Configuring JaCoCo Plugin to Run During the `test` Phase

Here’s how you can configure the JaCoCo Maven plugin to execute during the `test` phase:

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
                             http://maven.apache.org/xsd/maven-4.0.0.xsd">
    
    <modelVersion>4.0.0</modelVersion>

    <!-- Project coordinates -->
    <groupId>com.example</groupId>
    <artifactId>my-app</artifactId>
    <version>1.0.0</version>
    <packaging>jar</packaging>

    <!-- Dependencies -->
    <dependencies>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.13.2</version>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <!-- Build configurations -->
    <build>
        <plugins>
            <!-- JaCoCo Plugin -->
            <plugin>
                <groupId>org.jacoco</groupId>
                <artifactId>jacoco-maven-plugin</artifactId>
                <version>0.8.8</version>
                <executions>
                    <execution>
                        <goals>
                            <goal>prepare-agent</goal>
                        </goals>
                    </execution>
                    <execution>
                        <id>report</id>
                        <phase>test</phase> <!-- Bind the execution to the 'test' phase -->
                        <goals>
                            <goal>report</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>

            <!-- Compiler Plugin -->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.8.1</version>
                <configuration>
                    <source>1.8</source>
                    <target>1.8</target>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```

Breakdown of the Configuration:

1. **Plugin Declaration**:
   - The JaCoCo plugin is added under the `<plugins>` section within the `<build>` section.

2. **Executions**:
   - The `<executions>` tag allows you to define multiple execution configurations for the plugin.

3. **Goals**:
   - In this example, the JaCoCo plugin is configured with two goals:
     - **prepare-agent**: This goal prepares the agent that will monitor the execution of the tests. This is executed during the default phase associated with the goal.
     - **report**: This goal generates the code coverage report. It is explicitly bound to the `test` phase using the `<phase>` tag.

4. **Binding to a Lifecycle Phase**:
   - By specifying `<phase>test</phase>` within the `<execution>` block, you bind the execution of the `report` goal to the `test` phase of the Maven lifecycle. This means that the coverage report will be generated immediately after the tests are run.

### Executing the Lifecycle with Plugin Configurations

Once you have configured the plugin as shown above, you can simply run:

```bash
mvn test
```

This command will:

- Compile your code.
- Run your tests.
- Generate a JaCoCo coverage report during the `test` phase.

### Customizing Execution Phases

You can bind plugin executions to other phases as well, depending on your needs. For example:

- **pre-clean**: Execute before cleaning the project.
- **compile**: Execute after compiling the code.
- **package**: Execute when packaging the project.

#### Example: Running JaCoCo During the `verify` Phase

```xml
<execution>
    <id>report</id>
    <phase>verify</phase> <!-- Bind the execution to the 'verify' phase -->
    <goals>
        <goal>report</goal>
    </goals>
</execution>
```

This change would delay the generation of the JaCoCo report until after all tests have been verified.

### Best Practices for Plugin Configuration

- **Define Default Goals**: Use default goals when possible to keep your `pom.xml` clean.
- **Use Profiles for Conditional Executions**: If you need different plugin behaviors in different environments (e.g., development vs. production), consider using profiles.
- **Avoid Over-configuration**: Only configure what’s necessary to avoid complexity.

### Conclusion

By carefully configuring plugins in your `pom.xml`, you can fully control the build process, ensuring that tasks like code coverage, testing, and packaging happen exactly when you need them to. The `pom.xml` thus becomes a powerful tool for orchestrating complex builds and ensuring consistency across different environments.

## POM and Junit 5

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

### Junit 5 dependencies
The dependency you've added for JUnit 5 (`junit-jupiter`) is sufficient for most cases. The `junit-jupiter` artifact is a meta-dependency that includes the following components:

- `junit-jupiter-api` (the API for writing tests)
- `junit-jupiter-engine` (the test engine to run the tests)
- `junit-jupiter-params` (for parameterized tests)

So, by including `junit-jupiter`, you automatically get both `junit-jupiter-api` and `junit-jupiter-engine`, along with the other components of JUnit 5.

You do **not** need to explicitly add `junit-jupiter-api` and `junit-jupiter-engine` separately unless you have a specific reason to manage these components individually.

Here's your dependency block as it stands:

```xml
<dependencies>
    <!-- JUnit 5 dependency -->
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter</artifactId>
        <version>5.9.0</version>
        <scope>test</scope>
    </dependency>
</dependencies>
```
### Junit 5 API/Engine

In JUnit 5, the terms "API" and "Engine" refer to different components that serve distinct purposes within the testing framework:

**JUnit Jupiter API (`junit-jupiter-api`)**
- **Purpose**: This module provides the public API for writing tests. It includes annotations, assertions, and other utilities that you use when writing test cases.
- **Contents**: 
  - **Annotations**: Like `@Test`, `@BeforeEach`, `@AfterEach`, `@BeforeAll`, `@AfterAll`, etc.
  - **Assertions**: Like `assertEquals`, `assertTrue`, `assertThrows`, etc.
  - **Other Utilities**: Classes and interfaces to support parameterized tests, test lifecycle management, and more.
- **Usage**: This is the library you interact with directly when creating test cases in JUnit 5. For instance, when you write a test method annotated with `@Test`, you're using the JUnit Jupiter API.

**JUnit Jupiter Engine (`junit-jupiter-engine`)**
- **Purpose**: This module is the runtime component that discovers and executes tests written using the JUnit Jupiter API. It integrates with test runners or build tools to actually run the tests.
- **Contents**: 
  - **Test Engine Implementation**: The code that runs the tests, manages test discovery, and handles execution logic.
  - **Integration with Build Tools**: The engine ensures that tools like Maven, Gradle, or IDEs (like IntelliJ IDEA or VS Code) can run JUnit 5 tests.
- **Usage**: The engine is not something you interact with directly. Instead, it's used behind the scenes when you run your tests, ensuring they are executed according to the rules defined in the JUnit Jupiter API.

**Summary**
- **JUnit Jupiter API (`junit-jupiter-api`)**: The "what" of testing. It defines the structure, annotations, and assertions you use to write tests.
- **JUnit Jupiter Engine (`junit-jupiter-engine`)**: The "how" of testing. It finds and runs the tests you wrote using the API.

Both components are necessary for running JUnit 5 tests: the API allows you to write them, and the engine makes sure they are executed correctly.

### Compile partial files

In a Maven project, if you want to temporarily exclude certain files or directories from being compiled, you can do so by configuring the `maven-compiler-plugin` in your `pom.xml`. Here's how you can set it up:

#### 1. Exclude Specific Files or Directories
To exclude specific files or directories from being compiled, you can add an `excludes` section to the `maven-compiler-plugin` configuration.


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

- **`<exclude>`**: Defines the files or directories to be excluded. The patterns are relative to the `src/main/java` or `src/test/java` directories by default.
- **Wildcard usage**:
  - `**/` matches directories at any level.
  - `*.java` matches any Java file.
  - `**/directory/**` matches all files within the specified directory.

#### 2. Temporarily Exclude Files

If you want to temporarily exclude files, you can simply comment out or remove the `excludes` section when you want to include those files again.

#### 3. Excluding Test Files

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

#### 4. **Alternative: Moving Files Temporarily**

Another approach is to temporarily move the files you don't want to compile to a different directory outside of the `src/main/java` or `src/test/java` structure. Maven will not compile files that are not within these directories.

#### 5. **Reverting the Changes**

To revert the exclusion, simply remove or comment out the `excludes` configuration in the `pom.xml`, or move the files back to their original location if you chose to move them.

By using the `excludes` section in your Maven configuration, you can easily manage which files are included in the build process, allowing you to focus on specific parts of your project without permanently altering your codebase.

## Junt testing





