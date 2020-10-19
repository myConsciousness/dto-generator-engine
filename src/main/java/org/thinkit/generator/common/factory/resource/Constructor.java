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

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

/**
 * プログラムリソースにおけるコンストラクタを抽象化した抽象クラスです。<br>
 * この抽象クラスではコンストラクタ定義に必要な情報を保持します。<br>
 * この抽象クラスを継承する具象クラスは必ず {@link Component#createResource()} を実装してください。<br>
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
public abstract class Constructor extends Function {

    /**
     * コンストラクタ
     *
     * @param functionName        機能名
     * @param functionDescription 関数の説明
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    protected Constructor(String functionName, FunctionDescription functionDescription) {
        super(functionName, functionDescription);
    }

    /**
     * コンストラクタの説明定義に引数アノテーション定義を追加します。
     *
     * @param functionParamAnnotation 関数の引数アノテーション定義
     */
    public void add(@NonNull DescriptionTag functionParamAnnotation) {
        super.getFunctionDescription().add(functionParamAnnotation);
    }
}