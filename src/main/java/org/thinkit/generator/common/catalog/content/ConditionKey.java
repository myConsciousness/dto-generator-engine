/**
 * Project Name : generator-commons<br>
 * File Name : ConditionKey.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/08/03<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.common.catalog.content;

import org.thinkit.common.catalog.Catalog;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * コンテンツの条件キーを管理するカタログです。
 * <p>
 * 以下の静的メソッドを使用することで各要素の文字列表現を取得することができます。<br>
 * {@link #keyName()} <br>
 * {@link #operand()} <br>
 * {@link #value()}
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@RequiredArgsConstructor
public enum ConditionKey implements Catalog<ConditionKey> {

    /**
     * キー名
     */
    KEY_NAME(0, "keyName"),

    /**
     * 演算子
     */
    OPERAND(1, "operand"),

    /**
     * 値
     */
    VALUE(2, "value");

    /**
     * コード値
     */
    @Getter
    private final int code;

    /**
     * キー
     */
    @Getter
    private final String key;

    /**
     * {@link #KEY_NAME} 要素の文字列表現を返却します。
     *
     * @return {@link #KEY_NAME} 要素の文字列表現
     * @see #KEY_NAME
     */
    public static String keyName() {
        return KEY_NAME.getKey();
    }

    /**
     * {@link #OPERAND} 要素の文字列表現を返却します。
     *
     * @return {@link #OPERAND} 要素の文字列表現
     * @see #OPERAND
     */
    public static String operand() {
        return OPERAND.getKey();
    }

    /**
     * {@link #VALUE} 要素の文字列表現を返却します。
     *
     * @return {@link #VALUE} 要素の文字列表現
     * @see #VALUE
     */
    public static String value() {
        return VALUE.getKey();
    }
}