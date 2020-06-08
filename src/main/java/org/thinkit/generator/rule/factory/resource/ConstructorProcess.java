/**
 * Project Name : Generator<br>
 * File Name : ConstructorProcess.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/06/09<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.rule.factory.resource;

import java.util.ArrayList;
import java.util.List;

import org.thinkit.generator.catalog.ConstructorState;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 * プログラムリソースにおけるコンストラクタ処理を抽象化した抽象クラスです。<br>
 * この抽象クラスではコンストラクタ処理の定義に必要な情報を保持します。<br>
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
public abstract class ConstructorProcess extends Process {

    /**
     * コンストラクタ状態。<br>
     * 初期値は{@link ConstructorState#DEFAULT}が設定されています。
     */
    @Getter(AccessLevel.PROTECTED)
    private ConstructorState constructorState = ConstructorState.DEFAULT;

    /**
     * 変数リスト
     */
    @Getter(AccessLevel.PROTECTED)
    private List<String> variableNames = new ArrayList<>(0);

    /**
     * コンストラクタ
     * 
     * @param variableName 変数名
     * @exception NulLPointerException 引数として{@code null}が渡された場合
     */
    protected ConstructorProcess(String variableName) {
        super(variableName);
    }

    /**
     * 変数名を追加します。
     * 
     * @param variable 変数名
     */
    public void add(@NonNull String variableName) {
        this.variableNames.add(variableName);
    }

    /**
     * コンストラクタ状態をデフォルトコンストラクタに変更します。<br>
     * {@link Constructor}のインスタンス生成時では、<br>
     * 初期値として{@link ConstructorState#DEFAULT}が設定されているため、<br>
     * {@link #toDefault()}の呼び出しは必要ありません。
     * 
     * @return 当オブジェクトのインスタンス
     */
    public ConstructorProcess toDefault() {
        this.constructorState = ConstructorState.DEFAULT;
        return this;
    }

    /**
     * コンストラクタ状態を必須引数有りのコンストラクタに変更します。
     * 
     * @return 当オブジェクトのインスタンス
     */
    public ConstructorProcess toRequired() {
        this.constructorState = ConstructorState.REQUIRED;
        return this;
    }

    /**
     * コンストラクタ状態をコピーコンストラクタに変更します。
     * 
     * @return 当オブジェクトのインスタンス
     */
    public ConstructorProcess toCopying() {
        this.constructorState = ConstructorState.COPYING;
        return this;
    }
}