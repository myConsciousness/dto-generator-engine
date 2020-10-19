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

import org.thinkit.common.catalog.Indentation;
import org.thinkit.generator.common.catalog.Annotation;
import org.thinkit.generator.common.factory.resource.DescriptionTag;

import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * DTOクラスのメソッドの引数アノテーションを生成する具象クラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 *
 * @see FunctionParamAnnotation
 */
@ToString
@EqualsAndHashCode(callSuper = false)
final class DtoDescriptionTag extends DescriptionTag {

    /**
     * コンストラクタ
     *
     * @param variableName 変数名
     * @param description  説明
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    protected DtoDescriptionTag(String variableName, String description) {
        super(variableName, description);
    }

    @Override
    public String createResource() {
        final String space = Indentation.space();
        final String annotation = Annotation.param();

        final StringBuilder paramAnnotation = new StringBuilder();
        paramAnnotation.append(annotation).append(space).append(super.getVariableName()).append(space)
                .append(super.getDescription());

        return paramAnnotation.toString();
    }
}