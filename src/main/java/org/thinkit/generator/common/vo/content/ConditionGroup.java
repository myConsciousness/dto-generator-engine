/**
 * Project Name : generator-commons<br>
 * File Name : ConditionGroup.java<br>
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
 * ConditionGroup conditionGroup = ConditionGroup.of()
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
public final class ConditionGroup implements Iterable<Condition>, IterableNode<Condition>, Serializable {

    /**
     * シリアルバージョンUID
     */
    private static final long serialVersionUID = 9059498528635059645L;

    /**
     * 条件グループ
     */
    @Getter
    private List<Condition> conditionGroup;

    /**
     * 条件グループのサイズ
     */
    private int size;

    /**
     * デフォルトコンストラクタ
     */
    private ConditionGroup() {
        this.conditionGroup = new ArrayList<>(0);
        this.size = 0;
    }

    /**
     * コピーコンストラクタ
     *
     * @param conditionGroup 条件グループ
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private ConditionGroup(@NonNull ConditionGroup conditionGroup) {
        this.conditionGroup = new ArrayList<>(conditionGroup.getConditionGroup());
        this.size = conditionGroup.size();
    }

    /**
     * {@link ConditionGroup} クラスの新しいインスタンスを生成し返却します。
     *
     * @return {@link ConditionGroup} クラスの新しいインスタンス
     */
    public static ConditionGroup of() {
        return new ConditionGroup();
    }

    /**
     * 引数として指定された {@code conditionGroup} オブジェクトの情報をコピーした新しい {@link ConditionGroup}
     * クラスのインスタンスを生成し返却します。
     */
    public static ConditionGroup of(@NonNull ConditionGroup conditionGroup) {
        return new ConditionGroup(conditionGroup);
    }

    /**
     * 引数として渡された {@code condition} を条件リストへ追加します。
     * <p>
     * この {@link ConditionGroup#add(Condition)}
     * メソッドは自分自身のインスタンスを返却するため以下のようなメソッドチェーンでの操作が可能です。
     *
     * <pre>
     * <code>
     * ConditionGroup conditionGroup = ConditionGroup.of()
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
    public ConditionGroup add(@NonNull Condition condition) {
        this.conditionGroup.add(condition);
        size++;

        return this;
    }

    /**
     * 指定された {@code index} の数値に紐づく {@link Condition} オブジェクトを返却します。
     * 存在しないインデックスを指定した場合は実行時に必ず {@link ArrayIndexOutOfBoundsException} が発生するため、
     * {@link #get(int) メソッドの実行前にが必ず有効なアクセス範囲を確認してください。
     *
     * @param index インデックス
     * @return 指定された {@code index} の数値に紐づく {@link Condition} オブジェクト
     *
     * @exception ArrayIndexOutOfBoundsException 引数として指定された {@code index}
     *                                           に紐づく情報が存在しない場合
     */
    public Condition get(int index) {
        return this.conditionGroup.get(index);
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
     * {@link Condition} クラスを総称型として持つストリームを返却します。
     *
     * @return {@link Condition} クラスを総称型として持つストリーム
     */
    public Stream<Condition> stream() {
        return this.conditionGroup.stream();
    }

    @Override
    public List<Condition> nodes() {
        return this.conditionGroup;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public Iterator<Condition> iterator() {
        return FluentIterator.of(this);
    }
}