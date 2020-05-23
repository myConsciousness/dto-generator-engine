/**
 * Project Name : Business Tool<br>
 * File Name : DtoTemplateReplacementSequence.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/05/18<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */
package org.thinkit.generator.catalog.dtogenerator;

import org.thinkit.common.catalog.Catalog;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * DTOのテンプレート置換文字列区分を管理するカタログです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@Getter
@RequiredArgsConstructor
public enum DtoTemplateReplacementSequence implements Catalog<DtoTemplateReplacementSequence> {

    /**
     * プロジェクト名
     */
    PROJECT_NAME(0),

    /**
     * クラス名
     */
    CLASS_NAME(1),

    /**
     * 作成日付
     */
    CREATION_DATE(2),

    /**
     * 更新日付
     */
    UPDATE_DATE(3),

    /**
     * 著作権(年)
     */
    COPYRIGHT_YEAR(4),

    /**
     * 作成者
     */
    CREATOR(5),

    /**
     * パッケージ名
     */
    PACKAGE_NAME(6),

    /**
     * クラスの説明
     */
    CLASS_DESCRIPTION(7),

    /**
     * フィールド群
     */
    FIELDS(8),

    /**
     * コンストラクターの引数
     */
    CONSTRUCTOR_PARAMETERS(9),

    /**
     * コピーコンストラクタの引数
     */
    COPYING_CONSTRUCTOR_PARAMETER(10),

    /**
     * 引数ありコンストラクタの引数アノテーション
     */
    PARAM_ANOTATION_REQUIRED_CONSTRUCTOR(11),

    /**
     * コピーコンストラクタの引数アノテーション
     */
    PARAM_ANOTATION_COPYING_CONSTRUCTOR(12),

    /**
     * コンストラクタで初期化する引数の値
     */
    CONSTRUCTOR_PARAMETER_VALUES(13),

    /**
     * コピーコンストラクタで初期化する引数の値
     */
    COPYING_CONSTRUCTOR_PARAMETER_VALUE(14),

    /**
     * 引数ありコンストラクタの処理
     */
    REQUIRED_CONSTRUCTOR_PROCESS(15),

    /**
     * コピーコンストラクタの処理
     */
    COPYING_CONSTRUCTOR_PROCESS(16),

    /**
     * 変数の説明
     */
    VARIABLE_DESCRIPTION(17),

    /**
     * 初期値
     */
    INITIAL_VALUE(18),

    /**
     * 型
     */
    DATA_TYPE(19),

    /**
     * 変数名
     */
    VARIABLE_NAME(20);

    /**
     * コード値
     */
    private final int code;
}
