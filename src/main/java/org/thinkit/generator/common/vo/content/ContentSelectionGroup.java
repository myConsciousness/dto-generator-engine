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
 * コンテンツのコンテンツ選択グループを管理するデータクラスです。
 * <p>
 * このクラスはFluentインターフェースの概念を応用し設計されています。<br>
 * そのため、以下のようなメソッドチェーンでの操作が可能です。
 *
 * <pre>
 * 使用例:
 * <code>
 * ContentSelectionGroup contentSelectionGroup = ContentSelectionGroup.of()
 *                                               .add(selection1)
 *                                               .add(selection2);
 * </code>
 * </pre>
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@ToString
@EqualsAndHashCode
public final class ContentSelectionGroup
        implements Iterable<ContentSelection>, IterableNode<ContentSelection>, Serializable {

    /**
     * シリアルバージョンUID
     */
    private static final long serialVersionUID = 8783357037120275796L;

    /**
     * コンテンツ選択グループ
     */
    @Getter
    private List<ContentSelection> contentSelectionGroup;

    /**
     * デフォルトコンストラクタ
     */
    private ContentSelectionGroup() {
        this.contentSelectionGroup = new ArrayList<>(0);
    }

    /**
     * コピーコンストラクタ
     *
     * @param contentSelectionGroup コンテンツ選択グループ
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private ContentSelectionGroup(@NonNull ContentSelectionGroup contentSelectionGroup) {
        this.contentSelectionGroup = new ArrayList<>(contentSelectionGroup.getContentSelectionGroup());
    }

    /**
     * {@link ContentSelectionGroup} クラスの新しいインスタンスを生成し返却します。
     *
     * @return {@link ContentSelectionGroup} クラスの新しいインスタンス
     */
    public static ContentSelectionGroup of() {
        return new ContentSelectionGroup();
    }

    /**
     * 引数として指定された {@code contentSelectionGroup} オブジェクトの情報をコピーした新しい
     * {@link ContentSelectionGroup} クラスのインスタンスを生成し返却します。
     *
     * @param contentSelectionGroup コンテンツ選択グループ
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static ContentSelectionGroup of(@NonNull ContentSelectionGroup contentSelectionGroup) {
        return new ContentSelectionGroup(contentSelectionGroup);
    }

    /**
     * 引数として渡された {@code contentSelection} を選択ノードオブジェクトリストへ追加します。
     * <p>
     * この {@link ContentSelectionGroup#add(ContentSelection)}
     * メソッドは自分自身のインスタンスを返却するため以下のようなメソッドチェーンでの操作が可能です。
     *
     * <pre>
     * <code>
     * ContentSelectionGroup contentSelectionGroup = ContentSelectionGroup.of()
     *                                               .add(selection1)
     *                                               .add(selection2);
     * </code>
     * </pre>
     *
     * @param contentSelection コンテンツ選択項目
     * @return 自分自身のインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public ContentSelectionGroup add(@NonNull ContentSelection contentSelection) {
        this.contentSelectionGroup.add(contentSelection);
        return this;
    }

    /**
     * 指定された {@code index} の数値に紐づく {@link ContentSelection} オブジェクトを返却します。
     * 存在しないインデックスを指定した場合は実行時に必ず {@link ArrayIndexOutOfBoundsException} が発生するため、
     * {@link #get(int) メソッドの実行前にが必ず有効なアクセス範囲を確認してください。
     *
     * @param index インデックス
     * @return 指定された {@code index} の数値に紐づく {@link ContentSelection} オブジェクト
     *
     * @exception ArrayIndexOutOfBoundsException 引数として指定された {@code index}
     *                                           に紐づく情報が存在しない場合
     */
    public ContentSelection get(int index) {
        return this.contentSelectionGroup.get(index);
    }

    /**
     * オブジェクトに含まれる情報が空か判定します。
     *
     * @return オブジェクトに含まれる情報がからの場合は {@code true} 、それ以外は {@code false}
     */
    public boolean isEmpty() {
        return this.contentSelectionGroup.isEmpty();
    }

    /**
     * {@link Selection} クラスを総称型として持つストリームを返却します。
     *
     * @return {@link Selection} クラスを総称型として持つストリーム
     */
    public Stream<ContentSelection> stream() {
        return this.contentSelectionGroup.stream();
    }

    @Override
    public List<ContentSelection> nodes() {
        return this.contentSelectionGroup;
    }

    @Override
    public int size() {
        return this.contentSelectionGroup.size();
    }

    @Override
    public Iterator<ContentSelection> iterator() {
        return FluentIterator.of(this);
    }
}