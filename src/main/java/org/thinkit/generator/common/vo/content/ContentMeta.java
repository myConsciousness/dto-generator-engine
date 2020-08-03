/**
 * Project Name : generator-commons<br>
 * File Name : ContentMeta.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/08/02<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
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