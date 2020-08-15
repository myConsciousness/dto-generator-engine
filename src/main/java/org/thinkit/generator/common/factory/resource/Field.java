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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.thinkit.common.exception.LogicException;
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
     * フィールドの説明
     */
    @NonNull
    @Getter(AccessLevel.PROTECTED)
    private List<Description> descriptions = new ArrayList<>(0);

    /**
     * フィールドの定義
     */
    @NonNull
    @Getter(AccessLevel.PROTECTED)
    private List<FieldDefinition> fieldDefinitions = new ArrayList<>(0);

    /**
     * デフォルトコンストラクタ
     */
    protected Field() {
    }

    /**
     * フィールドの説明を追加します。<br>
     * 引数として {@code null} が渡された場合は実行時に必ず失敗します。
     *
     * @param description フィールドの説明
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public void add(Description description) {
        Objects.requireNonNull(description);
        this.descriptions.add(description);
    }

    /**
     * フィールドの定義を追加します。<br>
     * 引数として {@code null} が渡された場合は実行時に必ず失敗します。
     *
     * @param fieldDefinition フィールドの定義
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public void add(FieldDefinition fieldDefinition) {
        Objects.requireNonNull(fieldDefinition);
        this.fieldDefinitions.add(fieldDefinition);
    }

    /**
     * 説明とフィール定義の整合性を確認します。<br>
     * 設定された説明とフィールド定義の個数に不整合が検知された場合は実行時に必ず失敗します。
     *
     * @throws LogicException 設定された説明とフィールド定義の個数に不整合が存在する場合
     */
    protected void validate() {
        final int descriptionsSize = descriptions.size();
        final int fieldDefinitionsSize = fieldDefinitions.size();

        if (descriptionsSize != fieldDefinitionsSize) {
            throw new LogicException("detected an inconsistency in the number of descriptions and field definitions."
                    + String.format("%s descriptions but %s field definitions were setteled.", descriptionsSize,
                            fieldDefinitionsSize));
        }
    }
}