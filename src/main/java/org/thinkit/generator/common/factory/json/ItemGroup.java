/**
 * Project Name : generator-commons<br>
 * File Name : ItemGroup.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/08/02<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.common.factory.json;

import java.util.ArrayList;
import java.util.List;

import org.thinkit.generator.common.factory.Component;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 * JSONの項目グループを抽象化したクラスです。
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
public abstract class ItemGroup implements Component {

    /**
     * 項目グループ
     */
    @Getter(AccessLevel.PROTECTED)
    private List<Item> itemGroup;

    /**
     * デフォルトコンストラクタ
     */
    protected ItemGroup() {
        this.itemGroup = new ArrayList<>(0);
    }

    /**
     * 引数として指定された {@link item} オブジェクトを項目グループへ追加します。
     *
     * @param item 項目
     * @return 自分自身のインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public ItemGroup add(@NonNull Item item) {
        this.itemGroup.add(item);
        return this;
    }

    /**
     * 項目グループのサイズが空か判定します。
     *
     * @return 項目グループのサイズが空の場合は {@code true}、それ以外は {@code false}
     */
    public boolean isEmpty() {
        return this.itemGroup.size() <= 0;
    }
}