package org.example;

/**
 * User
 */
public class User {
    public static ValidateResult validateUser(
        String name, int age, String email, String address,
        String phone, String gender, String registrationDate,
        boolean subscriptionStatus
    ) {
        // 初期状態
        boolean success = !name.isEmpty() && age >= 0 && email.contains("@") && isValidPhone(phone);
        String errorMessage = null;
        String generatedId = success ? "USER123" : null;
        String logMessage = success ? "Success: User registered" : "Error: Validation failed";

        // バリデーション
        if (name.isEmpty()) {
            success = false;
            errorMessage = "名前は必須です";
            logMessage = "Error: Missing name";
        } else if (age < 0) {
            success = false;
            errorMessage = "年齢は0以上である必要があります";
            logMessage = "Error: Invalid age";
        } else if (!email.contains("@")) {
            success = false;
            errorMessage = "メールアドレスの形式が不正です";
            logMessage = "Error: Invalid email";
        } else if (!isValidPhone(phone)) {
            success = false;
            errorMessage = "電話番号の形式が不正です";
            logMessage = "Error: Invalid phone number";
        }

        return new ValidateResult(success, errorMessage, generatedId, logMessage);
    }

    // 電話番号の形式を検証するヘルパーメソッド
    private static boolean isValidPhone(String phone) {
        // 日本の電話番号フォーマット（例: 090-1234-5678）を検証
        return phone != null && phone.matches("^0\\d{1,3}-\\d{1,4}-\\d{4}$");
    }
}
