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
 * プログラムリソースにおけるフィールドを抽象化した抽象クラスです<br>
 * この抽象クラスではフィールドを生成するために必要な情報を保持します。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 *
 * @see Component
 */
@ToString
@EqualsAndHashCode
public abstract class Field implements Component {

    /**
     * フィールドの定義
     */
    @NonNull
    @Getter(AccessLevel.PROTECTED)
    private FieldDefinition fieldDefinition;

    /**
     * フィールドの説明
     */
    @NonNull
    @Getter(AccessLevel.PROTECTED)
    private Description description;

    /**
     * デフォルトコンストラクタ
     */
    @SuppressWarnings("unused")
    private Field() {
    }

    /**
     * 引数として渡された情報を基に {@link Field} クラスの新しいインスタンスを生成します。
     *
     * @param fieldDefinition フィールド定義
     * @param description     フィールドの説明
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    protected Field(@NonNull FieldDefinition fieldDefinition, @NonNull Description description) {
        this.fieldDefinition = fieldDefinition;
        this.description = description;
    }
}