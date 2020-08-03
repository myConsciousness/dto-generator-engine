/**
 * Project Name : generator-commons<br>
 * File Name : GroupKey.java<br>
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
 * コンテンツのグループキーを管理するカタログです。
 * <p>
 * 以下の静的メソッドを使用することで各要素の文字列表現を取得することができます。<br>
 * {@link #creator()} <br>
 * {@link #version()} <br>
 * {@link #meta()} <br>
 * {@link #selectionNodes()} <br>
 * {@link #conditionNodes()} <br>
 * {@link #node()} <br>
 * {@link #conditions()}
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@RequiredArgsConstructor
public enum GroupKey implements Catalog<GroupKey> {

    /**
     * 作成者
     */
    CREATOR(0, "creator"),

    /**
     * バージョン
     */
    VERSION(1, "version"),

    /**
     * メタ
     */
    META(2, "meta"),

    /**
     * 選択ノード群
     */
    SELECTION_NODES(3, "selectionNodes"),

    /**
     * 条件ノード群
     */
    CONDITION_NODES(4, "conditionNodes"),

    /**
     * ノード
     */
    NODE(5, "node"),

    /**
     * 条件群
     */
    CONDITIONS(6, "conditions");

    /**
     * コード値
     */
    @Getter
    private final int code;

    /**
     * キー
     */
    @Getter
    private final String key;

    /**
     * {@link #CREATOR} 要素の文字列表現を返却します。
     *
     * @return {@link #CREATOR} 要素の文字列表現
     * @see #CREATOR
     */
    public static String creator() {
        return CREATOR.getKey();
    }

    /**
     * {@link #VERSION} 要素の文字列表現を返却します。
     *
     * @return {@link #VERSION} 要素の文字列表現
     * @see #VERSION
     */
    public static String version() {
        return VERSION.getKey();
    }

    /**
     * {@link #META} 要素の文字列表現を返却します。
     *
     * @return {@link #META} 要素の文字列表現
     * @see #META
     */
    public static String meta() {
        return META.getKey();
    }

    /**
     * {@link #SELECTION_NODES} 要素の文字列表現を返却します。
     *
     * @return {@link #SELECTION_NODES} 要素の文字列表現
     * @see #SELECTION_NODES
     */
    public static String selectionNodes() {
        return SELECTION_NODES.getKey();
    }

    /**
     * {@link #CONDITION_NODES} 要素の文字列表現を返却します。
     *
     * @return {@link #CONDITION_NODES} 要素の文字列表現
     * @see #CONDITION_NODES
     */
    public static String conditionNodes() {
        return CONDITION_NODES.getKey();
    }

    /**
     * {@link #NODE} 要素の文字列表現を返却します。
     *
     * @return {@link #NODE} 要素の文字列表現
     * @see #NODE
     */
    public static String node() {
        return NODE.getKey();
    }

    /**
     * {@link #CONDITIONS} 要素の文字列表現を返却します。
     *
     * @return {@link #CONDITIONS} 要素の文字列表現
     * @see #CONDITIONS
     */
    public static String conditions() {
        return CONDITIONS.getKey();
    }
}