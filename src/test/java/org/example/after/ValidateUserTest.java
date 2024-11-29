package org.example.after;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.ValidateResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.yaml.snakeyaml.Yaml;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.example.User.validateUser;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("ユーザーの検証テスト")
public class ValidateUserTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("loadTestCases")
    @DisplayName("有効なユーザーかどうか判定")
    void testValidateUser(TestCase testCase) { // arrange

        // act
        var actual = validateUser(
            testCase.input.name,
            testCase.input.age,
            testCase.input.email,
            testCase.input.address,
            testCase.input.phone,
            testCase.input.gender,
            testCase.input.registration_date,
            testCase.input.subscription_status
        );

        // assert
        assertEquals(new ValidateResult(
            testCase.expected.success,
            testCase.expected.error_message,
            testCase.expected.generated_id,
            testCase.expected.log_message
        ), actual);
    }

    /**
     * YAMLファイルからテストケースを読み込む処理
     */
    @SuppressWarnings("unchecked")
    private static Stream<TestCase> loadTestCases() {
        // yamlのmerge keyの機能を使いたかったのでSnakeYamlを採用
        var yaml = new Yaml();

        // test-cases.yamlをリソースから取得
        var yamlStream = ValidateUserTest.class.getClassLoader().getResourceAsStream("testcases.yaml");
        if (yamlStream == null) {
            throw new RuntimeException("YAML file not found");
        }

        // SnakeYAMLでYAMLを読み込み（マージキーやアンカーが展開される）
        Map<String, Object> rootNode = yaml.load(yamlStream);

        // test_casesキーの値を取得し、型安全にキャスト
        var testCasesNode = (List<Map<String, Object>>) rootNode.get("test_cases");

        // JacksonのObjectMapperを使ってTestCaseクラスに変換
        var mapper = new ObjectMapper();
        List<TestCase> testCases = mapper.convertValue(testCasesNode, mapper.getTypeFactory().constructCollectionType(List.class, TestCase.class));

        // Streamに変換して返す
        return testCases.stream();
    }


    /**
     * テストケースを表現するClass
     * テストケース名、入力値、期待値を設定している
     */
    private static class TestCase {
        public String name;
        public Input input;
        public Expected expected;

        // ParameterizedTestのnameで使用される文字列を提供するために実装
        @Override
        public String toString() {
            return name;
        }

        private static class Input {
            public String name;
            public int age;
            public String email;
            public String address;
            public String phone;
            public String gender;
            public String registration_date;
            public boolean subscription_status;
        }

        private static class Expected {
            public boolean success;
            public String error_message;
            public String generated_id;
            public String log_message;
        }
    }

}
