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