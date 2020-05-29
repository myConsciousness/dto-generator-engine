/**
 * Project Name : Generator<br>
 * File Name : Parameter.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/05/25<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.rule.factory.resource;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 * プログラムリソースにおける引数を抽象化した抽象クラスです。<br>
 * この抽象クラスでは引数定義に必要な情報を保持します。<br>
 * この抽象クラスを継承する具象クラスは必ず{@link Component#createResource()}を実装してください。
 * 
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 * @see Component
 * @see Component#createResource()
 */
@ToString
@EqualsAndHashCode(callSuper = false)
public abstract class Parameter implements Component {

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
     * デフォルトコンストラクタ
     */
    @SuppressWarnings("unused")
    private Parameter() {
    }

    /**
     * コンストラクタ
     * 
     * @param dataType     データ型
     * @param variableName 変数名
     * @exception NullPointerException 引数として{@code null}が渡された場合
     */
    public Parameter(String dataType, String variableName) {
        this.dataType = dataType;
        this.variableName = variableName;
    }
}