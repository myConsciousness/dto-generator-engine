/**
 * Project Name : Generator<br>
 * File Name : DtoMethodDescription.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/06/06<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.rule.factory.dtofactory;

import java.util.List;

import org.thinkit.common.catalog.Indentation;
import org.thinkit.generator.rule.factory.resource.FunctionDescription;
import org.thinkit.generator.rule.factory.resource.FunctionParamAnnotation;

import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * DTOクラスのメソッドの説明定義を生成する具象クラスです。<br>
 * DTOに必要なメソッドの説明定義を生成する処理を{@link Component#createResource()}に実装します。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 * @see Component
 * @see Component#createResource()
 * @see FunctionDescription
 */
@ToString
@EqualsAndHashCode(callSuper = false)
class DtoMethodDescription extends FunctionDescription {

    /**
     * コンストラクタ
     *
     * @param description 説明
     * @exception NullPointerException 引数として{@code null}が渡された場合
     */
    protected DtoMethodDescription(String description) {
        super(description);
    }

    @Override
    public String createResource() {
        final String indentSpaces = Indentation.getIndentSpaces();
        final String returnCode = Indentation.returnCode();

        final StringBuilder methodDescription = new StringBuilder();
        methodDescription.append(indentSpaces).append("/**").append(returnCode);
        methodDescription.append(indentSpaces).append(" * ").append(super.getDescription()).append(returnCode);

        if (super.hasParamAnnotation()) {
            methodDescription.append(indentSpaces).append(" *").append(returnCode);

            final List<FunctionParamAnnotation> functionParamAnnotations = super.getFunctionParamAnnotations();

            for (FunctionParamAnnotation functionParamAnnotation : functionParamAnnotations) {
                methodDescription.append(indentSpaces).append(" * ").append(functionParamAnnotation.createResource())
                        .append(returnCode);
            }
        }

        methodDescription.append(indentSpaces).append(" */");

        return methodDescription.toString();
    }
}