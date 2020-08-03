/**
 * Project Name : generator-commons<br>
 * File Name : ContentLeafVertex.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/08/03<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.common.factory.content;

import org.thinkit.common.catalog.Delimiter;
import org.thinkit.common.catalog.Indentation;
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
        final String returnCode = Indentation.returnCode();
        final String comma = Delimiter.comma();

        super.getNodeGroups().forEach(nodeGroup -> {
            leafVertex.append(nodeGroup.createResource()).append(comma).append(returnCode).append(returnCode);
        });

        leafVertex.setLength(leafVertex.length() - (comma.length() + returnCode.length() * 2));

        return leafVertex.toString();
    }
}