/**
 * Project Name : generator-commons<br>
 * File Name : Condition.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/07/25<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.common.dto.contentgenerator;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 * コンテンツの条件を管理スデータクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
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

    /**
     * 引数として渡された情報を基に {@link Condition} クラスの新しいインスタンスを生成し返却します。
     *
     * @param key     キー
     * @param operand 演算子
     * @param value   値
     * @return {@link Condition} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static Condition of(@NonNull String key, @NonNull String operand, @NonNull String value) {
        return new Condition(key, operand, value);
    }

    /**
     * 引数として渡された {@code condition} オブジェクトの情報を基に {@link Condition}
     * クラスの新しいインスタンスを生成し返却します。
     *
     * @param condition 条件オブジェクト
     * @return {@link Condition} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static Condition of(@NonNull Condition condition) {
        return new Condition(condition);
    }
}