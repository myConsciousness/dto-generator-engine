/**
 * Project Name : generator-commons<br>
 * File Name : DtoResource.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/06/05<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.common.factory.dtogenerator;

import java.util.List;

import org.thinkit.common.catalog.Brace;
import org.thinkit.common.catalog.Identifier;
import org.thinkit.common.catalog.Indentation;
import org.thinkit.generator.common.catalog.Annotation;
import org.thinkit.generator.common.factory.resource.ClassDescription;
import org.thinkit.generator.common.factory.resource.Constructor;
import org.thinkit.generator.common.factory.resource.Copyright;
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
    public DtoResource(Copyright copyright, String packageName, ClassDescription classDescription,
            String resourceName) {
        super(copyright, packageName, classDescription, resourceName, new DtoField());
    }

    @Override
    public String createResource() {
        final String returnCode = Indentation.returnCode();

        this.createResource(super.getCopyright().createResource());
        this.createResource(returnCode);
        this.createResource(returnCode);

        this.createClassNameResource();
        this.createResource(returnCode);

        this.createFieldResource();
        this.createResource(returnCode);

        this.createConstructorResource();
        this.createResource(Brace.end());
        this.createResource(returnCode);

        return this.getResource();
    }

    /**
     * {@link Resource} に設定された定義情報を基にクラス名リソースを生成します。<br>
     * このメソッドではパッケージ名からクラス定義の開始ブレースまでを生成します。<br>
     */
    private void createClassNameResource() {
        final String space = Indentation.space();
        final String returnCode = Indentation.returnCode();

        final StringBuilder resource = new StringBuilder();
        resource.append("package").append(space).append(super.getPackageName()).append(";").append(returnCode);
        resource.append(returnCode);
        resource.append("import java.util.*;").append(returnCode);
        resource.append("import lombok.*;").append(returnCode);
        resource.append(returnCode);
        resource.append(super.getClassDescription().createResource()).append(returnCode);
        resource.append(Annotation.lombokGetter()).append(returnCode);
        resource.append(Annotation.lombokToString()).append(returnCode);
        resource.append(Annotation.lombokEqualsAndHashCode()).append(returnCode);
        resource.append(Identifier.PUBLIC.toIdentifier()).append(space).append("final").append(space).append("class")
                .append(space).append(super.getResourceName()).append(space);
        resource.append(Brace.start()).append(returnCode);

        this.createResource(resource.toString());
    }

    /**
     * {@link Resource} に設定された定義情報を基にフィールドリソースを生成します。<br>
     * このメソッドではフィールド定義までを生成します。<br>
     */
    private void createFieldResource() {
        this.createResource(super.getField().createResource());
    }

    /**
     * {@link Resource} に設定された定義情報を基にコンストラクタリソースを生成します。<br>
     * このメソッドではコンストラクタ定義までを生成します。<br>
     */
    private void createConstructorResource() {
        final String returnCode = Indentation.returnCode();

        final StringBuilder resource = new StringBuilder();
        final List<Constructor> constructors = super.getConstructors();

        for (Constructor constructor : constructors) {
            resource.append(constructor.createResource()).append(returnCode);
            resource.append(returnCode);
        }

        resource.setLength(resource.length() - returnCode.length());
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