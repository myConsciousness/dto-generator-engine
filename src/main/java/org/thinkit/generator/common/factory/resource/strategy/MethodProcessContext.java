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
 * メソッド処理のストラテジーを判断するコンテキストを抽象化したクラスです。
 * <p>
 * {#link ConstructorProcessContext} を継承した具象クラスは {@link #toProcess(String)}
 * を実装する必要があります。
 *
 * @author 1.0
 * @since 1.0
 * @version 1.0
 */
@ToString
@EqualsAndHashCode
public abstract class MethodProcessContext {

    /**
     * メソッドストラテジー
     */
    @Getter(AccessLevel.PROTECTED)
    private MethodProcessStrategy methodProcessStrategy = null;

    /**
     * デフォルトコンストラクタ
     */
    @SuppressWarnings("unused")
    private MethodProcessContext() {
    }

    /**
     * コンストラクタ
     *
     * @param methodProcessStrategy メソッド処理ストラテジー
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    protected MethodProcessContext(@NonNull MethodProcessStrategy methodProcessStrategy) {
        this.methodProcessStrategy = methodProcessStrategy;
    }

    /**
     * 引数として渡された情報を基にメソッド処理を生成し文字列として返却します。
     *
     * @param variableName 変数名
     * @return 渡された引数情報を基に生成されたメソッド処理
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public abstract String toProcess(@NonNull String variableName);
}