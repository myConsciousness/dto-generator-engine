/**
 * Project Name : generator-commons<br>
 * File Name : VesionKey.java<br>
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
 * コンテンツのバージョンキーを管理するカタログです。
 * <p>
 * 以下の静的メソッドを使用することで各要素の文字列表現を取得することができます。<br>
 * {@link #since()} <br>
 * {@link #version()}
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@RequiredArgsConstructor
public enum VesionKey implements Catalog<VesionKey> {

    /**
     * 作成時バージョン
     */
    SINCE(0, "since"),

    /**
     * 現行バージョン
     */
    VERSION(1, "version");

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
     * {@link #SINCE} 要素の文字列表現を返却します。
     *
     * @return {@link #SINCE} 要素の文字列表現
     * @see #SINCE
     */
    public static String since() {
        return SINCE.getKey();
    }

    /**
     * {@link #VERSION} 要素の文字列表現を返却します。
     *
     * @return {@link #VERSION} 要素の文字列表現
     * @see #VERSION
     */
    public static String version() {
        return VERSION.getKey();
    }
}