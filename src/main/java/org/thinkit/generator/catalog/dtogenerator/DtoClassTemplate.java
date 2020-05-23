/**
 * Project Name : Generator<br>
 * File Name : DtoClassTemplate.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/05/17<br>
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
 * DTOのクラス雛形区分を管理するカタログです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@Getter
@RequiredArgsConstructor
public enum DtoClassTemplate implements Catalog<DtoClassTemplate> {

    /**
     * DTOクラスのボイラープレート
     */
    CLASS_BOILERPLATE(0),

    /**
     * DTOクラスのボイラープレート(必須コンストラクタのみ)
     */
    CLASS_BOILERPLATE_ONLY_REQUIRED_CONSTRUCTOR(1),

    /**
     * DTOクラスのボイラープレート（デフォルトコンストラクタのみ）
     */
    CLASS_BOILERPLATE_ONLY_DEFAULT_CONSTRUCTOR(2);

    /**
     * コード値
     */
    private final int code;
}
