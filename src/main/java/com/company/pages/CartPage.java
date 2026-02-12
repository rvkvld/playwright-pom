package com.company.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CartPage {
  private final Page page;

  private final Locator cartLink;
  private final Locator emptyCartMessage;
  private final Locator emailInput;
  private final Locator subscribeButton;
  private final Locator subscriptionSuccessMessage;

  public CartPage(Page page) {
    this.page = page;
    this.cartLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Cart"));
    this.emptyCartMessage = page.getByText("Cart is empty!");
    this.emailInput = page.getByPlaceholder("Your email address");
    this.subscribeButton = page.getByRole(AriaRole.BUTTON);
    this.subscriptionSuccessMessage = page.getByText("You have been successfully subscribed!");
    log.debug("CartPage initialized with locators");
  }

  public void open(String baseUrl) {
    log.info("Opening base URL: {}", baseUrl);
    page.navigate(baseUrl);
    cartLink.click();
    log.debug("Clicked Cart link");
  }

  public void assertCartIsEmpty() {
    log.info("Asserting cart is empty");
    com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat(emptyCartMessage)
        .isVisible();
  }

  public void subscribe(String email) {
    log.info("Subscribing with email: {}", email);
    emailInput.fill(email);
    subscribeButton.click();
  }

  public void assertSubscriptionSuccess() {
    log.info("Asserting subscription success message is visible");
    com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat(subscriptionSuccessMessage)
        .isVisible();
  }
}
