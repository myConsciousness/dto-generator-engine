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

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 * プログラムリソースにおける関数の説明を抽象化した抽象クラスです。<br>
 * この抽象クラスでは関数の説明定義に必要な情報を保持します。<br>
 * この抽象クラスを継承する具象クラスは必ず {@link Component#createResource()} を実装してください。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 *
 * @see Component
 * @see Description
 * @see Component#createResource()
 */
@ToString
@EqualsAndHashCode(callSuper = false)
public abstract class FunctionDescription extends Description {

    /**
     * 引数の変数名
     */
    @NonNull
    @Getter(AccessLevel.PROTECTED)
    private List<DescriptionTag> descriptionTags = new ArrayList<>(0);

    /**
     * コンストラクタ
     *
     * @param description 説明
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    protected FunctionDescription(String description) {
        super(description);
    }

    /**
     * 引数のアノテーション情報を追加します。<br>
     * 引数として {@code null} が渡された場合は実行時に必ず失敗します。
     *
     * @param descriptionTag 引数のアノテーション情報
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public void add(DescriptionTag descriptionTag) {
        Objects.requireNonNull(descriptionTag);
        this.descriptionTags.add(descriptionTag);
    }

    /**
     * 関数の引数アノテーションが存在するか判定します。<br>
     * 関数の引数アノテーションが存在する場合は {@code true} 、それ以外は {@code false} を返却します。
     *
     * @return 関数の引数アノテーションが存在する場合は {@code true} 、それ以外は {@code false}
     */
    protected boolean hasAnnotation() {
        return !this.descriptionTags.isEmpty();
    }
}