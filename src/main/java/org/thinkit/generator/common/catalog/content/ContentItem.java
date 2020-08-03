/**
 * Project Name : generator-commons<br>
 * File Name : ContentItem.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/08/03<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.common.catalog.content;

import org.thinkit.common.catalog.Catalog;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * コンテンツ項目を管理するカタログです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@RequiredArgsConstructor
public enum ContentItem implements Catalog<ContentItem> {

    /**
     * 作成者
     */
    CREATOR(0),

    /**
     * バージョン
     */
    VERSION(1),

    /**
     * メタ
     */
    META(2);

    /**
     * コード値
     */
    @Getter
    private final int code;
}