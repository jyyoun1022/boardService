package lab;

import java.util.Enumeration;
import java.util.Properties;

public class labs {
    public static void main(String[] args) {
        String version = System.getProperty("java.version");
        String vendor = System.getProperty("java.vendor");
        String vendorUrl = System.getProperty("java.vendor.url");
        String classVersion = System.getProperty("java.class.version");
        String path = System.getProperty("java.class.path");
        String extDir = System.getProperty("java.ext.dir");
        String osName = System.getProperty("os.name");
        String osArch = System.getProperty("os.arch");
        String osVersion = System.getProperty("os.version");
        String fileSepar = System.getProperty("file.separator");
        String pathSepar = System.getProperty("path.separator");
        String lineSepar = System.getProperty("line.separator");
        String userName = System.getProperty("user.name");
        String userHome = System.getProperty("user.home");
        String userDir = System.getProperty("user.dir");

        System.out.println("version = " + version);
        System.out.println("vendor = " + vendor);
        System.out.println("vendorUrl = " + vendorUrl);
        System.out.println("classVersion = " + classVersion);
        System.out.println("path = " + path);
        System.out.println("extDir = " + extDir);
        System.out.println("osName = " + osName);
        System.out.println("osArch = " + osArch);
        System.out.println("osVersion = " + osVersion);
        System.out.println("fileSepar = " + fileSepar);
        System.out.println("pathSepar = " + pathSepar);
        System.out.println("lineSepar = " + lineSepar);
        System.out.println("userName = " + userName);
        System.out.println("userHome = " + userHome);
        System.out.println("userDir = " + userDir);
        System.out.println("===================================");
        Properties props = System.getProperties();
        for(Enumeration en = props.propertyNames(); en.hasMoreElements();) {
            String key = (String)en.nextElement();
            String value = props.getProperty(key);
            System.out.println(key + "=" + value);
        }
    }
    }

