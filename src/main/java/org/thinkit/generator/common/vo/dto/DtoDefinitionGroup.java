/*
 * Copyright 2020 Kato Shinya.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package org.thinkit.generator.common.vo.dto;

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
 * DTO定義グループを管理するデータクラスです。
 * <p>
 * このクラスはFluentインターフェースの概念を応用し設計されています。<br>
 * そのため、以下のようなメソッドチェーンでの操作が可能です。
 *
 * <pre>
 * <code>
 * DtoDefinitionGroup dtoDefinitionGroup = DtoDefinitionGroup.of()
 *                                  .add(dtoDefinition1)
 *                                  .add(dtoDefinition2);
 * </code>
 * </pre>
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@ToString
@EqualsAndHashCode
public final class DtoDefinitionGroup implements Iterable<DtoDefinition>, IterableNode<DtoDefinition>, Serializable {

    /**
     * シリアルバージョンUID
     */
    private static final long serialVersionUID = -7569802853448468850L;

    /**
     * DTO定義グループ
     */
    @Getter
    private List<DtoDefinition> dtoDefinitionGroup;

    /**
     * デフォルトコンストラクタ
     */
    private DtoDefinitionGroup() {
        this.dtoDefinitionGroup = new ArrayList<>(0);
    }

    /**
     * コピーコンストラクタ
     *
     * @param dtoDefinitionGroup DTO定義グループ
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private DtoDefinitionGroup(@NonNull DtoDefinitionGroup dtoDefinitionGroup) {
        this.dtoDefinitionGroup = new ArrayList<>(dtoDefinitionGroup.getDtoDefinitionGroup());
    }

    /**
     * {@link DtoDefinitionGroup} クラスの新しいインスタンスを生成し返却します。
     *
     * @return {@link DtoDefinitionGroup} クラスの新しいインスタンス
     */
    public static DtoDefinitionGroup of() {
        return new DtoDefinitionGroup();
    }

    /**
     * 引数として指定された {@code dtoDefinitionGroup} オブジェクトの情報をコピーした新しい
     * {@link DtoDefinitionGroup} クラスのインスタンスを生成し返却します。
     */
    public static DtoDefinitionGroup of(@NonNull DtoDefinitionGroup dtoDefinitionGroup) {
        return new DtoDefinitionGroup(dtoDefinitionGroup);
    }

    /**
     * 引数として渡された {@code dtoDefinition} を条件リストへ追加します。
     * <p>
     * この {@link DtoDefinitionGroup#add(DtoDefinition)}
     * メソッドは自分自身のインスタンスを返却するため以下のようなメソッドチェーンでの操作が可能です。
     *
     * <pre>
     * <code>
     * DtoDefinitionGroup dtoDefinitionGroup = DtoDefinitionGroup.of()
     *                                  .add(dtoDefinition1)
     *                                  .add(dtoDefinition2);
     * </code>
     * </pre>
     *
     * @param dtoDefinition DTO定義オブジェクト
     * @return 自分自身のインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public DtoDefinitionGroup add(@NonNull DtoDefinition dtoDefinition) {
        this.dtoDefinitionGroup.add(dtoDefinition);
        return this;
    }

    /**
     * 指定された {@code index} の数値に紐づく {@link DtoDefinition} オブジェクトを返却します。
     * 存在しないインデックスを指定した場合は実行時に必ず {@link ArrayIndexOutOfBoundsException} が発生するため、
     * {@link #get(int) メソッドの実行前にが必ず有効なアクセス範囲を確認してください。
     *
     * @param index インデックス
     * @return 指定された {@code index} の数値に紐づく {@link DtoDefinition} オブジェクト
     *
     * @exception ArrayIndexOutOfBoundsException 引数として指定された {@code index}
     *                                           に紐づく情報が存在しない場合
     */
    public DtoDefinition get(int index) {
        return this.dtoDefinitionGroup.get(index);
    }

    /**
     * オブジェクトに含まれる情報が空か判定します。
     *
     * @return オブジェクトに含まれる情報がからの場合は {@code true} 、それ以外は {@code false}
     */
    public boolean isEmpty() {
        return this.dtoDefinitionGroup.isEmpty();
    }

    /**
     * {@link DtoDefinition} クラスを総称型として持つストリームを返却します。
     *
     * @return {@link DtoDefinition} クラスを総称型として持つストリーム
     */
    public Stream<DtoDefinition> stream() {
        return this.dtoDefinitionGroup.stream();
    }

    @Override
    public List<DtoDefinition> nodes() {
        return this.dtoDefinitionGroup;
    }

    @Override
    public int size() {
        return this.dtoDefinitionGroup.size();
    }

    @Override
    public Iterator<DtoDefinition> iterator() {
        return FluentIterator.of(this);
    }
}