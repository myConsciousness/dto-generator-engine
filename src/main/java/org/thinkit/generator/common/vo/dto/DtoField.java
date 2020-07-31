/**
 * Project Name : generator-commons<br>
 * File Name : DtoField.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/05/04<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.common.vo.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

/**
 * DTOフィールドの情報を管理するデータクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@Getter
@ToString
@EqualsAndHashCode
public final class DtoField {

    /**
     * 変数名
     */
    private String variableName = "";

    /**
     * データ型
     */
    private String dataType = "";

    /**
     * 初期値
     */
    private String initialValue = "";

    /**
     * 不変
     */
    private boolean invariant = false;

    /**
     * 説明
     */
    private String description = "";

    /**
     * 子クラス定義情報群
     */
    @Setter
    private List<DtoDefinition> childDtoDefinitionList = new ArrayList<>();

    /**
     * デフォルトコンストラクタ
     */
    @SuppressWarnings("unused")
    private DtoField() {
    }

    /**
     * コンストラクタ
     *
     * @param variableName 変数名
     * @param dataType     データ型
     * @param initialValue 初期値
     * @param invariant    不変
     * @param description  説明
     */
    public DtoField(@NonNull String variableName, @NonNull String dataType, @NonNull String initialValue,
            boolean invariant, @NonNull String description) {
        this.variableName = variableName;
        this.dataType = dataType;
        this.initialValue = initialValue;
        this.invariant = invariant;
        this.description = description;
    }

    /**
     * コピーコンストラクタ
     *
     * @param dtoField DTOフィールド
     */
    public DtoField(@NonNull DtoField dtoField) {
        this.variableName = dtoField.getVariableName();
        this.dataType = dtoField.getDataType();
        this.initialValue = dtoField.getInitialValue();
        this.invariant = dtoField.isInvariant();
        this.description = dtoField.getDescription();
        this.childDtoDefinitionList = new ArrayList<>(dtoField.getChildDtoDefinitionList());
    }
}
