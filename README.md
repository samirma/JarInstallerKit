

How to use:
in your main method inside your jar-with-dependencies.jar just include this:

    public static void main(String args[]) {
            JarInstallerKit.starter(args);
            ...
    }

After that, every time that your jar run it will copy itself to the computer and it will run as a service when the computer boot


How to install:
mvn install

Add the maven dependency

        <dependency>
            <groupId>com.samir.commons.java.JarInstallerKit</groupId>
            <artifactId>JarInstallerKit</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>


Execute with "init" param in order to run your app manually:
java -jar your_project.jar init


