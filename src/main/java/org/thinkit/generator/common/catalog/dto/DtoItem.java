/**
 * Project Name : generator-commons<br>
 * File Name : DtoItem.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/04/28<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.common.catalog.dto;

import org.thinkit.common.catalog.Catalog;

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
