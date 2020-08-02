/**
 * Project Name : generator-commons<br>
 * File Name : DtoCreator.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/05/02<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.common.vo.dto;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 * DTO作成者の情報を管理するデータクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@Getter
@ToString
@EqualsAndHashCode
public final class DtoCreator implements Serializable {

    /**
     * シリアルバージョンUID
     */
    private static final long serialVersionUID = 2088194268330223984L;

    /**
     * 作成者
     */
    private String creator;

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
    private DtoCreator() {
    }

    /**
     * コンストラクタ
     *
     * @param creator      作成者
     * @param creationDate 作成日付
     * @param updateDate   更新日付
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private DtoCreator(@NonNull String creator, @NonNull String creationDate, @NonNull String updateDate) {
        this.creator = creator;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
    }

    /**
     * コピーコンストラクタ
     *
     * @param dtoCreator DTO作成者
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private DtoCreator(@NonNull DtoCreator dtoCreator) {
        this.creator = dtoCreator.getCreator();
        this.creationDate = dtoCreator.getCreationDate();
        this.updateDate = dtoCreator.getUpdateDate();
    }

    /**
     * 引数として渡された情報を基に {@link DtoCreator} クラスの新しいインスタンスを生成し返却します。
     *
     * @param creator      作成者
     * @param creationDate 作成日付
     * @param updateDate   更新日付
     * @return {@link DtoCreator} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static DtoCreator of(@NonNull String creator, @NonNull String creationDate, @NonNull String updateDate) {
        return new DtoCreator(creator, creationDate, updateDate);
    }

    /**
     * 引数として渡された {@code dtoCreator} オブジェクトの情報を基に {@link DtoCreator}
     * クラスの新しいインスタンスを生成し返却します。
     *
     * @param dtoCreator DTO作成者
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static DtoCreator of(@NonNull DtoCreator dtoCreator) {
        return new DtoCreator(dtoCreator);
    }
}
