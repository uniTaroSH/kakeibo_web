package models.validators;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import models.Record;

public class RecordValidator {
    public static List<String> validate(Record r) {
        List<String> errors = new ArrayList<String>();

        String date_error = _validateDate(r.getDate());
        if(!date_error.equals("")) {
            errors.add(date_error);
        }

        String item_error = _validateItem(r.getItem());
        if(!item_error.equals("")) {
            errors.add(item_error);
        }


        //タグは未入力でもOK


        //収支は両方ともnullまたは空欄であった場合、エラーとする。
        String incomeAndSpending_error = _validateIncomeAndSpending(r.getIncome(), r.getSpending());
        if(!incomeAndSpending_error.equals("")){
            errors.add(incomeAndSpending_error);
        }

        return errors;
    }


    private static String _validateDate(Date date) {
        if(date == null || date.equals("")) {
            return "日付を入力してください。";
            }

        return "";
    }


    private static String _validateItem(String item) {
        if(item == null || item.equals("")) {
            return "項目を入力してください。";
            }

        return "";
    }


    //エラーパターン：両方null、両方空白、片方がnullで片方が空白
    private static String _validateIncomeAndSpending(Integer income, Integer spending){
        if((income == null && spending == null)
        || (income.equals("") && spending.equals(""))
        || (income == null && spending.equals(""))
        || (income.equals("") && spending == null)){
            return "収入または支出のどちらかを入力してください。";
        }

        return "";
    }


}
