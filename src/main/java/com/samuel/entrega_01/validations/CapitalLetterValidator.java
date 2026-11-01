package com.samuel.entrega_01.validations;

import com.samuel.entrega_01.i18n.I18n;
import org.netbeans.validation.api.Problems;
import org.netbeans.validation.api.Validator;

public class CapitalLetterValidator implements Validator<String> {
    @Override
    public Class<String> modelType() {// This tells the library what data type I'm validating
        return String.class;
    }

    @Override
    public void validate(Problems problems, String fieldName, String userText) {
        if(!isFirstLetterCapitalised(userText)){
            problems.add(I18n.get("validation.name.capital"));
        }
    }

    public static boolean isFirstLetterCapitalised(String text){
        if(text == null || text.trim().isEmpty()){
            return false;
        }
        return Character.isUpperCase(text.trim().charAt(0));
    }
}
