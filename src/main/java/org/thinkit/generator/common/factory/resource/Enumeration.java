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

import org.thinkit.generator.common.factory.Component;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 * プログラムリソースにおける列挙子を抽象化した抽象クラスです。
 * <p>
 * この抽象クラスでは列挙子の定義に必要な情報を定義します。この抽象クラスを継承する具象クラスは必ず
 * {@link Component#createResource()} を実装してください。
 * <p>
 * 列挙子に固有の値を設定したい場合は {@link Enumeration} クラスのインスタンスを生成した後に {@link #add(Object)}
 * メソッドを呼び出してください。
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
public abstract class Enumeration implements Component {

    /**
     * 列挙定数名
     */
    @Getter(AccessLevel.PROTECTED)
    private String literal;

    /**
     * 列挙子の値リスト
     */
    @Getter(AccessLevel.PROTECTED)
    private List<Object> values = new ArrayList<>(0);

    /**
     * デフォルトコンストラクタ
     */
    @SuppressWarnings("unused")
    private Enumeration() {
    }

    /**
     * 引数として渡された情報を基に {@link Enumeration} クラスの新しいインスタンスを生成し返却します。
     *
     * @param literal 列挙定数名
     */
    protected Enumeration(@NonNull String literal) {
        this.literal = literal;
    }

    /**
     * 引数として渡された値を列挙子に設定する値として追加します。この {@link #add(Object)}
     * メソッドは自分自身のインスタンスを返却するため、メソッドチェーンの形式で後続の処理を行うことが可能です。
     *
     * @param value 列挙子固有の値をとして設定する任意の型の値
     * @return 自分自身のインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public Enumeration add(@NonNull Object value) {
        this.values.add(value);
        return this;
    }
}
