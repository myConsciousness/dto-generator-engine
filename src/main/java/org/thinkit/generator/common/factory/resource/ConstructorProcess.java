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

import org.thinkit.generator.common.catalog.ConstructorState;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 * プログラムリソースにおけるコンストラクタ処理を抽象化した抽象クラスです。<br>
 * この抽象クラスではコンストラクタ処理の定義に必要な情報を保持します。<br>
 * この抽象クラスを継承する具象クラスは必ず {@link Component#createResource()} を実装してください。
 * <p>
 * 以下のメソッドを使用することによりコンストラクタの状態を変更することができます。<br>
 * {@link #toDefault()} <br>
 * {@link #toRequired()} <br>
 * {@link #toCopying()} <br>
 * <p>
 * コンストラクタの状態は以下のメソッドで取得できます。<br>
 * {@link #getConstructorState()}
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
     * 初期値は {@link ConstructorState#DEFAULT} が設定されています。
     */
    @Getter(AccessLevel.PROTECTED)
    private ConstructorState constructorState = ConstructorState.DEFAULT;

    /**
     * ゲッター名
     */
    @Getter(AccessLevel.PROTECTED)
    private String getterName;

    /**
     * コンストラクタ
     *
     * @param variableName 変数名
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    protected ConstructorProcess(@NonNull String variableName) {
        super(variableName);
    }

    /**
     * コンストラクタ
     *
     * @param variableName 変数名
     * @param getterName   ゲッター名
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    protected ConstructorProcess(@NonNull String variableName, @NonNull String getterName) {
        super(variableName);
        this.getterName = getterName;
    }

    /**
     * コンストラクタ状態をデフォルトコンストラクタに変更します。<br>
     * {@link Constructor} のインスタンス生成時では、<br>
     * 初期値として {@link ConstructorState#DEFAULT} が設定されているため、<br>
     * {@link #toDefault()} の呼び出しは必要ありません。
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