package models.validators;

import java.util.ArrayList;
import java.util.List;

import models.Kakeibo;

public class KakeiboValidator {
    public static List<String> validate(Kakeibo k) {
        List<String> errors = new ArrayList<String>();

        String pageName_error = _validateTitle(k.getPageName());
        if(!pageName_error.equals("")) {
            errors.add(pageName_error);
        }


        return errors;
    }

    private static String _validateTitle(String pageName) {
        if(pageName == null || pageName.equals("")) {
            return "家計簿の名前を入力してください。";
            }

        return "";
    }
}