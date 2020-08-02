/**
 * Project Name : generator-commons<br>
 * File Name : ContentVersion.java<br>
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