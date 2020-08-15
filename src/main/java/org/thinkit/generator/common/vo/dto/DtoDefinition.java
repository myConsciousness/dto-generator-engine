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
