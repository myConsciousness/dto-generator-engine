/**
 * Project Name : generator-commons<br>
 * File Name : DtoMethodParamAnnotation.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/06/06<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.common.factory.dtogenerator;

import org.thinkit.common.catalog.Indentation;
import org.thinkit.generator.common.catalog.Annotation;
import org.thinkit.generator.common.factory.resource.FunctionParamAnnotation;

import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * DTOクラスのメソッドの引数アノテーションを生成する具象クラスです。<br>
 * DTOに必要なメソッドの引数アノテーションを生成する処理を{@link Component#createResource()}に実装します。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 * @see Component
 * @see Component#createResource()
 * @see FunctionParamAnnotation
 */
@ToString
@EqualsAndHashCode(callSuper = false)
final class DtoMethodParamAnnotation extends FunctionParamAnnotation {

    /**
     * コンストラクタ
     *
     * @param variableName 変数名
     * @param description  説明
     * @exception NullPointerException 引数として{@code null}が渡された場合
     */
    protected DtoMethodParamAnnotation(String variableName, String description) {
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