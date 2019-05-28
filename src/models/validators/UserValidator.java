package models.validators;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import models.User;
import utils.DBUtil;

public class UserValidator {
    public static List<String> validate(User u, Boolean code_duplicate_check_flag, Boolean password_check_flag) {
        List<String> errors = new ArrayList<String>();


        //ユーザーIDのエラー有無
        String code_error = _validateCode(u.getCode(), code_duplicate_check_flag);
        if(!code_error.equals("")) {
            errors.add(code_error);
        }


        //ユーザー名のエラー有無
        String name_error = _validateName(u.getName());
        if(!name_error.equals("")) {
            errors.add(name_error);
        }


        //パスワードのエラー有無
        String password_error = _validatePassword(u.getPassword(), password_check_flag);
        if(!password_error.equals("")) {
            errors.add(password_error);
        }

        return errors;
    }



    // ユーザーIDのエラーチェック
    private static String _validateCode(String code, Boolean code_duplicate_check_flag) {
        // 必須入力チェック
        if(code == null || code.equals("")) {
            return "ユーザーIDを入力してください。";
        }


        // 既に登録されているユーザーIDとの重複チェック
        if(code_duplicate_check_flag) {
            EntityManager em = DBUtil.createEntityManager();
            long users_count = (long)em.createNamedQuery("checkRegisteredCode", Long.class)
                                        .setParameter("code", code)
                                        .getSingleResult();


            em.close();
            if(users_count > 0) {
                return "入力された社員番号の情報は既に存在しています。";
            }
        }


        return "";
    }


    // 社員名の必須入力チェック
    private static String _validateName(String name) {
        if(name == null || name.equals("")) {
            return "氏名を入力してください。";
        }


        return "";
    }


    // パスワードの必須入力チェック
    private static String _validatePassword(String password, Boolean password_check_flag) {
        // パスワードを変更する場合のみ実行
        if(password_check_flag && (password == null || password.equals(""))) {
            return "パスワードを入力してください。";
        }


        return "";
    }


}
