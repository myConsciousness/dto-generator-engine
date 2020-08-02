/**
 * Project Name : generator-commons<br>
 * File Name : ContentCondition.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/07/25<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.common.vo.content;

import java.io.Serializable;

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
@ToString
@EqualsAndHashCode
public final class ContentCondition implements Serializable {

    /**
     * シリアルバージョンUID
     */
    private static final long serialVersionUID = 464752727099823500L;

    /**
     * キー
     */
    @Getter
    private String key;

    /**
     * 演算子
     */
    @Getter
    private String operand;

    /**
     * 値
     */
    @Getter
    private String value;

    /**
     * デフォルトコンストラクタ
     */
    private ContentCondition() {
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
    private ContentCondition(@NonNull String key, @NonNull String operand, @NonNull String value) {
        this.key = key;
        this.operand = operand;
        this.value = value;
    }

    /**
     * コピーコンストラクタ
     */
    private ContentCondition(@NonNull ContentCondition contentcondition) {
        this.key = contentcondition.getKey();
        this.operand = contentcondition.getOperand();
        this.value = contentcondition.getValue();
    }

    /**
     * 引数として渡された情報を基に {@link ContentCondition} クラスの新しいインスタンスを生成し返却します。
     *
     * @param key     キー
     * @param operand 演算子
     * @param value   値
     * @return {@link ContentCondition} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static ContentCondition of(@NonNull String key, @NonNull String operand, @NonNull String value) {
        return new ContentCondition(key, operand, value);
    }

    /**
     * 引数として渡された {@code contentcondition} オブジェクトの情報を基に {@link ContentCondition}
     * クラスの新しいインスタンスを生成し返却します。
     *
     * @param Contentcondition 条件オブジェクト
     * @return {@link ContentCondition} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static ContentCondition of(@NonNull ContentCondition contentcondition) {
        return new ContentCondition(contentcondition);
    }
}