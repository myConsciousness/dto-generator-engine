/**
 * Project Name : generator-commons<br>
 * File Name : MetaKey.java<br>
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
 * コンテンツのメタキーを管理するカタログです。
 * <p>
 * 以下の静的メソッドを使用することで各要素の文字列表現を取得することができます。<br>
 * {@link #packageName()} <br>
 * {@link #encoding()} <br>
 * {@link #description()}
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@RequiredArgsConstructor
public enum MetaKey implements Catalog<MetaKey> {

    /**
     * パッケージ名
     */
    PACKAGE_NAME(0, "packageName"),

    /**
     * エンコード方式
     */
    ENCODING(1, "encoding"),

    /**
     * 説明
     */
    DESCRIPTION(2, "description");

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
     * {@link #PACKAGE_NAME} 要素の文字列表現を返却します。
     *
     * @return {@link #PACKAGE_NAME} 要素の文字列表現
     * @see #PACKAGE_NAME
     */
    public static String packageName() {
        return PACKAGE_NAME.getKey();
    }

    /**
     * {@link #ENCODING} 要素の文字列表現を返却します。
     *
     * @return {@link #ENCODING} 要素の文字列表現
     * @see #ENCODING
     */
    public static String encoding() {
        return ENCODING.getKey();
    }

    /**
     * {@link #DESCRIPTION} 要素の文字列表現を返却します。
     *
     * @return {@link #DESCRIPTION} 要素の文字列表現
     * @see #DESCRIPTION
     */
    public static String description() {
        return DESCRIPTION.getKey();
    }
}