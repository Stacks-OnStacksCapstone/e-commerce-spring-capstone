package com.revature.stepimplementations.passwordreset;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.revature.stepimplementations.hooks.Hooks;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.beans.factory.annotation.Value;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.NoSuchElementException;

public class PasswordResetSteps {
    private static String apiToken;
    private static String accountNumber;
    private static String inboxNumber;
    private static String messageNumber;
    private static String passwordResetLink;

    @Before(order=3)
    public static void setupLocalVariables() throws IOException {
        // Loading the YAML file from the /resources folder
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        File file = new File(classLoader.getResource("testing.yml").getFile());
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        LinkedHashMap data = (LinkedHashMap) objectMapper.readValue(file, Object.class);
        accountNumber = ((Integer) ((LinkedHashMap) data.get("mailtrap")).get("account-number")).toString();
        inboxNumber = ((Integer) ((LinkedHashMap) data.get("mailtrap")).get("inbox-number")).toString();
        apiToken = (String) ((LinkedHashMap) data.get("mailtrap")).get("api-token");
    }

    @When("the user enters {string} as the email")
    public void the_user_enters_as_the_email(String email) {
        Hooks.passwordResetPage.emailInput.sendKeys(email);
    }

    @When("the user clicks on the send password reset link button")
    public void the_user_clicks_on_the_send_password_reset_link_button() {
        Hooks.passwordResetPage.passwordResetLinkButton.click();
    }

    @Then("the user should see a message saying {string}")
    public void the_user_should_see_a_message_saying(String expectedMessage) {
        Hooks.wait.ignoring(NoSuchElementException.class).until(ExpectedConditions.visibilityOf(Hooks.passwordResetPage.message));
        String actualMessage = Hooks.passwordResetPage.message.getText();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @When("the user goes to the reset link")
    public void the_user_goes_to_the_reset_link() throws IOException {
        //TODO: call mailtrap API to get token and navigate to the link given

        // 1. Get list of messages in inbox
        URL url = new URL("https://mailtrap.io/api/accounts/" + accountNumber + "/inboxes/" + inboxNumber + "/messages");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Api-Token", apiToken);
        int responseCode = con.getResponseCode();

        if (responseCode<200 || responseCode>300) {
            Assertions.fail("Unable to check inbox");
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine = null;
        StringBuffer responseBody = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            responseBody.append(inputLine);
        }
        in.close();
        String response = responseBody.toString();

        // 2. Get message number of the newest message
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(response);
            JSONArray jsonArray = (JSONArray) obj;
            JSONObject messageDetails = (JSONObject) jsonArray.get(0);
            messageNumber = ((Long) messageDetails.get("id")).toString();

        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }

        // 3. Get the contents of that message
        url = new URL("https://mailtrap.io/api/accounts/" + accountNumber + "/inboxes/" + inboxNumber + "/messages/" + messageNumber + "/body.raw");
        con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Api-Token", apiToken);
        responseCode = con.getResponseCode();

        if (responseCode<200 || responseCode>300) {
            Assertions.fail("Unable to check inbox");
        }

        in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        // 4. The last non-null line of the email will be the password reset link
        while ((inputLine = in.readLine()) != null) {
            passwordResetLink = inputLine;
        }
        in.close();

        Assertions.assertTrue(passwordResetLink.contains("http://localhost:3000/reset-password/"));
        Hooks.driver.get(passwordResetLink);
        Hooks.wait.until(ExpectedConditions.urlToBe(passwordResetLink));
    }

    @When("the user enters {string} as their new password")
    public void the_user_enters_as_their_new_password(String password) {
        Hooks.wait.ignoring(NoSuchElementException.class).until(ExpectedConditions.visibilityOf(Hooks.passwordResetPage.passwordInput));
        Hooks.passwordResetPage.passwordInput.sendKeys(password);
        // Need to tab out of password field to get the error message to display
        Hooks.actions.keyDown(Keys.TAB).keyUp(Keys.TAB).build().perform();
    }

    @When("the user clicks on the password reset button")
    public void the_user_clicks_on_the_reset_password_button() {
        Hooks.passwordResetPage.passwordResetButton.click();
    }

}
