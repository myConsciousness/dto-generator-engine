/**
 * Project Name : generator-commons<br>
 * File Name : ContentConditionGroup.java<br>
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
 * コンテンツの条件グループを管理するデータクラスです。
 * <p>
 * このクラスはFluentインターフェースの概念を応用し設計されています。<br>
 * そのため、以下のようなメソッドチェーンでの操作が可能です。
 *
 * <pre>
 * <code>
 * ContentConditionGroup ContentConditionGroup = ContentConditionGroup.of()
 *                                  .add(condition1)
 *                                  .add(condition2);
 * </code>
 * </pre>
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@ToString
@EqualsAndHashCode
public final class ContentConditionGroup
        implements Iterable<ContentCondition>, IterableNode<ContentCondition>, Serializable {

    /**
     * シリアルバージョンUID
     */
    private static final long serialVersionUID = -2407821983642951195L;

    /**
     * 条件グループ
     */
    @Getter
    private List<ContentCondition> contentConditionGroup;

    /**
     * デフォルトコンストラクタ
     */
    private ContentConditionGroup() {
        this.contentConditionGroup = new ArrayList<>(0);
    }

    /**
     * コピーコンストラクタ
     *
     * @param contentConditionGroup 条件グループ
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private ContentConditionGroup(@NonNull ContentConditionGroup contentConditionGroup) {
        this.contentConditionGroup = new ArrayList<>(contentConditionGroup.getContentConditionGroup());
    }

    /**
     * {@link ContentConditionGroup} クラスの新しいインスタンスを生成し返却します。
     *
     * @return {@link ContentConditionGroup} クラスの新しいインスタンス
     */
    public static ContentConditionGroup of() {
        return new ContentConditionGroup();
    }

    /**
     * 引数として指定された {@code contentConditionGroup} オブジェクトの情報をコピーした新しい
     * {@link ContentConditionGroup} クラスのインスタンスを生成し返却します。
     */
    public static ContentConditionGroup of(@NonNull ContentConditionGroup contentConditionGroup) {
        return new ContentConditionGroup(contentConditionGroup);
    }

    /**
     * 引数として渡された {@code contentCondition} を条件リストへ追加します。
     * <p>
     * この {@link ContentConditionGroup#add(ContentCondition)}
     * メソッドは自分自身のインスタンスを返却するため以下のようなメソッドチェーンでの操作が可能です。
     *
     * <pre>
     * <code>
     * ContentConditionGroup contentConditionGroup = ContentConditionGroup.of()
     *                                  .add(condition1)
     *                                  .add(condition2);
     * </code>
     * </pre>
     *
     * @param condition 条件オブジェクト
     * @return 自分自身のインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public ContentConditionGroup add(@NonNull ContentCondition contentCondition) {
        this.contentConditionGroup.add(contentCondition);
        return this;
    }

    /**
     * 指定された {@code index} の数値に紐づく {@link ContentCondition} オブジェクトを返却します。
     * 存在しないインデックスを指定した場合は実行時に必ず {@link ArrayIndexOutOfBoundsException} が発生するため、
     * {@link #get(int) メソッドの実行前にが必ず有効なアクセス範囲を確認してください。
     *
     * @param index インデックス
     * @return 指定された {@code index} の数値に紐づく {@link ContentCondition} オブジェクト
     *
     * @exception ArrayIndexOutOfBoundsException 引数として指定された {@code index}
     *                                           に紐づく情報が存在しない場合
     */
    public ContentCondition get(int index) {
        return this.contentConditionGroup.get(index);
    }

    /**
     * オブジェクトに含まれる情報が空か判定します。
     *
     * @return オブジェクトに含まれる情報がからの場合は {@code true} 、それ以外は {@code false}
     */
    public boolean isEmpty() {
        return this.contentConditionGroup.isEmpty();
    }

    /**
     * {@link ContentCondition} クラスを総称型として持つストリームを返却します。
     *
     * @return {@link ContentCondition} クラスを総称型として持つストリーム
     */
    public Stream<ContentCondition> stream() {
        return this.contentConditionGroup.stream();
    }

    @Override
    public List<ContentCondition> nodes() {
        return this.contentConditionGroup;
    }

    @Override
    public int size() {
        return this.contentConditionGroup.size();
    }

    @Override
    public Iterator<ContentCondition> iterator() {
        return FluentIterator.of(this);
    }
}