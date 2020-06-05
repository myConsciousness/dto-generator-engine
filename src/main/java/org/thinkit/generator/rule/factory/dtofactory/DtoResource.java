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

import org.thinkit.generator.rule.factory.resource.ClassDescription;
import org.thinkit.generator.rule.factory.resource.Field;
import org.thinkit.generator.rule.factory.resource.Method;

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
     * コンストラクタ
     * 
     * @param resourceName リソース名
     * @param field        フィールド定義
     * 
     * @exception NullPointerException 引数として{@code null}が渡された場合
     */
    public DtoResource(String resourceName, Field field) {
        super(resourceName, field);
    }

    @Override
    public String createResource() {
        final StringBuilder resource = new StringBuilder();

        final String resourceName = super.getResourceName();
        final ClassDescription classDescription = super.getClassDescription();
        final Field field = super.getField();
        final List<Constructor> constructors = super.getConstructors();
        final List<Method> methods = super.getMethods();

        return resource.toString();
    }
}