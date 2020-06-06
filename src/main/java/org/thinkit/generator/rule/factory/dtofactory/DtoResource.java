/**
 * Project Name : Generator<br>
 * File Name : DtoResource.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/06/05<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */
package org.thinkit.generator.rule.factory.dtofactory;

import org.thinkit.generator.rule.factory.resource.Resource;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

import org.thinkit.generator.rule.factory.resource.Constructor;
import org.thinkit.common.catalog.Brace;
import org.thinkit.common.catalog.Identifier;
import org.thinkit.common.catalog.Indentation;
import org.thinkit.generator.catalog.dtogenerator.DtoAnnotation;
import org.thinkit.generator.rule.factory.resource.Field;

/**
 * DTOクラスのリソースを生成する具象クラスです。<br>
 * DTOのリソースを生成する処理を{@link Component#createResource()}に実装します。
 * 
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 * @see Component
 * @see Component#createResource()
 * @see Resource
 */
@ToString
@EqualsAndHashCode(callSuper = false)
public class DtoResource extends Resource {

    /**
     * リソース
     */
    private final StringBuilder resource = new StringBuilder();

    /**
     * コンストラクタ
     * 
     * @param packageName  パッケージ名
     * @param resourceName リソース名
     * @param field        フィールド定義
     * 
     * @exception NullPointerException 引数として{@code null}が渡された場合
     */
    public DtoResource(String packageName, String resourceName, Field field) {
        super(packageName, resourceName, field);
    }

    @Override
    public String createResource() {
        final String returnCode = Indentation.returnCode();

        this.createClassNameResource();
        this.createResource(returnCode);

        this.createFieldResource();
        this.createResource(returnCode);

        this.createConstructorResource();
        this.createResource(Brace.end());

        return this.getResource();
    }

    /**
     * {@link Resource}に設定された定義情報を基にクラス名リソースを生成します。<br>
     * このメソッドではパッケージ名からクラス定義の開始ブレースまでを生成します。<br>
     */
    private void createClassNameResource() {
        final String space = Indentation.space();
        final String returnCode = Indentation.returnCode();

        final StringBuilder resource = new StringBuilder();
        resource.append(super.getPackageName()).append(returnCode);
        resource.append(returnCode);
        resource.append("import java.util.*;").append(returnCode);
        resource.append("import lombok.*;").append(returnCode);
        resource.append(returnCode);
        resource.append(super.getClassDescription().createResource()).append(returnCode);
        resource.append(DtoAnnotation.lombokToString()).append(returnCode);
        resource.append(DtoAnnotation.lombokEqualsAndHashCode()).append(returnCode);
        resource.append(Identifier.PUBLIC.toIdentifier()).append(space).append("class").append(space)
                .append(super.getResourceName()).append(space).append(Brace.start()).append(returnCode);

        this.createResource(resource.toString());
    }

    /**
     * {@link Resource}に設定された定義情報を基にフィールドリソースを生成します。<br>
     * このメソッドではフィールド定義までを生成します。<br>
     */
    private void createFieldResource() {
        final StringBuilder resource = new StringBuilder();
        resource.append(super.getField().createResource()).append(Indentation.returnCode());
        this.createResource(resource.toString());
    }

    /**
     * {@link Resource}に設定された定義情報を基にコンストラクタリソースを生成します。<br>
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