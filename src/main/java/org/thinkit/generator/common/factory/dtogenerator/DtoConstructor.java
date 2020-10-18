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

import org.thinkit.common.catalog.Brace;
import org.thinkit.common.catalog.Identifier;
import org.thinkit.common.catalog.Indentation;
import org.thinkit.common.catalog.Parenthesis;
import org.thinkit.generator.common.factory.resource.Constructor;
import org.thinkit.generator.common.factory.resource.FunctionDescription;

import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * DTOクラスのコンストラクタを生成する具象クラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 *
 * @see Constructor
 */
@ToString
@EqualsAndHashCode(callSuper = false)
final class DtoConstructor extends Constructor {

    /**
     * コンストラクタ
     *
     * @param constructorName     コンストラクタ名
     * @param functionDescription 関数の説明
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public DtoConstructor(String constructorName, FunctionDescription functionDescription) {
        super(constructorName, functionDescription);
    }

    @Override
    public String createResource() {

        final String space = Indentation.space();

        final StringBuilder constructor = new StringBuilder();
        constructor.append(super.getFunctionDescription().createResource());
        constructor.append(Identifier.PUBLIC.toIdentifier()).append(space).append(super.getFunctionName());
        constructor.append(Parenthesis.start()).append(this.getParameter()).append(Parenthesis.end()).append(space)
                .append(Brace.start());
        constructor.append(this.getProcess());
        constructor.append(Indentation.getIndentSpaces()).append(Brace.end());

        return constructor.toString();
    }
}