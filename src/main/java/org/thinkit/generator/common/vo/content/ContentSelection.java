/**
 * Project Name : generator-commons<br>
 * File Name : ContentSelection.java<br>
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
 * コンテンツ選択項目を管理するデータクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@Getter
@ToString
@EqualsAndHashCode
public final class ContentSelection implements Serializable {

    /**
     * シリアルバージョンUID
     */
    private static final long serialVersionUID = 54042329729393337L;

    /**
     * キー
     */
    private String key;

    /**
     * 値
     */
    private String value;

    /**
     * デフォルトコンストラクタ
     */
    private ContentSelection() {
    }

    /**
     * コンストラクタ
     *
     * @param key   キー
     * @param value 値
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private ContentSelection(@NonNull String key, @NonNull String value) {
        this.key = key;
        this.value = value;
    }

    /**
     * コピーコンストラクタ
     *
     * @param contentSelection コンテンツ選択項目
     *
     * @exception NullPointerException 引数として {@code null} が渡されたば場合
     */
    private ContentSelection(@NonNull ContentSelection contentSelection) {
        this.key = contentSelection.getKey();
        this.value = contentSelection.getValue();
    }

    /**
     * 引数として指定された情報を基に {@link ContentSelection} クラスの新しいインスタンスを生成し返却します。
     *
     * @param key   キー
     * @param value 値
     * @return {@link ContentSelection} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡されたば場合
     */
    public static ContentSelection of(@NonNull String key, @NonNull String value) {
        return new ContentSelection(key, value);
    }

    /**
     * 引数として指定された {@code contentSelection} オブジェクトの情報を基に {@link ContentSelection}
     * クラスの新しいインスタンスを生成し返却します。
     *
     * @param contentSelection コンテンツ選択項目
     * @return {@link ContentSelection} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡されたば場合
     */
    public static ContentSelection of(@NonNull ContentSelection contentSelection) {
        return new ContentSelection(contentSelection);
    }
}