/**
 * Project Name : generator-commons<br>
 * File Name : ContentNode.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/08/02<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.common.factory.content;

import org.thinkit.common.catalog.Brace;
import org.thinkit.common.catalog.Indentation;
import org.thinkit.generator.common.factory.json.ItemGroup;
import org.thinkit.generator.common.factory.json.Node;
import org.thinkit.generator.common.factory.json.NodeGroup;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

/**
 * コンテンツのノードを生成する処理を定義したクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@ToString
@EqualsAndHashCode(callSuper = false)
final class ContentNode extends Node {

    public ContentNode(@NonNull ItemGroup itemGroup, @NonNull NodeGroup nodeGroup) {
        super(itemGroup, nodeGroup);
    }

    @Override
    public String createResource() {

        final StringBuilder node = new StringBuilder();
        final String returnCode = Indentation.returnCode();

        node.append(Brace.start()).append(returnCode);
        node.append(super.getItemGroup().createResource()).append(returnCode);

        final NodeGroup nodeGroup = super.getNodeGroup();

        if (!nodeGroup.isEmpty()) {
            node.append(nodeGroup.createResource()).append(returnCode);
        }

        node.append(Brace.end());

        return node.toString();
    }
}