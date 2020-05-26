/**
 * Project Name : Generator<br>
 * File Name : FunctionDescription.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/05/26<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.rule.factory.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 * プログラムリソースにおける関数の説明を抽象化した抽象クラスです。<br>
 * この抽象クラスでは関数の説明定義に必要な情報を保持します。<br>
 * この抽象クラスを継承する具象クラスは必ず{@link Component#createResource()}を実装してください。
 * 
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 * @see Component
 * @see Description
 * @see Component#createResource()
 */
@ToString
@EqualsAndHashCode(callSuper = false)
public abstract class FunctionDescription extends Description {

    /**
     * 引数の変数名
     */
    @NonNull
    @Getter(AccessLevel.PROTECTED)
    private List<String> parameterVariableNames = new ArrayList<>(0);

    /**
     * 引数の説明
     */
    @NonNull
    @Getter(AccessLevel.PROTECTED)
    private List<String> parameterDescriptions = new ArrayList<>(0);

    /**
     * コンストラクタ
     * 
     * @param description 説明
     * @exception NullPointerException 引数として{@code null}が渡された場合
     */
    public FunctionDescription(String description) {
        super(description);
    }

    /**
     * 引数の変数名を追加します。<br>
     * 引数として{@code null}が渡された場合は実行時に必ず失敗します。
     * 
     * @param parameterVariableName 引数の変数名
     * @exception NullPointerException 引数として{@code null}が渡された場合
     */
    public void addParameterVariableName(String parameterVariableName) {
        Objects.requireNonNull(parameterVariableName);
        this.parameterVariableNames.add(parameterVariableName);
    }

    /**
     * 引数の説明を追加します。<br>
     * 引数として{@code null}が渡された場合は実行時に必ず失敗します。
     * 
     * @param parameterDescription 引数の説明
     * @exception NullPointerException 引数として{@code null}が渡された場合
     */
    public void addParameterDescriptions(String parameterDescription) {
        Objects.requireNonNull(parameterDescription);
        this.parameterDescriptions.add(parameterDescription);
    }
}