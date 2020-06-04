/**
 * Project Name : Generator<br>
 * File Name : DtoConstructor.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/06/04<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.rule.factory.dtofactory;

import java.util.List;

import org.thinkit.generator.rule.factory.resource.Parameter;
import org.thinkit.generator.rule.factory.resource.Process;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.thinkit.generator.rule.factory.resource.Function;
import org.thinkit.generator.rule.factory.resource.FunctionDescription;

/**
 * DTOクラスのコンストラクタを生成する具象クラスです。<br>
 * DTOに必要なコンストラクタを生成する処理を{@link Component#createResource()}に実装します。
 * 
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 * @see Component
 * @see Component#createResource()
 * @see Function
 */
@ToString
@EqualsAndHashCode(callSuper = false)
public class DtoConstructor extends Function {

    /**
     * カンマ
     */
    private static final String COMMMA = ",";

    /**
     * publicのアクセス修飾子
     */
    private static final String IDENTIFIER_PUBLIC = "public";

    /**
     * 左括弧
     */
    private static final String PARENTHESIS_START = "(";

    /**
     * 右括弧
     */
    private static final String PARENTHESIS_END = ")";

    /**
     * 右ブレース
     */
    private static final String BRACE_START = "{";

    /**
     * 左ブレース
     */
    private static final String BRACE_END = "}";

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
        final FunctionDescription functionDescription = super.getFunctionDescription();

        final StringBuilder constructor = new StringBuilder();
        final String indentSpaces = Indentation.getIndentSpaces();
        final String indentSpace = Indentation.getSpace();
        final String indentReturn = Indentation.getReturn();

        constructor.append(functionDescription.createResource()).append(indentReturn);

        constructor.append(indentSpaces).append(IDENTIFIER_PUBLIC).append(indentSpace).append(super.getFunctionName());
        constructor.append(PARENTHESIS_START).append(this.toParameter()).append(PARENTHESIS_END).append(indentSpace)
                .append(BRACE_START).append(indentReturn);
        constructor.append(this.toProcess());
        constructor.append(BRACE_END);

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
        for (Parameter parameter : parameters) {
            sb.append(parameter.createResource()).append(COMMMA).append(Indentation.getSpace());
        }

        sb.setLength(sb.length() - 1);
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
        final String indentReturn = Indentation.getReturn();

        for (Process process : processes) {
            sb.append(indentSpaces).append(process.createResource()).append(indentReturn);
        }

        sb.setLength(sb.length() - indentReturn.length());
        return sb.toString();
    }
}