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

package org.thinkit.generator.common.factory.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.thinkit.common.catalog.Delimiter;
import org.thinkit.common.catalog.Indentation;
import org.thinkit.generator.common.factory.Component;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 * プログラムリソースにおける関数を抽象化した抽象クラスです。<br>
 * この抽象クラスでは関数の定義に必要な情報を保持します。<br>
 * この抽象クラスを継承する具象クラスは必ず {@link Component#createResource()} を実装してください。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 *
 * @see Component
 * @see Component#createResource()
 */
@ToString
@EqualsAndHashCode
public abstract class Function implements Component {

    @NonNull
    @Getter(AccessLevel.PROTECTED)
    private String functionName = "";

    /**
     * 関数の説明
     */
    @NonNull
    @Getter(AccessLevel.PROTECTED)
    private FunctionDescription functionDescription = null;

    /**
     * 引数リスト
     */
    @NonNull
    @Getter(AccessLevel.PROTECTED)
    private List<Parameter> parameters = new ArrayList<>(0);

    /**
     * 処理リスト
     */
    @NonNull
    @Getter(AccessLevel.PROTECTED)
    private List<Process> processes = new ArrayList<>(0);

    /**
     * デフォルトコンストラクタ
     */
    @SuppressWarnings("unused")
    private Function() {
    }

    /**
     * コンストラクタ
     *
     * @param functionName        機能名
     * @param functionDescription 関数の説明
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    protected Function(String functionName, FunctionDescription functionDescription) {
        this.functionName = functionName;
        this.functionDescription = functionDescription;
    }

    /**
     * 関数の引数を追加します。<br>
     * 引数として {@code null} が渡された場合は実行時に必ず失敗します。
     *
     * @param parameter 関数の引数
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public void add(Parameter parameter) {
        Objects.requireNonNull(parameter);
        this.parameters.add(parameter);
    }

    /**
     * 関数の処理を追加します。<br>
     * 引数として {@code null} が渡された場合は実行時に必ず失敗します。
     *
     * @param process 関数の処理
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public void add(Process process) {
        Objects.requireNonNull(process);
        this.processes.add(process);
    }

    /**
     * {@link Parameter} で設定された引数情報を文字列表現として返却します。<br>
     * 引数情報が存在しない場合は必ず空文字列を返却します。
     *
     * @return 引数の文字列表現
     */
    protected String getParameter() {

        final List<Parameter> parameters = this.getParameters();

        if (parameters.isEmpty()) {
            return "";
        }

        final StringBuilder sb = new StringBuilder();
        final String space = Indentation.space();
        final String commma = Delimiter.comma();

        parameters.forEach(parameter -> {
            sb.append(parameter.createResource()).append(commma).append(space);
        });

        sb.setLength(sb.length() - (commma.length() + space.length()));

        return sb.toString();
    }

    /**
     * {@link Process} で設定された処理情報を文字列表現として返却します。<br>
     * 処理情報が存在しない場合は必ず空文字列を返却します。
     *
     * @return 処理の文字列表現
     */
    protected String getProcess() {

        final List<Process> processes = this.getProcesses();

        if (processes.isEmpty()) {
            return "";
        }

        final StringBuilder sb = new StringBuilder();

        processes.forEach(process -> {
            sb.append(process.createResource());
        });

        return sb.toString();
    }
}