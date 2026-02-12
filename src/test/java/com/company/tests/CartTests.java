package com.company.tests;

import com.company.config.TestConfig;
import com.company.pages.CartPage;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

    cartPage.subscribe(TestConfig.email());
    cartPage.assertSubscriptionSuccess();
    log.info("Test: userCanSubscribeFromEmptyCart finished");
  }
}
