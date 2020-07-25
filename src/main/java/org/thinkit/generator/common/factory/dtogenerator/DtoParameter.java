/**
 * Project Name : generator-commons<br>
 * File Name : Parameter.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/06/05<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.common.factory.dtogenerator;

import org.thinkit.common.catalog.Indentation;
import org.thinkit.generator.common.factory.resource.Parameter;

import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * DTOクラスの引数定義を生成する具象クラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 *
 * @see Parameter
 */
@ToString
@EqualsAndHashCode(callSuper = false)
final class DtoParameter extends Parameter {

    /**
     * コンストラクタ
     *
     * @param dataType     データ型
     * @param variableName 変数名
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public DtoParameter(String dataType, String variableName) {
        super(dataType, variableName);
    }

    @Override
    public String createResource() {
        final StringBuilder parameter = new StringBuilder();
        parameter.append(super.getDataType()).append(Indentation.space()).append(super.getVariableName());
        return parameter.toString();
    }
}