package km.kmwarehouse.services.services;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class ValidationServiceImpl implements ValidationService {
    private final int MINIMAL_LENGTH = 3;
    private final String INVALID_SYMBOLS = "[!#$%^&*()+=\\[\\]{};':\"\\\\|,<>\\/?]+";

    private boolean invalidEmail(String email) {
        return !EmailValidator.getInstance().isValid(email);
    }

    private boolean isEmptyPriceOrUnit(String input) {
        return input.length() < 1;
    }

    private boolean isInvalidPrice(String input) {
        return new BigDecimal(input).compareTo(new BigDecimal("0")) <= 0;
    }

    private boolean isTooShort(String input) {
        return input.length() < MINIMAL_LENGTH;
    }

    private boolean containsInvalidSymbol(String input) {
        Pattern pattern = Pattern.compile(INVALID_SYMBOLS);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    @Override
    public  <T> boolean isValid(T serviceModel) {
        boolean isValid = true;
        List<Field> fieldList = Arrays.stream(serviceModel.getClass().getDeclaredFields())
                .filter(f -> !f.getName().equals("active")).collect(Collectors.toList());
        for (Field field : fieldList) {
            field.setAccessible(true);
            try {
                if (field.getName().contains("price") || field.getName().contains("unit")){
                    if (isEmptyPriceOrUnit(field.get(serviceModel).toString())) {
                        isValid = false;
                    }
                }else {
                    if (isTooShort(field.get(serviceModel).toString())) {
                        isValid = false;
                    }
                }
                if (containsInvalidSymbol(field.get(serviceModel).toString())) {
                    isValid = false;
                }
                if (field.getName().contains("email")) {
                    if (invalidEmail(field.get(serviceModel).toString())) {
                        isValid = false;
                    }
                }
                if (field.getName().contains("price")) {
                    if (isInvalidPrice(field.get(serviceModel).toString())) {
                        isValid = false;
                    }
                }
            }catch (Exception e){
                isValid = false;
            }
            field.setAccessible(false);
        }
        return isValid;
    }
}
