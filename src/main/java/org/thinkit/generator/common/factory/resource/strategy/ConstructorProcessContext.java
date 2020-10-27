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

package org.thinkit.generator.common.factory.resource.strategy;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 * コンストラクタの処理定義をする際のストラテジーを判断するコンテキストを抽象化したクラスです。<br>
 * {#link ConstructorProcessContext} を継承した具象クラスは以下の抽象メソッドを実装する必要があります。<br>
 * {@link #toConstructorProcess(String, String)} <br>
 *
 * @author 1.0
 * @since 1.0
 * @version 1.0
 *
 * @see #toConstructorProcess(String, String)
 */
@ToString
@EqualsAndHashCode
public abstract class ConstructorProcessContext {

    /**
     * コンストラクタストラテジー
     */
    @Getter(AccessLevel.PROTECTED)
    private ConstructorProcessStrategy constructorProcessStrategy = null;

    /**
     * デフォルトコンストラクタ
     */
    @SuppressWarnings("unused")
    private ConstructorProcessContext() {
    }

    /**
     * コンストラクタ
     *
     * @param constructorProcessStrategy コンストラクタ処理ストラテジー
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    protected ConstructorProcessContext(@NonNull ConstructorProcessStrategy constructorProcessStrategy) {
        this.constructorProcessStrategy = constructorProcessStrategy;
    }

    /**
     * 引数として渡された情報を基にコンストラクタ処理定義を生成し文字列として返却する処理を定義する抽象メソッドです。
     *
     * @param variableName 変数名
     * @param getterName   ゲッター名
     * @return 渡された引数情報を基に生成されたコンストラクタ処理定義
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public abstract String toConstructorProcess(@NonNull String variableName, @NonNull String getterName);
}