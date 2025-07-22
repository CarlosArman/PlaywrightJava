package data;

import models.User;
import org.junit.jupiter.params.provider.Arguments;
import utilities.Logs;

import java.util.List;

public class CustomData {
    public static List<Arguments> getData() {
        final var user = new User();
        Logs.debug("Escribiendo el user: " + user);
        return List.of(
                Arguments.of("", user.getLastname(), user.getZipcode(), "Error: First Name is required"),
                Arguments.of(user.getFirstname(), "", user.getZipcode(), "Error: Last Name is required"),
                Arguments.of(user.getFirstname(), user.getLastname(), "", "Error: Postal Code is required")
        );
    }
}
