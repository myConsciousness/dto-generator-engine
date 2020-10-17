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

package org.thinkit.generator.common.catalog.dto;

import org.thinkit.api.catalog.Catalog;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * DTOの項目を管理するカタログです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@RequiredArgsConstructor
public enum DtoItem implements Catalog<DtoItem> {

    /**
     * 物理名
     */
    PHYSICAL_NAME(0),

    /**
     * 論理名
     */
    LOGICAL_NAME(1),

    /**
     * 補足
     */
    DESCRIPTION(2),

    /**
     * 作成者
     */
    CREATOR(3),

    /**
     * 作成日付
     */
    CREATION_TIME(4),

    /**
     * 更新日付
     */
    UPDTATE_TIME(5),

    /**
     * 論理削除
     */
    LOGICAL_DELETE(6),

    /**
     * 階層
     */
    LAYER(7),

    /**
     * 変数名
     */
    VARIABLE_NAME(8),

    /**
     * データ型
     */
    DATA_TYPE(9),

    /**
     * 不変
     */
    INVARIANT(10),

    /**
     * 初期値
     */
    INITIAL_VALUE(11),

    /**
     * パッケージ名
     */
    PACKAGE_NAME(12),

    /**
     * プロジェクト名
     */
    PROJECT_NAME(13),

    /**
     * バージョン
     */
    VERSION(14);

    /**
     * コード値
     */
    @Getter
    private final int code;
}
