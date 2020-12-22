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
@EqualsAndHashCode
public abstract class Resource {

    /**
     * 著作権
     */
    @Getter(AccessLevel.PROTECTED)
    private Copyright copyright;

    /**
     * パッケージ名
     */
    @Getter(AccessLevel.PROTECTED)
    private Package packageName;

    /**
     * リソース名
     */
    @Getter(AccessLevel.PROTECTED)
    private String resourceName;

    /**
     * クラスの説明
     */
    @Getter(AccessLevel.PROTECTED)
    private ClassDescription classDescription;

    /**
     * 依存パッケージ
     */
    @Getter(AccessLevel.PROTECTED)
    private List<DependentPackage> dependentPackages = new ArrayList<>(0);

    /**
     * 継承リスト
     */
    @Getter(AccessLevel.PROTECTED)
    private List<Inheritance> inheritances = new ArrayList<>(0);

    /**
     * インターフェースリスト
     */
    @Getter(AccessLevel.PROTECTED)
    private List<Interface> interfaces = new ArrayList<>(0);

    /**
     * 列挙定数リスト
     */
    @Getter(AccessLevel.PROTECTED)
    private List<Enumeration> enumerations = new ArrayList<>(0);

    /**
     * フィールドリスト
     */
    @Getter(AccessLevel.PROTECTED)
    private List<Field> fields = new ArrayList<>(0);

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
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    protected Resource(@NonNull Copyright copyright, @NonNull Package packageName,
            @NonNull ClassDescription classDescription, @NonNull String resourceName) {
        this.copyright = copyright;
        this.packageName = packageName;
        this.classDescription = classDescription;
        this.resourceName = resourceName;
    }

    /**
     * 依存パッケージ定義を追加します。
     *
     * @param dependentPackage 依存パッケージ
     * @return 自分自身のインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public Resource add(@NonNull DependentPackage dependentPackage) {
        this.dependentPackages.add(dependentPackage);
        return this;
    }

    /**
     * 継承定義を追加します。
     *
     * @param inheritance 継承
     * @return 自分自身のインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public Resource add(@NonNull Inheritance inheritance) {
        this.inheritances.add(inheritance);
        return this;
    }

    /**
     * インターフェース定義を追加します。
     *
     * @param _interface インターフェース
     * @return 自分自身のインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public Resource add(@NonNull Interface _interface) {
        this.interfaces.add(_interface);
        return this;
    }

    /**
     * Enum定数定義を追加します。
     *
     * @param enumeration Enum定数定義
     * @return 自分自身のインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public Resource add(@NonNull Enumeration enumeration) {
        this.enumerations.add(enumeration);
        return this;
    }

    /**
     * フィールド定義を追加します。
     *
     * @param enumeration フィールド定義
     * @return 自分自身のインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public Resource add(@NonNull Field field) {
        this.fields.add(field);
        return this;
    }

    /**
     * コンストラクタ定義を追加します。
     *
     * @param constructor コンストラクタ定義
     * @return 自分自身のインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public Resource add(@NonNull Constructor constructor) {
        this.constructors.add(constructor);
        return this;
    }

    /**
     * メソッド定義を追加します。
     *
     * @param method メソッド定義
     * @return 自分自身のインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public Resource add(@NonNull Method method) {
        this.methods.add(method);
        return this;
    }

    /**
     * リソースを生成し文字列表現として返却する処理を定義するメソッドです。
     *
     * @return Javaリソース
     */
    public abstract String createResource();
}
