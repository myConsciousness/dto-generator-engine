/**
 * Project Name : generator-commons<br>
 * File Name : ContentSelectionNodeGroup.java<br>
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
import java.util.stream.Stream;

import org.thinkit.common.util.iterator.FluentIterator;
import org.thinkit.common.util.iterator.IterableNode;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 * コンテンツの選択ノードオブジェクトグループを管理するデータクラスです。
 * <p>
 * このクラスはFluentインターフェースの概念を応用し設計されています。<br>
 * そのため、以下のようなメソッドチェーンでの操作が可能です。
 *
 * <pre>
 * 使用例:
 * <code>
 * ContentSelectionNodeGroup contentSelectionNodeGroup = ContentSelectionNodeGroup.of()
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
public final class ContentSelectionNodeGroup
        implements Iterable<ContentSelectionNode>, IterableNode<ContentSelectionNode>, Serializable {

    /**
     * シリアルバージョンUID
     */
    private static final long serialVersionUID = -8802715416310295230L;

    /**
     * 選択ノードオブジェクトグループ
     */
    @Getter
    private List<ContentSelectionNode> contentSelectionNodeGroup;

    /**
     * 選択ノードオブジェクトグループのサイズ
     */
    private int size;

    /**
     * デフォルトコンストラクタ
     */
    private ContentSelectionNodeGroup() {
        this.contentSelectionNodeGroup = new ArrayList<>(0);
        this.size = 0;
    }

    /**
     * コピーコンストラクタ
     *
     * @param contentSelectionNodeGroup 選択ノードオブジェクトグループ
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private ContentSelectionNodeGroup(@NonNull ContentSelectionNodeGroup contentSelectionNodeGroup) {
        this.contentSelectionNodeGroup = new ArrayList<>(contentSelectionNodeGroup.getContentSelectionNodeGroup());
        this.size = contentSelectionNodeGroup.size();
    }

    /**
     * {@link ContentSelectionNodeGroup} クラスの新しいインスタンスを生成し返却します。
     *
     * @return {@link ContentSelectionNodeGroup} クラスの新しいインスタンス
     */
    public static ContentSelectionNodeGroup of() {
        return new ContentSelectionNodeGroup();
    }

    /**
     * 引数として指定された {@code contentSelectionNodeGroup} オブジェクトの情報をコピーした新しい
     * {@link ContentSelectionNodeGroup} クラスのインスタンスを生成し返却します。
     *
     * @param contentSelectionNodeGroup 選択ノードオブジェクトグループ
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static ContentSelectionNodeGroup of(@NonNull ContentSelectionNodeGroup contentSelectionNodeGroup) {
        return new ContentSelectionNodeGroup(contentSelectionNodeGroup);
    }

    /**
     * 引数として渡された {@code contentSelectionNode} を選択ノードオブジェクトリストへ追加します。
     * <p>
     * この {@link ContentSelectionNodeGroup#add(ContentSelectionNode)}
     * メソッドは自分自身のインスタンスを返却するため以下のようなメソッドチェーンでの操作が可能です。
     *
     * <pre>
     * <code>
     * ContentSelectionNodeGroup contentSelectionNodeGroup = ContentSelectionNodeGroup.of()
     *                                               .add(selectionNode1)
     *                                               .add(selectionNode2);
     * </code>
     * </pre>
     *
     * @param contentSelectionNode 選択ノードオブジェクト
     * @return 自分自身のインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public ContentSelectionNodeGroup add(@NonNull ContentSelectionNode contentSelectionNode) {
        this.contentSelectionNodeGroup.add(contentSelectionNode);
        this.size++;

        return this;
    }

    /**
     * 指定された {@code index} の数値に紐づく {@link ContentSelectionNode} オブジェクトを返却します。
     * 存在しないインデックスを指定した場合は実行時に必ず {@link ArrayIndexOutOfBoundsException} が発生するため、
     * {@link #get(int) メソッドの実行前にが必ず有効なアクセス範囲を確認してください。
     *
     * @param index インデックス
     * @return 指定された {@code index} の数値に紐づく {@link ContentSelectionNode} オブジェクト
     *
     * @exception ArrayIndexOutOfBoundsException 引数として指定された {@code index}
     *                                           に紐づく情報が存在しない場合
     */
    public ContentSelectionNode get(int index) {
        return this.contentSelectionNodeGroup.get(index);
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
     * {@link SelectionNode} クラスを総称型として持つストリームを返却します。
     *
     * @return {@link SelectionNode} クラスを総称型として持つストリーム
     */
    public Stream<ContentSelectionNode> stream() {
        return this.contentSelectionNodeGroup.stream();
    }

    @Override
    public List<ContentSelectionNode> nodes() {
        return this.contentSelectionNodeGroup;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public Iterator<ContentSelectionNode> iterator() {
        return FluentIterator.of(this);
    }
}