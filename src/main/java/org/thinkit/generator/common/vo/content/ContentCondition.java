/*
 * Copyright 2020 Kato Shinya.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
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