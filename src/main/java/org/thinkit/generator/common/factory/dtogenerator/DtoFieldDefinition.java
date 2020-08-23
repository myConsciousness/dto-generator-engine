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

import org.thinkit.common.catalog.Delimiter;
import org.thinkit.common.catalog.Identifier;
import org.thinkit.common.catalog.Indentation;
import org.thinkit.common.catalog.Operand;
import org.thinkit.common.catalog.PrimitiveDataType;
import org.thinkit.generator.common.catalog.Annotation;
import org.thinkit.generator.common.factory.resource.FieldDefinition;

import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * DTOクラスのフィールド定義を生成する具象クラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 *
 * @see FieldDefinition
 */
@ToString
@EqualsAndHashCode(callSuper = false)
final class DtoFieldDefinition extends FieldDefinition {

    /**
     * コンストラクタ
     *
     * @param dataType     データ型
     * @param variableName 変数名
     * @param initialValue 初期値
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public DtoFieldDefinition(String dataType, String variableName, String initialValue) {
        super(dataType, variableName, initialValue);
    }

    @Override
    public String createResource() {
        final StringBuilder field = new StringBuilder();
        final String dataType = super.getDataType();

        if (PrimitiveDataType.isPrimitive(dataType)) {
            field.append(Annotation.lombokNonNull()).append(Indentation.returnCode());
        }

        final String space = Indentation.space();

        field.append(Identifier.PRIVATE.toIdentifier()).append(space).append(dataType).append(space);
        field.append(super.getVariableName()).append(space);
        field.append(Operand.assignment()).append(space).append(super.getInitialValue()).append(Delimiter.semicolon());

        return field.toString();
    }
}