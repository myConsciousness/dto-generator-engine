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
import org.thinkit.generator.common.factory.resource.Description;
import org.thinkit.generator.common.factory.resource.Field;
import org.thinkit.generator.common.factory.resource.FieldDefinition;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

/**
 * DTOクラスのフィールドを生成する具象クラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 *
 * @see Field
 */
@ToString
@EqualsAndHashCode(callSuper = false)
public final class DtoField extends Field {

    /**
     * 引数として渡された情報を基に {@link DtoField} の新しいインスタンスを生成します。
     *
     * @param fieldDefinition フィールド定義
     * @param description     フィールドの説明
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public DtoField(@NonNull FieldDefinition fieldDefinition, @NonNull Description description) {
        super(fieldDefinition, description);
    }

    @Override
    public String createResource() {

        final StringBuilder field = new StringBuilder();
        field.append(super.getDescription().createResource()).append(Indentation.returnCode());
        field.append(super.getFieldDefinition().createResource());

        return field.toString();
    }
}