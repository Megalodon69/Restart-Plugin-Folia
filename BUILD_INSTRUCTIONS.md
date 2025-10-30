# Build Instructions

## Prerequisites

- **Java Development Kit (JDK) 17 or higher**
- **Apache Maven 3.6 or higher**
- **Internet connection** (for downloading dependencies)

## Building the Plugin

### Using Maven

1. Open a terminal/command prompt in the project directory

2. Run the Maven package command:
   ```bash
   mvn clean package
   ```

3. The compiled plugin JAR will be located at:
   ```
   target/RestartPluginFolia-1.0.0.jar
   ```

### Build without running tests

If you want to skip tests during the build:
```bash
mvn clean package -DskipTests
```

## Troubleshooting

### Maven not found
Make sure Maven is installed and in your PATH:
```bash
mvn --version
```

If not installed, download from: https://maven.apache.org/download.cgi

### Dependency download issues
The plugin requires the Spigot API. If you have issues downloading it, ensure:
1. You have internet connection
2. The Spigot repository (https://hub.spigotmc.org/nexus/) is accessible
3. Your firewall isn't blocking Maven

### Java version issues
Check your Java version:
```bash
java -version
```

Make sure it's Java 17 or higher. If needed, set JAVA_HOME environment variable to your JDK 17+ installation.

## Alternative: Building Spigot locally

If you cannot access the Spigot repository, you can build Spigot locally:

1. Download BuildTools from: https://hub.spigotmc.org/jenkins/job/BuildTools/lastSuccessfulBuild/artifact/target/BuildTools.jar

2. Run BuildTools:
   ```bash
   java -jar BuildTools.jar --rev 1.20.1
   ```

3. This will install Spigot API to your local Maven repository

4. Then build the plugin:
   ```bash
   mvn clean package
   ```

## Verifying the Build

After a successful build, you should see:
```
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
```

And the JAR file should exist in the `target/` directory.
