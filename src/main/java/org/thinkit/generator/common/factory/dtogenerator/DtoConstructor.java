/**
 * Project Name : generator-commons<br>
 * File Name : DtoConstructor.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/06/04<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.common.factory.dtogenerator;

import java.util.List;

import org.thinkit.common.catalog.Brace;
import org.thinkit.common.catalog.Delimiter;
import org.thinkit.common.catalog.Identifier;
import org.thinkit.common.catalog.Indentation;
import org.thinkit.common.catalog.Parenthesis;
import org.thinkit.generator.common.factory.resource.Constructor;
import org.thinkit.generator.common.factory.resource.FunctionDescription;
import org.thinkit.generator.common.factory.resource.Parameter;
import org.thinkit.generator.common.factory.resource.Process;

import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * DTOクラスのコンストラクタを生成する具象クラスです。<br>
 * DTOに必要なコンストラクタを生成する処理を{@link Component#createResource()}に実装します。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 * @see Component
 * @see Component#createResource()
 * @see Constructor
 */
@ToString
@EqualsAndHashCode(callSuper = false)
public final class DtoConstructor extends Constructor {

    /**
     * 処理のインデント数
     */
    private static final int PROCESS_INDENT_COUNT = 8;

    /**
     * コンストラクタ
     *
     * @param constructorName     コンストラクタ名
     * @param functionDescription 関数の説明
     * @exception NullPointerException 引数として{@code null}が渡された場合
     */
    public DtoConstructor(String constructorName, FunctionDescription functionDescription) {
        super(constructorName, functionDescription);
    }

    @Override
    public String createResource() {
        final String indentSpaces = Indentation.getIndentSpaces();
        final String space = Indentation.space();
        final String returnCode = Indentation.returnCode();

        final StringBuilder constructor = new StringBuilder();

        constructor.append(super.getFunctionDescription().createResource()).append(returnCode);
        constructor.append(indentSpaces).append(Identifier.PUBLIC.toIdentifier()).append(space)
                .append(super.getFunctionName());
        constructor.append(Parenthesis.start()).append(this.toParameter()).append(Parenthesis.end()).append(space)
                .append(Brace.start()).append(returnCode);
        constructor.append(this.toProcess()).append(returnCode);
        constructor.append(Indentation.getIndentSpaces()).append(Brace.end());

        return constructor.toString();
    }

    /**
     * {@link Parameter}で設定された引数情報を文字列表現として返却します。<br>
     * 引数情報が存在しない場合は必ず空文字列を返却します。
     *
     * @return 引数の文字列表現
     */
    private String toParameter() {
        final List<Parameter> parameters = super.getParameters();

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

    /**
     * {@link Process}で設定された処理情報を文字列表現として返却します。<br>
     * 処理情報が存在しない場合は必ず空文字列を返却します。
     *
     * @return 処理の文字列表現
     */
    private String toProcess() {
        List<Process> processes = super.getProcesses();

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