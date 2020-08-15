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
 * DTOリソースグループを管理するデータクラスです。
 * <p>
 * このクラスはFluentインターフェースの概念を応用し設計されています。<br>
 * そのため、以下のようなメソッドチェーンでの操作が可能です。
 *
 * <pre>
 * <code>
 * DtoResourceGroup dtoResourceGroup = DtoResourceGroup.of()
 *                                  .add(dtoResource1)
 *                                  .add(dtoResource2);
 * </code>
 * </pre>
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@ToString
@EqualsAndHashCode
public final class DtoResourceGroup implements Iterable<DtoResource>, IterableNode<DtoResource>, Serializable {

    /**
     * シリアルバージョンUID
     */
    private static final long serialVersionUID = 8049148657195824853L;

    /**
     * DTOリソースグループ
     */
    @Getter
    private List<DtoResource> dtoResourceGroup;

    /**
     * デフォルトコンストラクタ
     */
    private DtoResourceGroup() {
        this.dtoResourceGroup = new ArrayList<>(0);
    }

    /**
     * コピーコンストラクタ
     *
     * @param dtoResourceGroup DTOリソースグループ
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private DtoResourceGroup(@NonNull DtoResourceGroup dtoResourceGroup) {
        this.dtoResourceGroup = new ArrayList<>(dtoResourceGroup.getDtoResourceGroup());
    }

    /**
     * {@link DtoResourceGroup} クラスの新しいインスタンスを生成し返却します。
     *
     * @return {@link DtoResourceGroup} クラスの新しいインスタンス
     */
    public static DtoResourceGroup of() {
        return new DtoResourceGroup();
    }

    /**
     * 引数として指定された {@code dtoResourceGroup} オブジェクトの情報をコピーした新しい
     * {@link DtoResourceGroup} クラスのインスタンスを生成し返却します。
     */
    public static DtoResourceGroup of(@NonNull DtoResourceGroup dtoResourceGroup) {
        return new DtoResourceGroup(dtoResourceGroup);
    }

    /**
     * 引数として渡された {@code dtoResource} を条件リストへ追加します。
     * <p>
     * この {@link DtoResourceGroup#add(DtoResource)}
     * メソッドは自分自身のインスタンスを返却するため以下のようなメソッドチェーンでの操作が可能です。
     *
     * <pre>
     * <code>
     * DtoResourceGroup dtoResourceGroup = DtoResourceGroup.of()
     *                                  .add(dtoResource1)
     *                                  .add(dtoResource2);
     * </code>
     * </pre>
     *
     * @param dtoResource DTOリソースオブジェクト
     * @return 自分自身のインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public DtoResourceGroup add(@NonNull DtoResource dtoResource) {
        this.dtoResourceGroup.add(dtoResource);
        return this;
    }

    /**
     * 指定された {@code index} の数値に紐づく {@link DtoResource} オブジェクトを返却します。
     * 存在しないインデックスを指定した場合は実行時に必ず {@link ArrayIndexOutOfBoundsException} が発生するため、
     * {@link #get(int) メソッドの実行前にが必ず有効なアクセス範囲を確認してください。
     *
     * @param index インデックス
     * @return 指定された {@code index} の数値に紐づく {@link DtoResource} オブジェクト
     *
     * @exception ArrayIndexOutOfBoundsException 引数として指定された {@code index}
     *                                           に紐づく情報が存在しない場合
     */
    public DtoResource get(int index) {
        return this.dtoResourceGroup.get(index);
    }

    /**
     * オブジェクトに含まれる情報が空か判定します。
     *
     * @return オブジェクトに含まれる情報がからの場合は {@code true} 、それ以外は {@code false}
     */
    public boolean isEmpty() {
        return this.dtoResourceGroup.isEmpty();
    }

    /**
     * {@link DtoResource} クラスを総称型として持つストリームを返却します。
     *
     * @return {@link DtoResource} クラスを総称型として持つストリーム
     */
    public Stream<DtoResource> stream() {
        return this.dtoResourceGroup.stream();
    }

    @Override
    public List<DtoResource> nodes() {
        return this.dtoResourceGroup;
    }

    @Override
    public int size() {
        return this.dtoResourceGroup.size();
    }

    @Override
    public Iterator<DtoResource> iterator() {
        return FluentIterator.of(this);
    }
}