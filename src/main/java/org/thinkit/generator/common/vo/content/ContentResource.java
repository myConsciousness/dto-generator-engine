/**
 * Project Name : generator-commons<br>
 * File Name : ContentResource.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/07/25<br>
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
 * コンテンツのリソース情報を管理するデータクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@ToString
@EqualsAndHashCode
public final class ContentResource implements Serializable {

    /**
     * シリアルバージョンUID
     */
    private static final long serialVersionUID = 5913289046847116788L;

    /**
     * パッケージ名
     */
    @Getter
    private String packageName;

    /**
     * コンテンツ名
     */
    @Getter
    private String contentName;

    /**
     * コンテンツ
     */
    @Getter
    private String content;

    /**
     * デフォルトコンストラクタ
     */
    private ContentResource() {
    }

    /**
     * コンストラクタ
     *
     * @param packageName パッケージ名
     * @param contentName コンテンツ名
     * @param content     コンテンツ
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private ContentResource(@NonNull String packageName, @NonNull String contentName, @NonNull String content) {
        this.packageName = packageName;
        this.contentName = contentName;
        this.content = content;
    }

    /**
     * コピーコンストラクタ
     *
     * @param contentResource コンテンツリソース
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private ContentResource(@NonNull ContentResource contentResource) {
        this.packageName = contentResource.getPackageName();
        this.contentName = contentResource.getContentName();
        this.content = contentResource.getContent();
    }

    /**
     * 引数として渡された情報を基に {@link ContentResource} クラスの新しいインスタンスを生成し返却します。
     *
     * @param packageName パッケージ名
     * @param contentName コンテンツ名
     * @param content     コンテンツ
     * @return {@link ContentResource} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static ContentResource of(@NonNull String packageName, @NonNull String contentName,
            @NonNull String content) {
        return new ContentResource(packageName, contentName, content);
    }

    /**
     * 引数として渡された {@code contentResource} オブジェクトの情報を基に {@link ContentResource}
     * クラスの新しいインスタンスを生成し返却します。
     *
     * @param contentResource コンテンツリソース
     * @return {@link ContentResource} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static ContentResource of(@NonNull ContentResource contentResource) {
        return new ContentResource(contentResource);
    }
}