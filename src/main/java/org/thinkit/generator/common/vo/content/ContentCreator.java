/**
 * Project Name : generator-commons<br>
 * File Name : ContentCreator.java<br>
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
 * コンテンツの作成者を管理するデータクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@Getter
@ToString
@EqualsAndHashCode
public final class ContentCreator implements Serializable {

    /**
     * シリアルバージョンUID
     */
    private static final long serialVersionUID = -7538909999016433540L;

    /**
     * 作成者
     */
    private String author;

    /**
     * 作成日付
     */
    private String creationDate;

    /**
     * 更新日付
     */
    private String updateDate;

    /**
     * デフォルトコンストラクタ
     */
    private ContentCreator() {
    }

    /**
     * コンストラクタ
     *
     * @param author       作成者
     * @param creationDate 作成日付
     * @param updateDate   更新日付
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private ContentCreator(@NonNull String author, @NonNull String creationDate, @NonNull String updateDate) {
        this.author = author;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
    }

    /**
     * コピーコンストラクタ
     *
     * @param contentCreator コンテンツ作成者
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private ContentCreator(@NonNull ContentCreator contentCreator) {
        this.author = contentCreator.getAuthor();
        this.creationDate = contentCreator.getCreationDate();
        this.updateDate = contentCreator.getUpdateDate();
    }

    /**
     * 引数として指定された情報を基に {@link ContentCreator} クラスの新しいインスタンスを生成し返却します。
     *
     * @param author       作成者
     * @param creationDate 作成日付
     * @param updateDate   更新日付
     * @return {@link ContentCreator} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static ContentCreator of(@NonNull String author, @NonNull String creationDate, @NonNull String updateDate) {
        return new ContentCreator(author, creationDate, updateDate);
    }

    /**
     * 引数として指定された {@code contentCreator} オブジェクトの情報を基に {@link ContentCreator}
     * クラスの新しいインスタンスを生成し返却します。
     *
     * @param contentCreator コンテンツ作成者
     * @return {@link ContentCreator} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static ContentCreator of(@NonNull ContentCreator contentCreator) {
        return new ContentCreator(contentCreator);
    }
}