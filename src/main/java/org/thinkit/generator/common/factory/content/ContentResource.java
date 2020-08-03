/**
 * Project Name : generator-commons<br>
 * File Name : ContentResource.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/08/03<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
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