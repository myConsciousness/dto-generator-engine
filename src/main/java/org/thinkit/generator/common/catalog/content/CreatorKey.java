/**
 * Project Name : generator-commons<br>
 * File Name : CreatorKey.java<br>
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
 * コンテンツの作成者キーを管理するカタログです。
 * <p>
 * 以下の静的メソッドを使用することで各要素の文字列表現を取得することができます。<br>
 * {@link #author()} <br>
 * {@link #creationDate()} <br>
 * {@link #updateDate()}
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@RequiredArgsConstructor
public enum CreatorKey implements Catalog<CreatorKey> {

    /**
     * 作成者
     */
    AUTHOR(0, "author"),

    /**
     * 作成日付
     */
    CREATION_DATE(1, "creationDate"),

    /**
     * 更新日付
     */
    UPDATE_DATE(2, "updateDate");

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
     * {@link #AUTHOR} 要素の文字列表現を返却します。
     *
     * @return {@link #AUTHOR} 要素の文字列表現
     * @see #AUTHOR
     */
    public static String author() {
        return AUTHOR.getKey();
    }

    /**
     * {@link #CREATION_DATE} 要素の文字列表現を返却します。
     *
     * @return {@link #CREATION_DATE} 要素の文字列表現
     * @see #CREATION_DATE
     */
    public static String creationDate() {
        return CREATION_DATE.getKey();
    }

    /**
     * {@link #UPDATE_DATE} 要素の文字列表現を返却します。
     *
     * @return {@link #UPDATE_DATE} 要素の文字列表現
     * @see #UPDATE_DATE
     */
    public static String updateDate() {
        return UPDATE_DATE.getKey();
    }
}