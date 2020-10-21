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
 * プログラムリソースにおける継承を抽象化した抽象クラスです。<br>
 * この抽象クラスでは継承の定義に必要な情報を保持します。<br>
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
public abstract class Inheritance implements Component {

    /**
     * 継承名
     */
    @Getter(AccessLevel.PROTECTED)
    private String literal;

    /**
     * 総称型
     */
    @Getter(AccessLevel.PROTECTED)
    private Generics generics;

    /**
     * デフォルトコンストラクタ
     */
    @SuppressWarnings("unused")
    private Inheritance() {
    }

    /**
     * コンストラクタ
     *
     * @param literal  継承名
     * @param generics 総称型
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    protected Inheritance(@NonNull String literal, @NonNull Generics generics) {
        this.literal = literal;
        this.generics = generics;
    }
}
