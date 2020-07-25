/**
 * Project Name : generator-commons<br>
 * File Name : SelectionNodes.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/07/25<br>
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

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 * コンテンツにおける選択ノード群の情報を管理するデータクラスです。
 * <p>
 * このクラスはFluentインターフェースの概念を応用し設計されています。<br>
 * そのため、以下のようなメソッドチェーンでの操作が可能です。
 *
 * <pre>
 * 使用例:
 * <code>
 * SelectionNodes selectionNodes = SelectionNodes.of()
 *                                               .add(selectionNode1)
 *                                               .add(selectionNode2);
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
public final class SelectionNodes implements Iterable<SelectionNode>, Iterator<SelectionNode> {

    /**
     * 選択ノード群
     */
    private List<SelectionNode> selectionNodes = List.of();

    /**
     * 選択ノード群のサイズ
     */
    private int size = 0;

    /**
     * イテレート時のカーソルインデックス
     */
    private int cursorIndex = 0;

    /**
     * デフォルトコンストラクタ
     */
    private SelectionNodes() {
    }

    /**
     * コピーコンストラクタ
     *
     * @param selectionNodes 選択ノード群
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private SelectionNodes(@NonNull SelectionNodes selectionNodes) {
        this.selectionNodes = new ArrayList<>(selectionNodes.getSelectionNodes());
    }

    /**
     * {@link SelectionNodes} クラスの新しいインスタンスを生成し返却します。
     *
     * @return {@link SelectionNodes} クラスの新しいインスタンス
     */
    public static SelectionNodes of() {
        return new SelectionNodes();
    }

    /**
     * 引数として指定された {@code selectionNodes} オブジェクトの情報をコピーした新しい {@link SelectionNodes}
     * クラスのインスタンスを生成し返却します。
     */
    public static SelectionNodes of(@NonNull SelectionNodes selectionNodes) {
        return new SelectionNodes(selectionNodes);
    }

    /**
     * {@link SelectionNode} クラスを総称型としてもつ {@link Iterator} オブジェクトを返却します。
     *
     * @return {@link SelectionNode} クラスを総称型としてもつ {@link Iterator} オブジェクト
     */
    @Override
    public Iterator<SelectionNode> iterator() {
        return this;
    }

    /**
     * イテレート処理時に選択ノード群の次の要素を返却します。
     *
     * @return 選択ノード群の次の要素
     */
    @Override
    public SelectionNode next() {
        return this.selectionNodes.get(++this.cursorIndex);
    }

    /**
     * イテレート時に選択ノード群の次の要素が存在するか確認します。
     *
     * @return イテレート時における選択ノード群に次の要素が存在する場合は {@code true} 、それ以外は {@code false}
     */
    @Override
    public boolean hasNext() {
        return this.size > this.cursorIndex;
    }

    /**
     * 引数として渡された {@code selectionNode} を選択ノードリストへ追加します。
     * <p>
     * この {@link SelectionNodes#add(SelectionNode)}
     * メソッドは自分自身のインスタンスを返却するため以下のようなメソッドチェーンでの操作が可能です。
     *
     * <pre>
     * <code>
     * SelectionNodes selectionNodes = SelectionNodes.of()
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
    public SelectionNodes add(@NonNull SelectionNode selectionNode) {
        selectionNodes.add(selectionNode);
        this.size++;

        return this;
    }
}