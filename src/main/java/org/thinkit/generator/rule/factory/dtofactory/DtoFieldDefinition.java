/**
 * Project Name : Generator<br>
 * File Name : DtoFieldDefinition.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/06/04<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.rule.factory.dtofactory;

import org.thinkit.generator.rule.factory.resource.FieldDefinition;

import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * DTOクラスのフィールド定義を生成する具象クラスです。<br>
 * DTOに必要なフィールド定義を生成する処理を{@link Component#createResource()}に実装します。
 * 
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 * @see Component
 * @see Component#createResource()
 * @see FieldDefinition
 */
@ToString
@EqualsAndHashCode(callSuper = false)
public class DtoFieldDefinition extends FieldDefinition {

    /**
     * アクセス修飾子
     */
    private static final String IDENTIFIER = "private";

    /**
     * 代入演算子
     */
    private static final String ASSIGNMENT_OPERATOR = "=";

    /**
     * セミコロン
     */
    private static final String SEMI_COLON = ";";

    /**
     * コンストラクタ
     * 
     * @param dataType     データ型
     * @param variableName 変数名
     * @param initialValue 初期値
     * @exception NullPointerException 引数として{@code null}が渡された場合
     */
    public DtoFieldDefinition(String dataType, String variableName, String initialValue) {
        super(dataType, variableName, initialValue);
    }

    @Override
    public String createResource() {
        final StringBuilder field = new StringBuilder();
        final String indentSpace = Indentation.getSpace();

        field.append(IDENTIFIER).append(indentSpace).append(super.getDataType()).append(indentSpace);
        field.append(super.getVariableName()).append(indentSpace);
        field.append(ASSIGNMENT_OPERATOR).append(indentSpace).append(super.getInitialValue()).append(SEMI_COLON);

        return field.toString();
    }
}