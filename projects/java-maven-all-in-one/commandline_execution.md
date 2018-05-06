**Commandline execution**

0. Prerequisites

    Add [maven-surefire-plugin](https://maven.apache.org/surefire/maven-surefire-plugin/usage.html#) to your pom.xml

    ```
    <build>
        <pluginManagement>
            <plugins>
                <plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-compiler-plugin</artifactId>
                    <configuration>
                        <source>1.8</source>
                        <target>1.8</target>
                    </configuration>
                </plugin>
                <plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-surefire-plugin</artifactId>
                    <version>2.19.1</version>
                </plugin>
            </plugins>
        </pluginManagement>
    </build>
    ```

1. Execution

    Run single class (in this case class is actually suite):
    ```
    mvn -Dtest=demo_01_junit.suite.TestSuite test
    ```

    Run all classes in package:
    ```
    mvn -Dtest=demo_01_junit.tests.* test
    ```