/**
 * Project Name : generator-commons<br>
 * File Name : ContentItem.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/08/02<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.common.factory.content;

import org.thinkit.generator.common.factory.json.Item;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

/**
 * コンテンツの項目を生成する処理を定義したクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 *
 * @see Component
 * @see #createResource()
 */
@ToString
@EqualsAndHashCode(callSuper = false)
final class ContentItem extends Item {

    /**
     * コンストラクタ
     *
     * @param key   キー
     * @param value 値
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public ContentItem(@NonNull String key, @NonNull String value) {
        super(key, value);
    }

    @Override
    public String createResource() {
        return String.format("\"%s\" : \"%s\"", super.getKey(), super.getValue());
    }
}