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
import org.thinkit.generator.common.factory.resource.ClassDescription;

import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * DTOクラスのクラスに関する説明を生成する具象クラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 *
 * @see ClassDescription
 */
@ToString
@EqualsAndHashCode(callSuper = false)
final class DtoClassDescription extends ClassDescription {

    /**
     * 初期バージョン
     */
    private static final String INITIAL_VERSION = "1.0";

    /**
     * コンストラクタ
     *
     * @param description クラスの説明
     * @param creator     クラスの作成者
     * @param version     クラスのバージョン
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public DtoClassDescription(String description, String creator, String version) {
        super(description, creator, version);
    }

    @Override
    public String createResource() {

        final String space = Indentation.space();

        final StringBuilder classDescription = new StringBuilder();
        classDescription.append("/**");
        classDescription.append(" * ").append(super.getDescription());
        classDescription.append(" *");
        classDescription.append(" * ").append(Annotation.author()).append(space).append(super.getCreator());
        classDescription.append(" * ").append(Annotation.since()).append(space).append(INITIAL_VERSION);
        classDescription.append(" * ").append(Annotation.version()).append(space).append(super.getVersion());
        classDescription.append(" */");

        return classDescription.toString();
    }
}