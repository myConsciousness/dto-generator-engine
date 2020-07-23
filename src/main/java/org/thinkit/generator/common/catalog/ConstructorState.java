/**
 * Project Name : generator-commons<br>
 * File Name : ConstructorState.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/06/08<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.common.catalog;

import org.thinkit.common.catalog.Catalog;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * コンストラクタ状態を管理するカタログです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@RequiredArgsConstructor
public enum ConstructorState implements Catalog<ConstructorState> {

    /**
     * デフォルトコンストラクタ
     */
    DEFAULT(0),

    /**
     * 必須引数有りコンストラクタ
     */
    REQUIRED(1),

    /**
     * コピーコンストラクタ
     */
    COPYING(2);

    /**
     * コード値
     */
    @Getter
    private final int code;
}