/**
 * Project Name : generator-commons<br>
 * File Name : ConditionNodes.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/07/26<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.common.dto.contentgenerator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.thinkit.common.iterator.FluentIterator;
import org.thinkit.common.iterator.IterableNode;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 * コンテンツの条件ノード群を管理するデータクラスです。
 * <p>
 * このクラスはFluentインターフェースの概念を応用し設計されています。<br>
 * そのため、以下のようなメソッドチェーンでの操作が可能です。
 *
 * <pre>
 * <code>
 * ConditionNodes conditionNodes = ConditionNodes.of()
 *                                              .add(conditionNode1)
 *                                              .add(conditionNode2);
 * </code>
 * </pre>
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@ToString
@EqualsAndHashCode
public final class ConditionNodes implements Iterable<ConditionNode>, IterableNode<ConditionNode> {

    /**
     * 条件ノード群
     */
    @Getter
    private List<ConditionNode> conditionNodes;

    /**
     * 条件ノード群のサイズ
     */
    private int size;

    /**
     * デフォルトコンストラクタ
     */
    private ConditionNodes() {
        this.conditionNodes = List.of();
        this.size = 0;
    }

    /**
     * コピーコンストラクタ
     *
     * @param conditionNodes 条件ノード群
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private ConditionNodes(@NonNull ConditionNodes conditionNodes) {
        this.conditionNodes = new ArrayList<>(conditionNodes.getConditionNodes());
        this.size = conditionNodes.size();
    }

    /**
     * {@link ConditionNodes} クラスの新しいインスタンスを生成し返却します。
     *
     * @return {@link ConditionNodes} クラスの新しいインスタンス
     */
    public static ConditionNodes of() {
        return new ConditionNodes();
    }

    /**
     * 引数として指定された {@code conditionNodes} オブジェクトの情報をコピーした新しい {@link ConditionNodes}
     * クラスのインスタンスを生成し返却します。
     *
     * @param conditionNodes 条件ノード群
     * @return {@link ConditionNodes} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が指定された場合
     */
    public static ConditionNodes of(@NonNull ConditionNodes conditionNodes) {
        return new ConditionNodes(conditionNodes);
    }

    /**
     * 引数として渡された {@code conditionNode} を条件リストへ追加します。
     * <p>
     * この {@link ConditionNodes#add(ConditionNode)}
     * メソッドは自分自身のインスタンスを返却するため以下のようなメソッドチェーンでの操作が可能です。
     *
     * <pre>
     * <code>
     * ConditionNodes conditionNodes = ConditionNodes.of()
     *                                              .add(conditionNode1)
     *                                              .add(conditionNode2);
     * </code>
     * </pre>
     *
     * @param conditionNode 条件ノード
     * @return 自分自身のインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public ConditionNodes add(@NonNull ConditionNode conditionNode) {
        this.conditionNodes.add(conditionNode);
        size++;

        return this;
    }

    /**
     * 条件ノード群のサイズが空か判定します。
     *
     * @return 条件ノード群のサイズが {@code 0} 以下の場合は {@code true} 、 それ以外は{@code false}
     */
    public boolean isEmpty() {
        return this.size <= 0;
    }

    @Override
    public List<ConditionNode> nodes() {
        return this.conditionNodes;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public Iterator<ConditionNode> iterator() {
        return FluentIterator.of(this);
    }
}