/**
 * Project Name : generator-commons<br>
 * File Name : SelectionNode.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/07/25<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.common.dto.contentgenerator;

import java.util.HashMap;
import java.util.Map;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 * コンテンツにおける選択ノードの情報を管理するデータクラスです。
 * <p>
 * このクラスはFluentインターフェースの概念を応用し設計されています。<br>
 * そのため、以下のようなメソッドチェーンでの操作が可能です。
 *
 * <pre>
 * 使用例:
 * <code>
 * SelectionNode selectionNode = SelectionNode.of(conditionId)
 *                                            .put(key1, value1)
 *                                            .put(key2, value2);
 * </code>
 * </pre>
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@Getter
@ToString
@EqualsAndHashCode
public final class SelectionNode {

    /**
     * 条件ID
     */
    private String conditionId;

    /**
     * 選択情報
     */
    private Map<String, String> selection = Map.of();

    /**
     * デフォルトコンストラクタ
     */
    @SuppressWarnings("unused")
    private SelectionNode() {
    }

    /**
     * コンストラクタ
     *
     * @param conditionId 条件ID
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private SelectionNode(@NonNull String conditionId) {
        this.conditionId = conditionId;
    }

    /**
     * コピーコンストラクタ
     *
     * @param selectionNode 選択ノード
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public SelectionNode(@NonNull SelectionNode selectionNode) {
        this.conditionId = selectionNode.getConditionId();
        this.selection = new HashMap<>(selectionNode.getSelection());
    }

    /**
     * {@link SelectionNode} クラスの新しいインスタンスを生成し返却します。
     *
     * @param conditionId 条件ID
     * @return {@link SelectionNode} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static SelectionNode of(@NonNull String conditionId) {
        return new SelectionNode(conditionId);
    }

    /**
     * 引数として渡された {@code selectionNode} オブジェクトの情報をコピーした{@link SelectionNode}
     * クラスの新しいインスタンスを生成し返却します。
     *
     * @param selectionNode 選択ノード
     * @return {@link SelectionNode} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static SelectionNode of(@NonNull SelectionNode selectionNode) {
        return new SelectionNode(selectionNode);
    }

    /**
     * 引数として指定された {@code key} と {@code value} を選択情報に追加します。
     * <p>
     * この {@link SelectionNode#put(String, String)}
     * メソッドは自分自身のインスタンスを返却するため以下のようなメソッドチェーンでの操作が可能です。
     *
     * <pre>
     * <code>
     * SelectionNode selectionNode = SelectionNode.of(conditionId)
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
    public SelectionNode put(@NonNull String key, @NonNull String value) {
        selection.put(key, value);
        return this;
    }
}