package top.liisla.electronicphotoalbum.Rely;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

@Component
public class EmailValidator {
    private static final String EMAIL_PATTERN = "^[\\w.-]+@([\\w\\-]+\\.)+[A-Z|a-z]{2,3}$";

    public static boolean validate(String email) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
