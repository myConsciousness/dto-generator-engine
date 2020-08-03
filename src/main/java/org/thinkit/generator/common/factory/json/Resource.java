/**
 * Project Name : generator-commons<br>
 * File Name : Resource.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/08/02<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.common.factory.json;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 * JSONリソースを抽象化したクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@ToString
@EqualsAndHashCode
public abstract class Resource {

    /**
     * 葉頂点
     */
    @Getter(AccessLevel.PROTECTED)
    private LeafVertex leafVertex;

    /**
     * デフォルトコンストラクタ
     */
    @SuppressWarnings("unused")
    private Resource() {
    }

    /**
     * コンストラクタ
     *
     * @param leafVertex 葉頂点
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    protected Resource(@NonNull LeafVertex leafVertex) {
        this.leafVertex = leafVertex;
    }

    /**
     * JSONリソースを生成し文字列表現として返却する処理を定義するメソッドです。
     *
     * @return JSONリソース
     */
    public abstract String createResource();
}