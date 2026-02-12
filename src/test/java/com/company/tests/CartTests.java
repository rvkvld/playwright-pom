package com.company.tests;

import com.company.config.TestConfig;
import com.company.pages.CartPage;
import com.microsoft.playwright.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

@Slf4j
public class CartTests {
  private static Playwright playwright;
  private static Browser browser;

  private BrowserContext context;
  private Page page;
  private CartPage cartPage;

  @BeforeAll
  static void setupClass() {
    boolean headless = Boolean.parseBoolean(System.getenv().getOrDefault("HEADLESS", "true"));
    log.info("Starting Playwright. Headless mode: {}", headless);
    playwright = Playwright.create();
    browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(headless));
  }

  @AfterAll
  static void tearDownClass() {
    log.info("Closing browser and Playwright");
    browser.close();
    playwright.close();
  }

  @BeforeEach
  void setup() {
    context = browser.newContext();
    page = context.newPage();
    cartPage = new CartPage(page);
  }

  @AfterEach
  void cleanup() {
    log.info("Closing browser context");
    context.close();
  }

  @Test
  void userCanSubscribeFromEmptyCart() {
    log.info("Test: userCanSubscribeFromEmptyCart started");
    cartPage.open(TestConfig.baseUrl());
    cartPage.assertCartIsEmpty();

    cartPage.subscribe("abc@mail.com");
    cartPage.assertSubscriptionSuccess();
    log.info("Test: userCanSubscribeFromEmptyCart finished");
  }
}
