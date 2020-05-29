/**
 * Project Name : Generator<br>
 * File Name : Function.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/05/26<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.rule.factory.resource;

import java.util.List;
import java.util.Objects;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.util.ArrayList;

/**
 * プログラムリソースにおける関数を抽象化した抽象クラスです。<br>
 * この抽象クラスでは関数の定義に必要な情報を保持します。<br>
 * この抽象クラスを継承する具象クラスは必ず{@link Component#createResource()}を実装してください。
 * 
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 * @see Component
 * @see Component#createResource()
 */
@ToString
@EqualsAndHashCode(callSuper = false)
public abstract class Function implements Component {

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
     * @param functionDescription 関数の説明
     * @exception NullPointerException 引数として{@code null}が渡された場合
     */
    public Function(FunctionDescription functionDescription) {
        this.functionDescription = functionDescription;
    }

    /**
     * 関数の引数を追加します。<br>
     * 引数として{@code null}が渡された場合は実行時に必ず失敗します。
     * 
     * @param parameter 関数の引数
     * @exception NullPointerException 引数として{@code null}が渡された場合
     */
    public void add(Parameter parameter) {
        Objects.requireNonNull(parameter);
        this.parameters.add(parameter);
    }

    /**
     * 関数の処理を追加します。<br>
     * 引数として{@code null}が渡された場合は実行時に必ず失敗します。
     * 
     * @param process 関数の処理
     * @exception NullPointerException 引数として{@code null}が渡された場合
     */
    public void add(Process process) {
        Objects.requireNonNull(process);
        this.processes.add(process);
    }
}