/**
 * Project Name : generator-commons<br>
 * File Name : LeafVertex.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/08/02<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.common.factory.json;

import org.thinkit.generator.common.factory.Component;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 * JSONの葉頂点を抽象化したクラスです。
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
public abstract class LeafVertex implements Component {

    /**
     * ノードグループ
     */
    @Getter(AccessLevel.PROTECTED)
    private NodeGroup nodeGroup;

    /**
     * デフォルトコンストラクタ
     */
    @SuppressWarnings("unused")
    private LeafVertex() {
    }

    /**
     * コンストラクタ
     *
     * @param nodeGroup ノードグループ
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    protected LeafVertex(@NonNull NodeGroup nodeGroup) {
        this.nodeGroup = nodeGroup;
    }
}