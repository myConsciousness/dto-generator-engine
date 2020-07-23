/**
 * Project Name : generator-commons<br>
 * File Name : Field.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/05/26<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.common.factory.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.thinkit.common.exception.LogicException;

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
 * @see Component
 */
@ToString
@EqualsAndHashCode(callSuper = false)
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
     * 引数として{@code null}が渡された場合は実行時に必ず失敗します。
     *
     * @param description フィールドの説明
     * @exception NullPointerException 引数として{@code null}が渡された場合
     */
    public void add(Description description) {
        Objects.requireNonNull(description);
        this.descriptions.add(description);
    }

    /**
     * フィールドの定義を追加します。<br>
     * 引数として{@code null}が渡された場合は実行時に必ず失敗します。
     *
     * @param fieldDefinition フィールドの定義
     * @exception NullPointerException 引数として{@code null}が渡された場合
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