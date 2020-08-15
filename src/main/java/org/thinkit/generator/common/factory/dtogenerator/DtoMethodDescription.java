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

package org.thinkit.generator.common.factory.dtogenerator;

import java.util.List;

import org.thinkit.common.catalog.Indentation;
import org.thinkit.generator.common.factory.resource.FunctionDescription;
import org.thinkit.generator.common.factory.resource.FunctionParamAnnotation;

import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * DTOクラスのメソッドの説明定義を生成する具象クラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 *
 * @see FunctionDescription
 */
@ToString
@EqualsAndHashCode(callSuper = false)
class DtoMethodDescription extends FunctionDescription {

    /**
     * コンストラクタ
     *
     * @param description 説明
     * @exception NullPointerException 引数として {@code null} が渡された場合
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