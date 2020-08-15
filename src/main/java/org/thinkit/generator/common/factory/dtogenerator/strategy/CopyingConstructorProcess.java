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
 * コピーコンストラクタの処理定義を生成する際に使用するストラテジーを実装した具象クラスです。
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
public class CopyingConstructorProcess extends ConstructorProcessStrategy {

    /**
     * カレントオブジェクト
     */
    private static final String CURRENT_OBJECT = "this";

    @Override
    public String toConstructorProcess(@NonNull String variableName, @NonNull String getterName) {
        final String space = Indentation.space();
        final String returnCode = Indentation.returnCode();
        final String period = Delimiter.period();
        final String assignment = Operand.assignment();

        final StringBuilder process = new StringBuilder();
        process.append(CURRENT_OBJECT).append(period).append(getterName).append(space).append(assignment).append(space);
        process.append(variableName).append(".get").append(this.toInitialUpperCase(getterName)).append("();")
                .append(returnCode);

        process.setLength(process.length() - returnCode.length());

        return process.toString();
    }

    /**
     * 文字列の上1桁目を大文字に変換して返却します。
     *
     * @param sequence 文字列
     * @return 上1桁目が大文字に変換された文字列
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private String toInitialUpperCase(@NonNull String sequence) {
        return sequence.substring(0, 1).toUpperCase() + sequence.substring(1);
    }
}