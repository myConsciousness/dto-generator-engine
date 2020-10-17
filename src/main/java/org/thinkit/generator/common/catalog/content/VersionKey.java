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

import org.thinkit.api.catalog.Catalog;

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
public enum VersionKey implements Catalog<VersionKey> {

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