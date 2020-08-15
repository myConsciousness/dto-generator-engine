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
 * DTOフィールドオブジェクトグループを管理するデータクラスです。
 * <p>
 * このクラスはFluentインターフェースの概念を応用し設計されています。<br>
 * そのため、以下のようなメソッドチェーンでの操作が可能です。
 *
 * <pre>
 * <code>
 * DtoFieldGroup dtoFieldGroup = DtoFieldGroup.of()
 *                                  .add(dtoField1)
 *                                  .add(dtoField2);
 * </code>
 * </pre>
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@ToString
@EqualsAndHashCode
public final class DtoFieldGroup implements Iterable<DtoField>, IterableNode<DtoField>, Serializable {

    /**
     * シリアルバージョンUID
     */
    private static final long serialVersionUID = -2251700047243592927L;

    /**
     * DTOフィールドオブジェクトグループ
     */
    @Getter
    private List<DtoField> dtoFieldGroup;

    /**
     * デフォルトコンストラクタ
     */
    private DtoFieldGroup() {
        this.dtoFieldGroup = new ArrayList<>(0);
    }

    /**
     * コピーコンストラクタ
     *
     * @param dtoFieldGroup DTOフィールドオブジェクトグループ
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private DtoFieldGroup(@NonNull DtoFieldGroup dtoFieldGroup) {
        this.dtoFieldGroup = new ArrayList<>(dtoFieldGroup.getDtoFieldGroup());
    }

    /**
     * {@link DtoFieldGroup} クラスの新しいインスタンスを生成し返却します。
     *
     * @return {@link DtoFieldGroup} クラスの新しいインスタンス
     */
    public static DtoFieldGroup of() {
        return new DtoFieldGroup();
    }

    /**
     * 引数として指定された {@code dtoFieldGroup} オブジェクトの情報をコピーした新しい {@link DtoFieldGroup}
     * クラスのインスタンスを生成し返却します。
     */
    public static DtoFieldGroup of(@NonNull DtoFieldGroup dtoFieldGroup) {
        return new DtoFieldGroup(dtoFieldGroup);
    }

    /**
     * 引数として渡された {@code dtoField} を条件リストへ追加します。
     * <p>
     * この {@link DtoFieldGroup#add(DtoField)}
     * メソッドは自分自身のインスタンスを返却するため以下のようなメソッドチェーンでの操作が可能です。
     *
     * <pre>
     * <code>
     * DtoFieldGroup dtoFieldGroup = DtoFieldGroup.of()
     *                                  .add(dtoField1)
     *                                  .add(dtoField2);
     * </code>
     * </pre>
     *
     * @param dtoField DTOフィールドオブジェクト
     * @return 自分自身のインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public DtoFieldGroup add(@NonNull DtoField dtoField) {
        this.dtoFieldGroup.add(dtoField);
        return this;
    }

    /**
     * 指定された {@code index} の数値に紐づく {@link DtoField} オブジェクトを返却します。
     * 存在しないインデックスを指定した場合は実行時に必ず {@link ArrayIndexOutOfBoundsException} が発生するため、
     * {@link #get(int) メソッドの実行前にが必ず有効なアクセス範囲を確認してください。
     *
     * @param index インデックス
     * @return 指定された {@code index} の数値に紐づく {@link DtoField} オブジェクト
     *
     * @exception ArrayIndexOutOfBoundsException 引数として指定された {@code index}
     *                                           に紐づく情報が存在しない場合
     */
    public DtoField get(int index) {
        return this.dtoFieldGroup.get(index);
    }

    /**
     * オブジェクトに含まれる情報が空か判定します。
     *
     * @return オブジェクトに含まれる情報がからの場合は {@code true} 、それ以外は {@code false}
     */
    public boolean isEmpty() {
        return this.dtoFieldGroup.isEmpty();
    }

    /**
     * {@link DtoField} クラスを総称型として持つストリームを返却します。
     *
     * @return {@link DtoField} クラスを総称型として持つストリーム
     */
    public Stream<DtoField> stream() {
        return this.dtoFieldGroup.stream();
    }

    @Override
    public List<DtoField> nodes() {
        return this.dtoFieldGroup;
    }

    @Override
    public int size() {
        return this.dtoFieldGroup.size();
    }

    @Override
    public Iterator<DtoField> iterator() {
        return FluentIterator.of(this);
    }
}