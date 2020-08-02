/**
 * Project Name : generator-commons<br>
 * File Name : ContentSelectionNode.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/07/25<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.common.vo.content;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 * コンテンツの選択ノードを管理するデータクラスです。
 * <p>
 * このクラスはFluentインターフェースの概念を応用し設計されています。<br>
 * そのため、以下のようなメソッドチェーンでの操作が可能です。
 *
 * <pre>
 * 使用例:
 * <code>
 * ContentSelectionNode contentSelectionNode = ContentSelectionNode.of(conditionId)
 *                                            .put(key1, value1)
 *                                            .put(key2, value2);
 * </code>
 * </pre>
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@ToString
@EqualsAndHashCode
public final class ContentSelectionNode implements Serializable {

    /**
     * シリアルバージョンUID
     */
    private static final long serialVersionUID = 3246450914037753704L;

    /**
     * 条件ID
     */
    @Getter
    private String conditionId;

    /**
     * 選択情報
     */
    @Getter
    private Map<String, String> selection;

    /**
     * デフォルトコンストラクタ
     */
    @SuppressWarnings("unused")
    private ContentSelectionNode() {
    }

    /**
     * コンストラクタ
     *
     * @param conditionId 条件ID
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private ContentSelectionNode(@NonNull String conditionId) {
        this.conditionId = conditionId;
        this.selection = Map.of();
    }

    /**
     * コピーコンストラクタ
     *
     * @param contentSelectionNode 選択ノード
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public ContentSelectionNode(@NonNull ContentSelectionNode contentSelectionNode) {
        this.conditionId = contentSelectionNode.getConditionId();
        this.selection = new HashMap<>(contentSelectionNode.getSelection());
    }

    /**
     * {@link ContentSelectionNode} クラスの新しいインスタンスを生成し返却します。
     *
     * @param conditionId 条件ID
     * @return {@link ContentSelectionNode} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static ContentSelectionNode of(@NonNull String conditionId) {
        return new ContentSelectionNode(conditionId);
    }

    /**
     * 引数として渡された {@code contentSelectionNode}
     * オブジェクトの情報をコピーした{@link ContentSelectionNode} クラスの新しいインスタンスを生成し返却します。
     *
     * @param contentSelectionNode 選択ノード
     * @return {@link ContentSelectionNode} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static ContentSelectionNode of(@NonNull ContentSelectionNode contentSelectionNode) {
        return new ContentSelectionNode(contentSelectionNode);
    }

    /**
     * 引数として指定された {@code key} と {@code value} を選択情報に追加します。
     * <p>
     * この {@link ContentSelectionNode#put(String, String)}
     * メソッドは自分自身のインスタンスを返却するため以下のようなメソッドチェーンでの操作が可能です。
     *
     * <pre>
     * <code>
     * ContentSelectionNode contentSelectionNode = ContentSelectionNode.of(conditionId)
     *                                           .put(key1, value1)
     *                                           .put(key2, value2);
     * </code>
     * </pre>
     *
     * @param key   選択情報のキー
     * @param value 選択情報の値
     * @return 自分自身のインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public ContentSelectionNode put(@NonNull String key, @NonNull String value) {
        this.selection.put(key, value);
        return this;
    }
}