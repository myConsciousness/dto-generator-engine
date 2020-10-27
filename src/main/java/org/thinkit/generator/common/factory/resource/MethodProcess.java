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

import org.thinkit.generator.common.catalog.MethodType;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 * プログラムリソースにおけるメソッド処理を抽象化した抽象クラスです。<br>
 * この抽象クラスではメソッド処理の定義に必要な情報を保持します。<br>
 * この抽象クラスを継承する具象クラスは必ず {@link Component#createResource()} を実装してください。
 * <p>
 * 以下のメソッドを使用することによりメソッド種別を変更することができます。<br>
 * {@link #toGetter()} <br>
 * {@link #toSetter()} <br>
 * <p>
 * メソッド種別は以下のメソッドで取得できます。<br>
 * {@link #getMethodType()}
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 *
 * @see Component
 * @see Component#createResource()
 */
@ToString
@EqualsAndHashCode(callSuper = false)
public abstract class MethodProcess extends Process {

    /**
     * メソッド種別
     */
    @Getter(AccessLevel.PROTECTED)
    private MethodType methodType = MethodType.DEFAULT;

    /**
     * コンストラクタ
     *
     * @param variableName 変数名
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    protected MethodProcess(@NonNull String variableName) {
        super(variableName);
    }

    /**
     * メソッド種別を {@link MethodType#GETTER} へ変更します。
     *
     * @return 自分自身のインスタンス
     */
    public MethodProcess toGetter() {
        this.methodType = MethodType.GETTER;
        return this;
    }

    /**
     * メソッド種別を {@link MethodType#SETTER} へ変更します。
     *
     * @return 自分自身のインスタンス
     */
    public MethodProcess toSetter() {
        this.methodType = MethodType.SETTER;
        return this;
    }
}
