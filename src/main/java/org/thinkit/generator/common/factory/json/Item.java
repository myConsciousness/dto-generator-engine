/**
 * Project Name : generator-commons<br>
 * File Name : Item.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/08/02<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.common.factory.json;

import org.thinkit.generator.common.factory.Component;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 * JSONノードの項目を抽象化したクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 *
 * @see Component
 * @see #createResource()
 */
@ToString
@EqualsAndHashCode
public abstract class Item implements Component {

    /**
     * キー
     */
    @Getter(AccessLevel.PROTECTED)
    private String key;

    /**
     * 値
     */
    @Getter(AccessLevel.PROTECTED)
    private String value;

    /**
     * デフォルトコンストラクタ
     */
    @SuppressWarnings("unused")
    private Item() {
    }

    /**
     * コンストラクタ
     *
     * @param key   キー
     * @param value 値
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    protected Item(@NonNull String key, @NonNull String value) {
        this.key = key;
        this.value = value;
    }
}