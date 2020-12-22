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
import org.thinkit.generator.common.catalog.Annotation;
import org.thinkit.generator.common.factory.resource.ClassDescription;
import org.thinkit.generator.common.factory.resource.Copyright;
import org.thinkit.generator.common.factory.resource.Package;
import org.thinkit.generator.common.factory.resource.Resource;

import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * DTOクラスのリソースを生成する具象クラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 *
 * @see Resource
 */
@ToString
@EqualsAndHashCode(callSuper = false)
final class DtoResource extends Resource {

    /**
     * リソース
     */
    private final StringBuilder resource = new StringBuilder();

    /**
     * コンストラクタ
     *
     * @param copyright        著作権
     * @param packageName      パッケージ名
     * @param classDescription クラスの説明
     * @param resourceName     リソース名
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public DtoResource(Copyright copyright, Package packageName, ClassDescription classDescription,
            String resourceName) {
        super(copyright, packageName, classDescription, resourceName);
    }

    @Override
    public String createResource() {

        this.createResource(super.getCopyright().createResource());
        this.createClassNameResource();
        this.createFieldResource();
        this.createConstructorResource();
        this.createResource(Brace.end());

        return this.getResource();
    }

    /**
     * {@link Resource} に設定された定義情報を基にクラス名リソースを生成します。<br>
     * このメソッドではパッケージ名からクラス定義の開始ブレースまでを生成します。<br>
     */
    private void createClassNameResource() {

        final String space = Indentation.space();

        final StringBuilder resource = new StringBuilder();
        resource.append("package").append(space).append(super.getPackageName()).append(";");
        resource.append("import java.util.*;");
        resource.append("import lombok.*;");
        resource.append(super.getClassDescription().createResource());
        resource.append(Annotation.lombokGetter());
        resource.append(Annotation.lombokToString());
        resource.append(Annotation.lombokEqualsAndHashCode());
        resource.append(Identifier.PUBLIC.toIdentifier()).append(space).append("final").append(space).append("class")
                .append(space).append(super.getResourceName()).append(space);
        resource.append(Brace.start());

        this.createResource(resource.toString());
    }

    /**
     * {@link Resource} に設定された定義情報を基にフィールドリソースを生成します。<br>
     * このメソッドではフィールド定義までを生成します。<br>
     */
    private void createFieldResource() {

        final String returnCode = Indentation.returnCode();

        super.getFields().forEach(field -> {
            this.createResource(field.createResource());
            this.createResource(returnCode);
            this.createResource(returnCode);
        });
    }

    /**
     * {@link Resource} に設定された定義情報を基にコンストラクタリソースを生成します。<br>
     * このメソッドではコンストラクタ定義までを生成します。<br>
     */
    private void createConstructorResource() {

        final StringBuilder resource = new StringBuilder();

        super.getConstructors().forEach(constructor -> {
            resource.append(constructor.createResource());
        });

        this.createResource(resource.toString());
    }

    /**
     * 引数として指定された文字列を基にリソースを生成します。
     *
     * @param resource リソース
     */
    private void createResource(String resource) {
        assert resource != null;
        this.resource.append(resource);
    }

    /**
     * 生成されたリソースを返却します。
     *
     * @return 生成されたリソース
     */
    private String getResource() {
        return this.resource.toString();
    }
}
