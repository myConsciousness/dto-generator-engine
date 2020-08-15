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

package org.thinkit.generator.common.factory.content;

import org.thinkit.common.catalog.Brace;
import org.thinkit.common.catalog.Indentation;
import org.thinkit.generator.common.factory.json.LeafVertex;
import org.thinkit.generator.common.factory.json.Resource;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

/**
 * コンテンツのリソースを生成する処理を定義したクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@ToString
@EqualsAndHashCode(callSuper = false)
final class ContentResource extends Resource {

    /**
     * コンストラクタ
     *
     * @param leafVertex 葉頂点
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public ContentResource(@NonNull LeafVertex leafVertex) {
        super(leafVertex);
    }

    @Override
    public String createResource() {

        final StringBuilder resource = new StringBuilder();
        final String returnCode = Indentation.returnCode();

        resource.append(Brace.start()).append(returnCode);
        resource.append(super.getLeafVertex().createResource()).append(returnCode);
        resource.append(Brace.end()).append(returnCode);

        return resource.toString();
    }
}