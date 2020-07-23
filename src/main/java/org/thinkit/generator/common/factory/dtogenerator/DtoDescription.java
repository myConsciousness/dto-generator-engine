/**
 * Project Name : generator-commons<br>
 * File Name : DtoDescription.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/06/01<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.common.factory.dtogenerator;

import org.thinkit.generator.common.factory.resource.Description;
import org.thinkit.common.catalog.Indentation;

import lombok.ToString;
import lombok.EqualsAndHashCode;

/**
 * DTOクラスの説明を生成する具象クラスです。<br>
 * DTOに必要な説明を生成する処理を {@link Component#createResource()} に実装します。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 *
 * @see Component
 * @see Component#createResource()
 * @see Description
 */
@ToString
@EqualsAndHashCode(callSuper = false)
final class DtoDescription extends Description {

    /**
     * コンストラクタ
     *
     * @param description 説明
     */
    public DtoDescription(String description) {
        super(description);
    }

    @Override
    public String createResource() {
        final String indentSpaces = Indentation.getIndentSpaces();
        final String returnCode = Indentation.returnCode();

        final StringBuilder description = new StringBuilder();
        description.append(indentSpaces).append("/**").append(returnCode);
        description.append(indentSpaces).append(" * ").append(super.getDescription()).append(returnCode);
        description.append(indentSpaces).append(" */");

        return description.toString();
    }
}