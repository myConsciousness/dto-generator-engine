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

package org.thinkit.generator.common.factory.json;

import java.util.ArrayList;
import java.util.List;

import org.thinkit.generator.common.catalog.JsonNodeType;
import org.thinkit.generator.common.factory.Component;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 * JSONのノードグループを管理する抽象クラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 *
 * @see Component
 * @see #createResource()
 */
@ToString
@EqualsAndHashCode
public abstract class NodeGroup implements Component {

    /**
     * ノードタイプ
     */
    @Getter(AccessLevel.PROTECTED)
    private JsonNodeType nodeType = JsonNodeType.OBJECT;

    /**
     * キー
     */
    @Getter(AccessLevel.PROTECTED)
    private String key;

    /**
     * ノードグループ
     */
    @Getter(AccessLevel.PROTECTED)
    private List<Node> nodeGroup;

    /**
     * デフォルトコンストラクタ
     */
    @SuppressWarnings("unused")
    private NodeGroup() {
    }

    /**
     * コンストラクタ
     *
     * @param key キー
     *
     * @exception NullPointerException 引数として {@code null}が渡された場合
     */
    protected NodeGroup(@NonNull String key) {
        this.key = key;
        this.nodeGroup = new ArrayList<>(0);
    }

    /**
     * 引数として指定された {@code node} オブジェクトをノードグループへ追加します。
     * <p>
     * 引数として {@code null} が渡された場合は実行時に必ず失敗します。
     *
     * @param node ノードオブジェクト
     * @return 自分自身のインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public NodeGroup add(@NonNull Node node) {
        this.nodeGroup.add(node);
        return this;
    }

    /**
     * ノードグループのサイズが空か判定します。
     *
     * @return ノードグループのサイズが空の場合は {@code true}、それ以外は {@code false}
     */
    public boolean isEmpty() {
        return this.nodeGroup.size() <= 0;
    }

    /**
     * JSONノードタイプをオブジェクトへ変換します。JSONノードタイプは初期値として {@link JsonNodeType#OBJECT}
     * が設定されているため、 {@link NodeGroup} クラスのインスタンスを生成した時点でこの
     * {@link NodeGroup#toObject()} メソッドを使用する必要はありません。
     * <p>
     * この {@link NodeGroup#toObject()}
     * メソッドは自分自身のインスタンスを返却するため、後続処理をメソッドチェーンの形式で実行することができます。
     *
     * @return 自分自身のインスタンス
     */
    public NodeGroup toObject() {
        this.nodeType = JsonNodeType.OBJECT;
        return this;
    }

    /**
     * JSONノードタイプを配列へ変換します。
     * <p>
     * この {@link NodeGroup#toArray()}
     * メソッドは自分自身のインスタンスを返却するため、後続処理をメソッドチェーンの形式で実行することができます。
     *
     * @return 自分自身のインスタンス
     */
    public NodeGroup toArray() {
        this.nodeType = JsonNodeType.ARRAY;
        return this;
    }
}