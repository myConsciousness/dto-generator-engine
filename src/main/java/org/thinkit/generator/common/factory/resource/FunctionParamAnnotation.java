/**
 * Project Name : generator-commons<br>
 * File Name : FunctionParamAnnotation.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/05/29<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.common.factory.resource;

import org.thinkit.generator.common.factory.Component;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 * プログラムリソースにおける引数のアノテーション情報を抽象化した抽象クラスです。<br>
 * この抽象クラスでは引数のアノテーション定義に必要な情報を保持します。<br>
 * この抽象クラスを継承する具象クラスは必ず {@link Component#createResource()} を実装してください。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 *
 * @see Component
 * @see Component#createResource()
 */
@ToString
@EqualsAndHashCode
public abstract class FunctionParamAnnotation implements Component {

    /**
     * 変数名
     */
    @NonNull
    @Getter(AccessLevel.PROTECTED)
    private String variableName = "";

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
    private FunctionParamAnnotation() {
    }

    /**
     * コンストラクタ
     *
     * @param variableName 変数名
     * @param description  説明
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    protected FunctionParamAnnotation(String variableName, String description) {
        this.variableName = variableName;
        this.description = description;
    }
}