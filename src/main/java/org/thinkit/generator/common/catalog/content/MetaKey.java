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

package org.thinkit.generator.common.catalog.content;

import org.thinkit.common.catalog.Catalog;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * コンテンツのメタキーを管理するカタログです。
 * <p>
 * 以下の静的メソッドを使用することで各要素の文字列表現を取得することができます。<br>
 * {@link #packageName()} <br>
 * {@link #contentName()} <br>
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
     * コンテンツ名
     */
    CONTENT_NAME(1, "contentName"),

    /**
     * エンコード方式
     */
    ENCODING(2, "encoding"),

    /**
     * 説明
     */
    DESCRIPTION(3, "description");

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
     * {@link #CONTENT_NAME} 要素の文字列表現を返却します。
     *
     * @return {@link #CONTENT_NAME} 要素の文字列表現
     * @see #CONTENT_NAME
     */
    public static String contentName() {
        return CONTENT_NAME.getKey();
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