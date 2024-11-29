package org.example.before;

import org.example.ValidateResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.example.User.validateUser;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("ユーザーの検証テスト")
public class ValidateUserTest {

    @Test
    @DisplayName("有効なユーザーかどうか判定")
    void testValidateUser() {

        // 正常系
        assertEquals(new ValidateResult(true, null, "USER123", "Success: User registered"),
            validateUser(
                "山田太郎", 30, "yamada@example.com", "東京都xx区1-1-1",
                "090-1234-5678", "男性", "2024-11-29", true
            )
        );

        // 名前が未入力
        assertEquals(new ValidateResult(false, "名前は必須です", null, "Error: Missing name"),
            validateUser(
                "", 30, "yamada@example.com", "東京都xx区1-1-1",
                "090-1234-5678", "男性", "2024-11-29", true
            )
        );

        // 年齢が不正
        assertEquals(new ValidateResult(false, "年齢は0以上である必要があります", null, "Error: Invalid age"),
            validateUser(
                "山田太郎", -1, "yamada@example.com", "東京都xx区1-1-1",
                "090-1234-5678", "男性", "2024-11-29", true
            )
        );

        // メールアドレスが不正
        assertEquals(new ValidateResult(false, "メールアドレスの形式が不正です", null, "Error: Invalid email"),
            validateUser(
                "山田太郎", 30, "invalid-email", "東京都xx区1-1-1",
                "090-1234-5678", "男性", "2024-11-29", true
            )
        );

        // 電話番号が不正
        assertEquals(new ValidateResult(false, "電話番号の形式が不正です", null, "Error: Invalid phone number"),
            validateUser(
                "山田太郎", 30, "yamada@example.com", "東京都xx区1-1-1",
                "09012345678", "男性", "2024-11-29", true
            )
        );
    }
}
