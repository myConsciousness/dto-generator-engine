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

import org.thinkit.generator.common.factory.Component;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 * プログラムリソースにおける引数を抽象化した抽象クラスです。<br>
 * この抽象クラスでは引数定義に必要な情報を保持します。<br>
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
public abstract class Parameter implements Component {

    /**
     * データ型
     */
    @Getter(AccessLevel.PROTECTED)
    private String dataType;

    /**
     * 変数名
     */
    @Getter(AccessLevel.PROTECTED)
    private String variableName;

    /**
     * デフォルトコンストラクタ
     */
    @SuppressWarnings("unused")
    private Parameter() {
    }

    /**
     * コンストラクタ
     *
     * @param dataType     データ型
     * @param variableName 変数名
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    protected Parameter(@NonNull String dataType, @NonNull String variableName) {
        this.dataType = dataType;
        this.variableName = variableName;
    }
}