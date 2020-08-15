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