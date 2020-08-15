/*
 * Copyright 2020 Kato Shinya.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package org.thinkit.generator.common.vo.dto;

import java.io.Serializable;

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
public final class DtoField implements Serializable {

    /**
     * シリアルバージョンUID
     */
    private static final long serialVersionUID = 2082213692978784937L;

    /**
     * 変数名
     */
    private String variableName;

    /**
     * データ型
     */
    private String dataType;

    /**
     * 初期値
     */
    private String initialValue;

    /**
     * 不変
     */
    private boolean invariant;

    /**
     * 説明
     */
    private String description;

    /**
     * 子DTO定義グループ
     */
    @Setter
    private DtoDefinitionGroup childDtoDefinitionGroup;

    /**
     * デフォルトコンストラクタ
     */
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
     *
     * @exception NullPointerException 引数として {@code null} が指定された場合
     */
    private DtoField(@NonNull String variableName, @NonNull String dataType, @NonNull String initialValue,
            boolean invariant, @NonNull String description) {
        this.variableName = variableName;
        this.dataType = dataType;
        this.initialValue = initialValue;
        this.invariant = invariant;
        this.description = description;
        this.childDtoDefinitionGroup = DtoDefinitionGroup.of();
    }

    /**
     * コピーコンストラクタ
     *
     * @param dtoField DTOフィールド
     *
     * @exception NullPointerException 引数として {@code null} が指定された場合
     */
    private DtoField(@NonNull DtoField dtoField) {
        this.variableName = dtoField.getVariableName();
        this.dataType = dtoField.getDataType();
        this.initialValue = dtoField.getInitialValue();
        this.invariant = dtoField.isInvariant();
        this.description = dtoField.getDescription();
        this.childDtoDefinitionGroup = DtoDefinitionGroup.of(dtoField.getChildDtoDefinitionGroup());
    }

    /**
     * 引数として指定された情報を基に {@link DtoField} クラスの新しいインスタンスを生成し返却します。
     *
     * @param variableName 変数名
     * @param dataType     データ型
     * @param initialValue 初期値
     * @param invariant    不変
     * @param description  説明
     * @return {@link DtoField} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が指定された場合
     */
    public static DtoField of(@NonNull String variableName, @NonNull String dataType, @NonNull String initialValue,
            boolean invariant, @NonNull String description) {
        return new DtoField(variableName, dataType, initialValue, invariant, description);
    }

    /**
     * 引数として指定された {@code dtoField} オブジェクトの情報を基に {@link DtoField}
     * クラスの新しいインスタンスを生成し返却します。
     *
     * @param dtoField DTOフィールド
     * @return {@link DtoField} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が指定された場合
     */
    public static DtoField of(@NonNull DtoField dtoField) {
        return new DtoField(dtoField);
    }
}
