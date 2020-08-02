/**
 * Project Name : generator-commons<br>
 * File Name : ContentItemGroup.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/08/02<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.common.factory.content;

import org.thinkit.common.catalog.Indentation;
import org.thinkit.generator.common.factory.json.ItemGroup;

import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * コンテンツの項目グループを生成する処理を定義したクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@ToString
@EqualsAndHashCode(callSuper = false)
final class ContentItemGroup extends ItemGroup {

    @Override
    public String createResource() {

        final StringBuilder itemGroup = new StringBuilder();
        final String indent = Indentation.getIndentSpaces();
        final String returnCode = Indentation.returnCode();

        super.getItemGroup().forEach(item -> {
            itemGroup.append(indent).append(item.createResource()).append(returnCode);
        });

        itemGroup.setLength(itemGroup.length() - returnCode.length());

        return itemGroup.toString();
    }
}