/**
 * Project Name : generator-commons<br>
 * File Name : ConditionNodeGroup.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/07/26<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.common.vo.content;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import org.thinkit.common.util.iterator.FluentIterator;
import org.thinkit.common.util.iterator.IterableNode;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 * コンテンツの条件ノードオブジェクトグループを管理するデータクラスです。
 * <p>
 * このクラスはFluentインターフェースの概念を応用し設計されています。<br>
 * そのため、以下のようなメソッドチェーンでの操作が可能です。
 *
 * <pre>
 * <code>
 * ConditionNodeGroup conditionNodeGroup = ConditionNodeGroup.of()
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
public final class ConditionNodeGroup implements Iterable<ConditionNode>, IterableNode<ConditionNode>, Serializable {

    /**
     * シリアルバージョンUID
     */
    private static final long serialVersionUID = -1845483983937698317L;

    /**
     * 条件ノードオブジェクトグループ
     */
    @Getter
    private List<ConditionNode> conditionNodeGroup;

    /**
     * 条件ノードオブジェクトグループのサイズ
     */
    private int size;

    /**
     * デフォルトコンストラクタ
     */
    private ConditionNodeGroup() {
        this.conditionNodeGroup = new ArrayList<>(0);
        this.size = 0;
    }

    /**
     * コピーコンストラクタ
     *
     * @param conditionNodeGroup 条件ノードオブジェクトグループ
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private ConditionNodeGroup(@NonNull ConditionNodeGroup conditionNodeGroup) {
        this.conditionNodeGroup = new ArrayList<>(conditionNodeGroup.getConditionNodeGroup());
        this.size = conditionNodeGroup.size();
    }

    /**
     * {@link ConditionNodeGroup} クラスの新しいインスタンスを生成し返却します。
     *
     * @return {@link ConditionNodeGroup} クラスの新しいインスタンス
     */
    public static ConditionNodeGroup of() {
        return new ConditionNodeGroup();
    }

    /**
     * 引数として指定された {@code conditionNodeGroup} オブジェクトの情報をコピーした新しい
     * {@link ConditionNodeGroup} クラスのインスタンスを生成し返却します。
     *
     * @param conditionNodeGroup 条件ノードオブジェクトグループ
     * @return {@link ConditionNodeGroup} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が指定された場合
     */
    public static ConditionNodeGroup of(@NonNull ConditionNodeGroup conditionNodeGroup) {
        return new ConditionNodeGroup(conditionNodeGroup);
    }

    /**
     * 引数として渡された {@code conditionNode} を条件リストへ追加します。
     * <p>
     * この {@link ConditionNodeGroup#add(ConditionNode)}
     * メソッドは自分自身のインスタンスを返却するため以下のようなメソッドチェーンでの操作が可能です。
     *
     * <pre>
     * <code>
     * ConditionNodeGroup conditionNodeGroup = ConditionNodeGroup.of()
     *                                              .add(conditionNode1)
     *                                              .add(conditionNode2);
     * </code>
     * </pre>
     *
     * @param conditionNode 条件ノードオブジェクト
     * @return 自分自身のインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public ConditionNodeGroup add(@NonNull ConditionNode conditionNode) {
        this.conditionNodeGroup.add(conditionNode);
        size++;

        return this;
    }

    /**
     * オブジェクトに含まれる情報が空か判定します。
     *
     * @return オブジェクトに含まれる情報がからの場合は {@code true} 、それ以外は {@code false}
     */
    public boolean isEmpty() {
        return this.size <= 0;
    }

    /**
     * {@link ConditionNode} クラスを総称型として持つストリームを返却します。
     *
     * @return {@link ConditionNode} クラスを総称型として持つストリーム
     */
    public Stream<ConditionNode> stream() {
        return this.conditionNodeGroup.stream();
    }

    @Override
    public List<ConditionNode> nodes() {
        return this.conditionNodeGroup;
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