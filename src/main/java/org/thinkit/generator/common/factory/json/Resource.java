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