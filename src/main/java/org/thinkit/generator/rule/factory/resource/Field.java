/**
 * Project Name : Generator<br>
 * File Name : Field.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/05/25<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.rule.factory.resource;

import lombok.Getter;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

/**
 * プログラムリソースにおけるフィールドを抽象化した抽象クラスです。<br>
 * この抽象クラスではフィールド定義に必要な情報を保持します。
 * 
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 * @see Component
 */
@ToString
@EqualsAndHashCode(callSuper = false)
public abstract class Field extends Component {

    /**
     * データ型
     */
    @NonNull
    @Getter(AccessLevel.PROTECTED)
    private String dataType = "";

    /**
     * 変数名
     */
    @NonNull
    @Getter(AccessLevel.PROTECTED)
    private String variableName = "";

    /**
     * 初期値
     */
    @NonNull
    @Getter(AccessLevel.PROTECTED)
    private String initialValue = "";

    /**
     * 説明
     */
    @NonNull
    @Getter(AccessLevel.PROTECTED)
    private String description = "";

    /**
     * デフォルトコンストラクタ
     */
    @SuppressWarnings("unused")
    private Field() {
    }

    /**
     * コンストラクタ
     * 
     * @param dataType     データ型
     * @param variableName 変数名
     * @param initialValue 初期値
     * @param description  説明
     * @exception NullPointerException 引数として{@code null}が渡された場合
     */
    protected Field(String dataType, String variableName, String initialValue, String description) {
        this.dataType = dataType;
        this.variableName = variableName;
        this.initialValue = initialValue;
        this.description = description;
    }
}