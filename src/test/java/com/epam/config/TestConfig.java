package com.epam.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class TestConfig {
  private static final Properties PROPS = new Properties();

  static {
    String env = System.getProperty("env", "dev"); // default dev
    String path = "config/" + env + ".properties";

    try (InputStream is = TestConfig.class.getClassLoader().getResourceAsStream(path)) {
      if (is == null) throw new IllegalStateException("Config not found: " + path);
      PROPS.load(is);
    } catch (IOException e) {
      throw new RuntimeException("Failed to load config: " + path, e);
    }
  }

  private TestConfig() {}

  public static String baseUrl() {
    return sysOrProp("baseUrl");
  }

  public static boolean headless() {
    return Boolean.parseBoolean(sysOrProp("headless"));
  }

  public static String browser() {
    return sysOrProp("browser");
  }

  public static int timeoutMs() {
    return Integer.parseInt(sysOrProp("timeoutMs"));
  }

  // System properties override file values
  private static String sysOrProp(String key) {
    return System.getProperty(key, PROPS.getProperty(key));
  }
}
