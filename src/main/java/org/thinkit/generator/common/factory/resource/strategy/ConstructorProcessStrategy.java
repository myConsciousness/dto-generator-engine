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

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

/**
 * コンストラクタの処理定義を生成する際のストラテジーを抽象化したクラスです。<br>
 * それぞれ異なるコンストラクタの処理定義を生成する際に {@link ConstructorProcessStrategy}
 * を継承した具象クラスを定義してください。
 * <p>
 * {@link ConstructorProcessStrategy} を継承した場合は以下の抽象メソッドを必ず実装する必要があります。<br>
 * {@link #toConstructorProcess(String, String)} <br>
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 *
 * @see #toConstructorProcess(String, String)
 */
@ToString
@EqualsAndHashCode(callSuper = false)
public abstract class ConstructorProcessStrategy {

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
