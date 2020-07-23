/**
 * Project Name : generator-commons<br>
 * File Name : GeneratorDivision.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/05/16<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.catalog;

import org.thinkit.common.catalog.Catalog;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 生成器区分を管理するカテゴリです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@Getter
@RequiredArgsConstructor
public enum GeneratorDivision implements Catalog<GeneratorDivision> {

    /**
     * DTO定義
     */
    DTO_DEFINITOON(0);

    /**
     * コード値
     */
    private final int code;
}
