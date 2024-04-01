package com.nopcommerce.account;

public class Testing {
    public static void main(String[] args) {
        String projectPath = System.getProperty("user.dir");
        System.out.println(projectPath);
        System.out.println(System.setProperty("user.dir",projectPath + "\\browserDriver\\geckodriver.exe"));
    }
}
