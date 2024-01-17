package contactsS2;

import java.util.regex.Pattern;

public final class RegexValidator {
    private static final Pattern phonePattern = Pattern.compile(
            "^\\+?([\\da-zA-Z]+[\\s-]?)?(\\([\\da-zA-Z]{2,}(\\)[\\s-]|\\)$))?([\\da-zA-Z]{2,}[\\s-]?)*([\\da-zA-Z]{2,})?$"
    );

    private RegexValidator() {

    }

    public static boolean isValidPhoneNumber(CharSequence phone) {
        return phonePattern.matcher(phone).matches();
    }

    public static String validateAndResetPhoneNumber(String phoneNo) {
        if (RegexValidator.isValidPhoneNumber(phoneNo)) {
            return phoneNo;  // Return the valid phone number as is
        } else {
            return "[no number]";  // Reset to "[no number]" for invalid input
        }
    }


    // ^: Start of the string.
    // \\+?: Optional plus symbol (? makes the plus symbol optional).
    //\\(?: Optional opening parenthesis (? makes the parenthesis optional).
    //*([a-zA-Z0-9]{1,}): First group, which can contain uppercase letters,
    //* lowercase letters, and digits. Must have at least one symbol ({1,}).
    // \\)?: Optional closing parenthesis (? makes the parenthesis optional).
    // [- ]?: Optional dash or space between groups (? makes the dash or space optional).
    //*([a-zA-Z0-9]{2,}): Second group, which can contain uppercase letters,
    //*lowercase letters, and digits. Must have at least two symbols ({2,}).
    //$: End of the string.
}
