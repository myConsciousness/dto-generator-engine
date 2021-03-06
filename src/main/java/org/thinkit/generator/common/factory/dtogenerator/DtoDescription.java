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

import org.thinkit.generator.common.factory.resource.Description;

import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * DTOクラスの説明を生成する具象クラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 *
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

        final StringBuilder description = new StringBuilder();
        description.append("/**");
        description.append(" * ").append(super.getDescription());
        description.append(" */");

        return description.toString();
    }
}