/**
 * Project Name : Generator<br>
 * File Name : DtoCopyingConstructorProcessStrategy.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/06/09<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.rule.factory.strategy.dtogenerator;

import org.thinkit.common.catalog.Delimiter;
import org.thinkit.common.catalog.Indentation;
import org.thinkit.common.catalog.Operand;
import org.thinkit.generator.rule.factory.strategy.resource.ConstructorProcessStrategy;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

/**
 * コピーコンストラクタの処理定義を生成する際に使用するストラテジーを実装した具象クラスです。<br>
 * <br>
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
public class DtoCopyingConstructorProcessStrategy extends ConstructorProcessStrategy {

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
     * @exception NullPointerException 引数として{@code null}が渡された場合
     */
    private String toInitialUpperCase(@NonNull String sequence) {
        return sequence.substring(0, 1).toUpperCase() + sequence.substring(1);
    }
}