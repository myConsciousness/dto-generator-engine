/**
 * Project Name : generator-commons<br>
 * File Name : ConditionNodeKey.java<br>
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
 * コンテンツの条件ノードキーを管理するカタログです。
 * <p>
 * 以下の静的メソッドを使用することで各要素の文字列表現を取得することができます。<br>
 * {@link #conditionId()} <br>
 * {@link #exclude()}
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@RequiredArgsConstructor
public enum ConditionNodeKey implements Catalog<ConditionNodeKey> {

    /**
     * 条件ID
     */
    CONDITION_ID(0, "conditionId"),

    /**
     * 除外
     */
    EXCLUDE(1, "exclude");

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
     * {@link #CONDITION_ID} 要素の文字列表現を返却します。
     *
     * @return {@link #CONDITION_ID} 要素の文字列表現
     * @see #CONDITION_ID
     */
    public static String conditionId() {
        return CONDITION_ID.getKey();
    }

    /**
     * {@link #EXCLUDE} 要素の文字列表現を返却します。
     *
     * @return {@link #EXCLUDE} 要素の文字列表現
     * @see #EXCLUDE
     */
    public static String exclude() {
        return EXCLUDE.getKey();
    }
}