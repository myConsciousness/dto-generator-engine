/**
 * Project Name : generator-commons<br>
 * File Name : SelectionNodeGroup.java<br>
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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.thinkit.common.util.iterator.FluentIterator;
import org.thinkit.common.util.iterator.IterableNode;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 * コンテンツの選択ノード群を管理するデータクラスです。
 * <p>
 * このクラスはFluentインターフェースの概念を応用し設計されています。<br>
 * そのため、以下のようなメソッドチェーンでの操作が可能です。
 *
 * <pre>
 * 使用例:
 * <code>
 * SelectionNodeGroup selectionNodeGroup = SelectionNodeGroup.of()
 *                                               .add(selectionNode1)
 *                                               .add(selectionNode2);
 * </code>
 * </pre>
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@ToString
@EqualsAndHashCode
public final class SelectionNodeGroup implements Iterable<SelectionNode>, IterableNode<SelectionNode>, Serializable {

    /**
     * シリアルバージョンUID
     */
    private static final long serialVersionUID = -8802715416310295230L;

    /**
     * 選択ノード群
     */
    @Getter
    private List<SelectionNode> selectionNodes;

    /**
     * 選択ノード群のサイズ
     */
    private int size;

    /**
     * デフォルトコンストラクタ
     */
    private SelectionNodeGroup() {
        this.selectionNodes = List.of();
        this.size = 0;
    }

    /**
     * コピーコンストラクタ
     *
     * @param selectionNodes 選択ノード群
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private SelectionNodeGroup(@NonNull SelectionNodeGroup selectionNodeGroup) {
        this.selectionNodes = new ArrayList<>(selectionNodeGroup.getSelectionNodes());
        this.size = selectionNodes.size();
    }

    /**
     * {@link SelectionNodeGroup} クラスの新しいインスタンスを生成し返却します。
     *
     * @return {@link SelectionNodeGroup} クラスの新しいインスタンス
     */
    public static SelectionNodeGroup of() {
        return new SelectionNodeGroup();
    }

    /**
     * 引数として指定された {@code selectionNodeGroup} オブジェクトの情報をコピーした新しい
     * {@link SelectionNodeGroup} クラスのインスタンスを生成し返却します。
     */
    public static SelectionNodeGroup of(@NonNull SelectionNodeGroup selectionNodeGroup) {
        return new SelectionNodeGroup(selectionNodeGroup);
    }

    /**
     * 引数として渡された {@code selectionNode} を選択ノードリストへ追加します。
     * <p>
     * この {@link SelectionNodeGroup#add(SelectionNode)}
     * メソッドは自分自身のインスタンスを返却するため以下のようなメソッドチェーンでの操作が可能です。
     *
     * <pre>
     * <code>
     * SelectionNodeGroup selectionNodeGroup = SelectionNodeGroup.of()
     *                                               .add(selectionNode1)
     *                                               .add(selectionNode2);
     * </code>
     * </pre>
     *
     * @param selectionNode 選択ノード
     * @return 自分自身のインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public SelectionNodeGroup add(@NonNull SelectionNode selectionNode) {
        selectionNodes.add(selectionNode);
        this.size++;

        return this;
    }

    @Override
    public List<SelectionNode> nodes() {
        return this.selectionNodes;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public Iterator<SelectionNode> iterator() {
        return FluentIterator.of(this);
    }
}