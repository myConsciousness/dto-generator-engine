/**
 * Project Name : generator-commons<br>
 * File Name : DtoDefinition.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/05/05<br>
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
import lombok.Setter;
import lombok.ToString;

/**
 * DTO定義の情報を管理するデータクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
public final class DtoDefinition implements Serializable {

    /**
     * シリアルバージョンUID
     */
    private static final long serialVersionUID = -2315269404994313179L;

    /**
     * クラス名
     */
    private String className;

    /**
     * 説明
     */
    private String description;

    /**
     * DTOフィールドグループ
     */
    private DtoFieldGroup dtoFieldGroup;

    /**
     * デフォルトコンストラクタ
     */
    public DtoDefinition() {
    }

    /**
     * コピーコンストラクタ
     *
     * @param dtoDefinition DTO定義
     */
    public DtoDefinition(@NonNull DtoDefinition dtoDefinition) {
        this.className = dtoDefinition.getClassName();
        this.description = dtoDefinition.getDescription();
        this.dtoFieldGroup = DtoFieldGroup.of(dtoDefinition.getDtoFieldGroup());
    }
}
