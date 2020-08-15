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

package org.thinkit.generator.common.factory.dtogenerator.strategy;

import org.thinkit.common.catalog.Delimiter;
import org.thinkit.common.catalog.Indentation;
import org.thinkit.common.catalog.Operand;
import org.thinkit.generator.common.factory.resource.strategy.ConstructorProcessStrategy;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

/**
 * 必須引数有りのコンストラクタの処理定義を生成する際に使用するストラテジーを実装した具象クラスです。
 * <p>
 * 以下の機能を提供しています。<br>
 * {@link #toConstructorProcess(String, String)}<br>
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 *
 * @see #toConstructorProcess(String, String)
 */
@ToString
@EqualsAndHashCode(callSuper = false)
public class RequiredConstructorProcess extends ConstructorProcessStrategy {

    /**
     * カレントオブジェクト
     */
    private static final String CURRENT_OBJECT = "this";

    @Override
    public String toConstructorProcess(@NonNull String variableName, @NonNull String getterName) {
        final String space = Indentation.space();

        final StringBuilder process = new StringBuilder();
        process.append(CURRENT_OBJECT).append(Delimiter.period()).append(variableName).append(space);
        process.append(Operand.assignment()).append(space).append(variableName).append(Delimiter.semicolon());

        return process.toString();
    }
}