/**
 * Project Name : Generator<br>
 * File Name : DtoRequiredConstructorStrategy.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/06/08<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.rule.factory.strategy.dtogenerator;

import java.util.List;

import org.thinkit.common.catalog.Delimiter;
import org.thinkit.common.catalog.Indentation;
import org.thinkit.generator.rule.factory.resource.Parameter;
import org.thinkit.generator.rule.factory.resource.Process;
import org.thinkit.generator.rule.factory.strategy.resource.ConstructorStrategy;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

/**
 * 必須引数有りコンストラクタ定義を生成する際に使用するストラテジを実装した具象クラスです。<br>
 * <br>
 * 以下の機能を提供しています。<br>
 * {@link #toParameter(Parameter)}<br>
 * {@link #toProcess(Process)}<br>
 * 
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 * 
 * @see #toParameter(Parameter)
 * @see #toProcess(Process)
 */
@ToString
@EqualsAndHashCode(callSuper = false)
public class DtoRequiredConstructorStrategy extends ConstructorStrategy {

    /**
     * 処理のインデント数
     */
    private static final int PROCESS_INDENT_COUNT = 8;

    /**
     * デフォルトコンストラクタ
     */
    public DtoRequiredConstructorStrategy() {
    }

    @Override
    public String toParameter(@NonNull List<Parameter> parameters) {
        if (parameters.isEmpty()) {
            return "";
        }

        final StringBuilder sb = new StringBuilder();
        final String space = Indentation.space();
        final String commma = Delimiter.commma();

        for (Parameter parameter : parameters) {
            sb.append(parameter.createResource()).append(commma).append(space);
        }

        sb.setLength(sb.length() - (commma.length() + space.length()));

        return sb.toString();
    }

    @Override
    public String toProcess(@NonNull List<Process> processes) {
        if (processes.isEmpty()) {
            return "";
        }

        final StringBuilder sb = new StringBuilder();
        final String indentSpaces = Indentation.getIndentSpaces(PROCESS_INDENT_COUNT);
        final String returnCode = Indentation.returnCode();

        for (Process process : processes) {
            sb.append(indentSpaces).append(process.createResource()).append(returnCode);
        }

        sb.setLength(sb.length() - returnCode.length());
        return sb.toString();
    }
}