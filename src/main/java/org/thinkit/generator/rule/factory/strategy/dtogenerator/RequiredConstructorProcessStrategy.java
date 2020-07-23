/**
 * Project Name : generator-commons<br>
 * File Name : RequiredConstructorProcessStrategy.java<br>
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
 * 必須引数有りのコンストラクタの処理定義を生成する際に使用するストラテジーを実装した具象クラスです。<br>
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
public class RequiredConstructorProcessStrategy extends ConstructorProcessStrategy {

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