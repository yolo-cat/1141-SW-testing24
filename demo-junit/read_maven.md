## Quick note

```bash
// mvn run java
mvn exec:java -Dexec.mainClass="fcu.App"


```



## Install Maven in VS code

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


## mvn cheatsheet

Here's a cheatsheet for common Maven commands and operations. This should help you quickly reference the most commonly used `mvn` commands.

### New project

  ```bash
  mvn archetype:generate -DgroupId=com.example -DartifactId=my-app -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
  ```

### Maven Build Lifecycle Phases
Maven has a predefined build lifecycle with various phases:

- **validate**: Validates the project is correct and all necessary information is available.
- **compile**: Compiles the source code of the project.
- **test**: Runs the tests using a suitable unit testing framework (e.g., JUnit).
- **package**: Packages the compiled code into a distributable format (e.g., JAR, WAR).
- **verify**: Runs any checks to verify the package is valid and meets quality criteria.
- **install**: Installs the package into the local repository, making it available for other projects on the same machine.
- **deploy**: Copies the final package to the remote repository for sharing with other developers and projects.

### Basic Commands

- **Build the project** (executes `validate`, `compile`, `test`, `package`):
  ```bash
  mvn package
  ```

- **Compile the project**:
  ```bash
  mvn compile
  ```

- **Clean the project** (removes the `target/` directory):
  ```bash
  mvn clean
  ```

- **Clean and then build the project**:
  ```bash
  mvn clean package
  ```

- **Run tests**:
  ```bash
  mvn test
  ```

- **Install the package into the local repository**:
  ```bash
  mvn install
  ```

- **Deploy the package to a remote repository**:
  ```bash
  mvn deploy
  ```

### Running Specific Phases or Goals

- **Run only the `test` phase**:
  ```bash
  mvn test
  ```

- **Skip tests during the build**:
  ```bash
  mvn install -DskipTests
  ```

- **Compile without running tests**:
  ```bash
  mvn install -Dmaven.test.skip=true
  ```

- **Run a specific test class**:
  ```bash
  mvn -Dtest=ClassName test
  ```

- **Run a specific test method in a class**:
  ```bash
  mvn -Dtest=ClassName#methodName test
  ```

### Dependency Management

- **Download dependencies**:
  ```bash
  mvn dependency:resolve
  ```

- **Display dependency tree**:
  ```bash
  mvn dependency:tree
  ```

- **Analyze and list all dependencies**:
  ```bash
  mvn dependency:list
  ```

### Project Information

- **Display project information**:
  ```bash
  mvn help:effective-pom
  ```

- **Display list of available goals**:
  ```bash
  mvn help:describe -Dcmd=help
  ```

- **Show details of a specific goal**:
  ```bash
  mvn help:describe -Dmojo=<plugin-goal> -Ddetail
  ```

### Profiles

- **Activate a profile**:
  ```bash
  mvn <goal> -PprofileName
  ```

- **List available profiles**:
  ```bash
  mvn help:all-profiles
  ```

### Archetypes

- **Generate a new project from an archetype**:
  ```bash
  mvn archetype:generate
  ```

### Clean Up

- **Purge local repository of unneeded files**:
  ```bash
  mvn dependency:purge-local-repository
  ```

### Debugging

- **Enable debug output**:
  ```bash
  mvn <goal> -X
  ```

- **Enable batch mode (non-interactive)**:
  ```bash
  mvn <goal> -B
  ```

This cheatsheet should cover the most common tasks you'll perform with Maven. Let me know if you need any further details or specific examples!


