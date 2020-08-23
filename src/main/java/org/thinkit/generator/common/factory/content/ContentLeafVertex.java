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

import org.thinkit.common.catalog.Delimiter;
import org.thinkit.generator.common.factory.json.LeafVertex;

import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * コンテンツの葉頂点を生成する処理を定義したクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@ToString
@EqualsAndHashCode(callSuper = false)
final class ContentLeafVertex extends LeafVertex {

    @Override
    public String createResource() {

        final StringBuilder leafVertex = new StringBuilder();
        final String comma = Delimiter.comma();

        super.getNodeGroups().forEach(nodeGroup -> {
            leafVertex.append(nodeGroup.createResource()).append(comma);
        });

        leafVertex.setLength(leafVertex.length() - comma.length());

        return leafVertex.toString();
    }
}