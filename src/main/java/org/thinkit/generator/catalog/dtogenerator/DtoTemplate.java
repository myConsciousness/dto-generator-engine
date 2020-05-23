/**
 * Project Name : Business Tool<br>
 * File Name : DtoTemplate.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/05/20<br>
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
 * DTOの雛形区分を管理するカテゴリです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@Getter
@RequiredArgsConstructor
public enum DtoTemplate implements Catalog<DtoTemplate> {

    /**
     * フィールドのJavadoc
     */
    FIELD_JAVADOC(0),

    /**
     * フィールド定義
     */
    FIELD_DEFINITION(1),

    /**
     * nullを許容しないフィールド定義
     */
    NON_NULL_FIELD_DEFINITION(2),

    /**
     * コンストラクタの処理
     */
    CONSTRUCTOR_PROCESS(3),

    /**
     * コピーコンストラクタの処理
     */
    COPYING_CONSTRUCTOR_PROCESS(4),

    /**
     * final引数定義
     */
    FINAL_PARAMETER(5),

    /**
     * コンストラクタのParamアノテーション定義
     */
    CONSTRUCTOR_PARAM_ANNOTATION(6);

    /**
     * コード値
     */
    private final int code;
}
