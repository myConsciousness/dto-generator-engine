/**
 * Project Name : generator-commons<br>
 * File Name : DtoMatrix.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/04/23<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.<br>
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
 * DTOマトリクスを管理するデータクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@Getter
@ToString
@EqualsAndHashCode
public final class DtoMatrix implements Serializable {

    /**
     * シリアルバージョンUID
     */
    private static final long serialVersionUID = -8358418392349994438L;

    /**
     * DTOメタ
     */
    private DtoMeta dtoMeta;

    /**
     * DTO作成者
     */
    private DtoCreator dtoCreator;

    /**
     * DTO定義グループ
     */
    private DtoDefinitionGroup dtoDefinitionGroup;

    /**
     * デフォルトコンストラクタ
     */
    private DtoMatrix() {
    }

    /**
     * コンストラクタ
     *
     * @param dtoMeta            DTOメタ
     * @param dtoCreator         DTO作成者
     * @param dtoDefinitionGroup DTO定義グループ
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private DtoMatrix(@NonNull DtoMeta dtoMeta, @NonNull DtoCreator dtoCreator,
            @NonNull DtoDefinitionGroup dtoDefinitionGroup) {
        this.dtoMeta = DtoMeta.of(dtoMeta);
        this.dtoCreator = DtoCreator.of(dtoCreator);
        this.dtoDefinitionGroup = DtoDefinitionGroup.of(dtoDefinitionGroup);
    }

    /**
     * コピーコンストラクタ
     *
     * @param dtoMatrix DTOマトリクス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private DtoMatrix(@NonNull DtoMatrix dtoMatrix) {
        this.dtoMeta = DtoMeta.of(dtoMatrix.getDtoMeta());
        this.dtoCreator = DtoCreator.of(dtoMatrix.getDtoCreator());
        this.dtoDefinitionGroup = DtoDefinitionGroup.of(dtoMatrix.getDtoDefinitionGroup());
    }

    /**
     * 引数として渡された情報を基に {@link DtoMatrix} クラスの新しいインスタンスを生成し返却します。
     *
     * @param dtoMeta            DTOメタ
     * @param dtoCreator         DTO作成者
     * @param dtoDefinitionGroup DTO定義グループ
     * @return {@link DtoMatrix} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static DtoMatrix of(@NonNull DtoMeta dtoMeta, @NonNull DtoCreator dtoCreator,
            @NonNull DtoDefinitionGroup dtoDefinitionGroup) {
        return new DtoMatrix(dtoMeta, dtoCreator, dtoDefinitionGroup);
    }

    /**
     * 引数として渡された {@code dtoMatrix} オブジェクトの情報を基に {@link DtoMatrix}
     * クラスの新しいインスタンスを生成し返却します。
     *
     * @param dtoMatrix DTOマトリクス
     * @return {@link DtoMatrix} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static DtoMatrix of(@NonNull DtoMatrix dtoMatrix) {
        return new DtoMatrix(dtoMatrix);
    }
}
