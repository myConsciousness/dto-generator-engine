/**
 * Project Name : Generator<br>
 * File Name : Resource.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/05/29<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.rule.factory.resource;

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
 * この抽象クラスを継承する具象クラスは必ず{@link #createResource()}を実装してください。
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
     * @exception NullPointerException 引数として{@code null}が渡された場合
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
     * @exception NullPointerException 引数として{@code null}が渡された場合
     */
    public void add(@NonNull Description description) {
        this.field.add(description);
    }

    /**
     * フィールド定義をフィールドへ追加します。
     *
     * @param fieldDefinition フィールド定義
     *
     * @exception NullPointerException 引数として{@code null}が渡された場合
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
     * リソースを生成し文字列表現として返却する処理を定義するメソッドです。<br>
     * このメソッドを定義している{@link Component}を継承した具象サブクラスは<br>
     * 必ずこのメソッドを実装する必要があります。 <br>
     * <br>
     * この抽象メソッドはこの抽象クラスを継承する部品の特性に応じて実装してください。<br>
     * 例えば、この抽象クラスをJavaリソースにおけるフィールド定義を管理する部品が継承した場合、<br>
     * その部品クラスはフィールド定義を文字列として生成する処理を実装する必要があります。
     *
     * @return 各要素を管理する部品で生成されたJavaリソース
     */
    public abstract String createResource();
}