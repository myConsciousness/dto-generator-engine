/**
 * Project Name : generator-commons<br>
 * File Name : ContentConditionNodeGroup.java<br>
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
 * ContentConditionNodeGroup contentConditionNodeGroup = ContentConditionNodeGroup.of()
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
public final class ContentConditionNodeGroup
        implements Iterable<ContentConditionNode>, IterableNode<ContentConditionNode>, Serializable {

    /**
     * シリアルバージョンUID
     */
    private static final long serialVersionUID = 3060527407116637527L;

    /**
     * 条件ノードオブジェクトグループ
     */
    @Getter
    private List<ContentConditionNode> contentConditionNodeGroup;

    /**
     * デフォルトコンストラクタ
     */
    private ContentConditionNodeGroup() {
        this.contentConditionNodeGroup = new ArrayList<>(0);
    }

    /**
     * コピーコンストラクタ
     *
     * @param contentConditionNodeGroup 条件ノードオブジェクトグループ
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private ContentConditionNodeGroup(@NonNull ContentConditionNodeGroup contentConditionNodeGroup) {
        this.contentConditionNodeGroup = new ArrayList<>(contentConditionNodeGroup.getContentConditionNodeGroup());
    }

    /**
     * {@link ContentConditionNodeGroup} クラスの新しいインスタンスを生成し返却します。
     *
     * @return {@link ContentConditionNodeGroup} クラスの新しいインスタンス
     */
    public static ContentConditionNodeGroup of() {
        return new ContentConditionNodeGroup();
    }

    /**
     * 引数として指定された {@code contentConditionNodeGroup} オブジェクトの情報をコピーした新しい
     * {@link ContentConditionNodeGroup} クラスのインスタンスを生成し返却します。
     *
     * @param contentConditionNodeGroup 条件ノードオブジェクトグループ
     * @return {@link ContentConditionNodeGroup} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が指定された場合
     */
    public static ContentConditionNodeGroup of(@NonNull ContentConditionNodeGroup contentConditionNodeGroup) {
        return new ContentConditionNodeGroup(contentConditionNodeGroup);
    }

    /**
     * 引数として渡された {@code conditionNode} を条件リストへ追加します。
     * <p>
     * この {@link ContentConditionNodeGroup#add(ContentConditionNode)}
     * メソッドは自分自身のインスタンスを返却するため以下のようなメソッドチェーンでの操作が可能です。
     *
     * <pre>
     * <code>
     * ContentConditionNodeGroup contentConditionNodeGroup = ContentConditionNodeGroup.of()
     *                                              .add(conditionNode1)
     *                                              .add(conditionNode2);
     * </code>
     * </pre>
     *
     * @param ContentConditionNode 条件ノードオブジェクト
     * @return 自分自身のインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public ContentConditionNodeGroup add(@NonNull ContentConditionNode contentConditionNode) {
        this.contentConditionNodeGroup.add(contentConditionNode);
        return this;
    }

    /**
     * 指定された {@code index} の数値に紐づく {@link ContentConditionNode} オブジェクトを返却します。
     * 存在しないインデックスを指定した場合は実行時に必ず {@link ArrayIndexOutOfBoundsException} が発生するため、
     * {@link #get(int) メソッドの実行前にが必ず有効なアクセス範囲を確認してください。
     *
     * @param index インデックス
     * @return 指定された {@code index} の数値に紐づく {@link ContentConditionNode} オブジェクト
     *
     * @exception ArrayIndexOutOfBoundsException 引数として指定された {@code index}
     *                                           に紐づく情報が存在しない場合
     */
    public ContentConditionNode get(int index) {
        return this.contentConditionNodeGroup.get(index);
    }

    /**
     * オブジェクトに含まれる情報が空か判定します。
     *
     * @return オブジェクトに含まれる情報がからの場合は {@code true} 、それ以外は {@code false}
     */
    public boolean isEmpty() {
        return this.contentConditionNodeGroup.isEmpty();
    }

    /**
     * {@link ContentConditionNode} クラスを総称型として持つストリームを返却します。
     *
     * @return {@link ContentConditionNode} クラスを総称型として持つストリーム
     */
    public Stream<ContentConditionNode> stream() {
        return this.contentConditionNodeGroup.stream();
    }

    @Override
    public List<ContentConditionNode> nodes() {
        return this.contentConditionNodeGroup;
    }

    @Override
    public int size() {
        return this.contentConditionNodeGroup.size();
    }

    @Override
    public Iterator<ContentConditionNode> iterator() {
        return FluentIterator.of(this);
    }
}