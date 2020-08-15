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
 * コンテンツのメタを管理するデータクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@Getter
@ToString
@EqualsAndHashCode
public final class ContentMeta implements Serializable {

    /**
     * シリアルバージョンUID
     */
    private static final long serialVersionUID = -3651293890431000252L;

    /**
     * パッケージ名
     */
    private String packageName;

    /**
     * コンテンツ名
     */
    private String contentName;

    /**
     * エンコード方式
     */
    private String encoding;

    /**
     * 説明
     */
    private String description;

    /**
     * デフォルトコンストラクタ
     */
    private ContentMeta() {
    }

    /**
     * コンストラクタ
     *
     * @param packageName パッケージ名
     * @param contentName コンテンツ名
     * @param encoding    エンコード方式
     * @param description 説明
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private ContentMeta(@NonNull String packageName, @NonNull String contentName, @NonNull String encoding,
            @NonNull String description) {
        this.packageName = packageName;
        this.contentName = contentName;
        this.encoding = encoding;
        this.description = description;
    }

    /**
     * コピーコンストラクタ
     *
     * @param contentMeta コンテンツメタ
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private ContentMeta(@NonNull ContentMeta contentMeta) {
        this.packageName = contentMeta.getPackageName();
        this.contentName = contentMeta.getContentName();
        this.encoding = contentMeta.getEncoding();
        this.description = contentMeta.getDescription();
    }

    /**
     * 引数として指定された情報を基に {@link ContentMeta} クラスの新しいインスタンスを生成し返却します。
     *
     * @param packageName パッケージ名
     * @param contentName コンテンツ名
     * @param encoding    エンコード方式
     * @param description 説明
     * @return {@link ContentMeta} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static ContentMeta of(@NonNull String packageName, @NonNull String contentName, @NonNull String encoding,
            @NonNull String description) {
        return new ContentMeta(packageName, contentName, encoding, description);
    }

    /**
     * 引数として指定された {@code contentMeta} オブジェクトの情報を基に {@link ContentMeta}
     * クラスの新しいインスタンスを生成し返却します。
     *
     * @param contentMeta コンテンツメタ
     * @return {@link ContentMeta} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static ContentMeta of(@NonNull ContentMeta contentMeta) {
        return new ContentMeta(contentMeta);
    }
}