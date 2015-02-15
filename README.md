

How to use:
in your main method inside your jar-with-dependencies.jar just include this:

    public static void main(String args[]) {
            JarInstallerKit.starter(args);
            ...
    }

After that every time that your jar run it will copy it self for the computer and it will run as a service when the computer boot

