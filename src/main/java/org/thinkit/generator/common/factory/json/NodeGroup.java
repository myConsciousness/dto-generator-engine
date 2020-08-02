/**
 * Project Name : generator-commons<br>
 * File Name : NodeGroup.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/08/02<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.common.factory.json;

import java.util.ArrayList;
import java.util.List;

import org.thinkit.generator.common.factory.Component;

import lombok.EqualsAndHashCode;
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
     * ノードグループ
     */
    private List<Node> nodeGroup;

    /**
     * デフォルトコンストラクタ
     */
    private NodeGroup() {
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
}