/**
 * Project Name : generator-commons<br>
 * File Name : ClassItem.java<br>
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
 * クラス項目定義の情報を管理するデータクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@Getter
@ToString
@EqualsAndHashCode
public final class ClassItem {

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
    private List<ClassDefinition> childClassDefinitionList = new ArrayList<>();

    /**
     * デフォルトコンストラクタ
     */
    @SuppressWarnings("unused")
    private ClassItem() {
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
    public ClassItem(@NonNull String variableName, @NonNull String dataType, @NonNull String initialValue,
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
     * @param classItemDefinition クラス項目定義情報
     */
    public ClassItem(@NonNull ClassItem classItem) {
        this.variableName = classItem.getVariableName();
        this.dataType = classItem.getDataType();
        this.initialValue = classItem.getInitialValue();
        this.invariant = classItem.isInvariant();
        this.description = classItem.getDescription();
        this.childClassDefinitionList = new ArrayList<>(classItem.getChildClassDefinitionList());
    }
}
