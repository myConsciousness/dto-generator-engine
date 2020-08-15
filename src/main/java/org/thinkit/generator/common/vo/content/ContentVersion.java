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
 * コンテンツバージョンを管理するデータクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@Getter
@ToString
@EqualsAndHashCode
public final class ContentVersion implements Serializable {

    /**
     * シリアルバージョンUID
     */
    private static final long serialVersionUID = -5276153750305168221L;

    /**
     * 作成時バージョン
     */
    private String since;

    /**
     * 現行バージョン
     */
    private String version;

    /**
     * デフォルトコンストラクタ
     */
    private ContentVersion() {
    }

    /**
     * コンストラクタ
     *
     * @param since   作成時バージョン
     * @param version 現行バージョン
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private ContentVersion(@NonNull String since, @NonNull String version) {
        this.since = since;
        this.version = version;
    }

    /**
     * コピーコンストラクタ
     *
     * @param contentVersion コンテンツバージョン
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private ContentVersion(@NonNull ContentVersion contentVersion) {
        this.since = contentVersion.getSince();
        this.version = contentVersion.getVersion();
    }

    /**
     * 引数として渡された情報を基に {@link ContentVersion} クラスの新しいインスタンスを生成し返却します。
     *
     * @param since   作成時バージョン
     * @param version 現行バージョン
     * @return {@link ContentVersion} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static ContentVersion of(@NonNull String since, @NonNull String version) {
        return new ContentVersion(since, version);
    }

    /**
     * 引数として渡された {@code contentVersion} オブジェクトの情報を基に {@link ContentVersion}
     * クラスの新しいインスタンスを生成し返却します。
     *
     * @param contentVersion コンテンツバージョン
     * @return {@link ContentVersion} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static ContentVersion of(@NonNull ContentVersion contentVersion) {
        return new ContentVersion(contentVersion);
    }
}