package org.thinkit.generator.common.dto.contentgenerator;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public final class Condition {

    /**
     * キー
     */
    private String key;

    /**
     * 演算子
     */
    private String operand;

    /**
     * 値
     */
    private String value;

    /**
     * デフォルトコンストラクタ
     */
    private Condition() {
    }

    /**
     * コンストラクタ
     *
     * @param key     キー
     * @param operand 演算子
     * @param value   値
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private Condition(@NonNull String key, @NonNull String operand, @NonNull String value) {
        this.key = key;
        this.operand = operand;
        this.value = value;
    }

    /**
     * コピーコンストラクタ
     */
    private Condition(@NonNull Condition condition) {
        this.key = condition.getKey();
        this.operand = condition.getOperand();
        this.value = condition.getValue();
    }
}