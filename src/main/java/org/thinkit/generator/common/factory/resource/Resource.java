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

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 * プログラムリソースを抽象化した抽象クラスです。<br>
 * この抽象クラスではプログラムリソース定義に必要な情報を保持します。<br>
 * この抽象クラスを継承する具象クラスは必ず {@link #createResource()} を実装してください。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@ToString
@EqualsAndHashCode(callSuper = false)
public abstract class Resource {

    /**
     * 著作権
     */
    @NonNull
    @Getter(AccessLevel.PROTECTED)
    private Copyright copyright = null;

    /**
     * パッケージ名
     */
    @NonNull
    @Getter(AccessLevel.PROTECTED)
    private String packageName = "";

    /**
     * リソース名
     */
    @NonNull
    @Getter(AccessLevel.PROTECTED)
    private String resourceName = "";

    /**
     * クラスの説明
     */
    @NonNull
    @Getter(AccessLevel.PROTECTED)
    private ClassDescription classDescription = null;

    /**
     * フィールド
     */
    @NonNull
    @Getter(AccessLevel.PROTECTED)
    private Field field = null;

    /**
     * コンストラクタリスト
     */
    @Getter(AccessLevel.PROTECTED)
    private List<Constructor> constructors = new ArrayList<>(0);

    /**
     * メソッドリスト
     */
    @Getter(AccessLevel.PROTECTED)
    private List<Method> methods = new ArrayList<>(0);

    /**
     * コンストラクタ
     *
     * @param copyright        著作権
     * @param packageName      パッケージ名
     * @param classDescription クラスの説明
     * @param resourceName     リソース名
     * @param field            フィールド定義
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    protected Resource(Copyright copyright, String packageName, ClassDescription classDescription, String resourceName,
            Field field) {
        this.copyright = copyright;
        this.packageName = packageName;
        this.classDescription = classDescription;
        this.resourceName = resourceName;
        this.field = field;
    }

    /**
     * 説明定義をフィールドへ追加します。
     *
     * @param description 説明定義
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public void add(@NonNull Description description) {
        this.field.add(description);
    }

    /**
     * フィールド定義をフィールドへ追加します。
     *
     * @param fieldDefinition フィールド定義
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public void add(@NonNull FieldDefinition fieldDefinition) {
        this.field.add(fieldDefinition);
    }

    /**
     * コンストラクタ定義を追加します。
     *
     * @param constructor コンストラクタ定義
     */
    public void add(Constructor constructor) {
        this.constructors.add(constructor);
    }

    /**
     * メソッド定義を追加します。
     *
     * @param method メソッド定義
     */
    public void add(Method method) {
        this.methods.add(method);
    }

    /**
     * リソースを生成し文字列表現として返却する処理を定義するメソッドです。
     *
     * @return Javaリソース
     */
    public abstract String createResource();
}