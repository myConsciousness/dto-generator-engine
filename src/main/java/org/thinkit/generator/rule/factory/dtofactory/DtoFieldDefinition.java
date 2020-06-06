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

import org.thinkit.generator.catalog.dtogenerator.DtoAnnotation;
import org.thinkit.generator.rule.factory.resource.FieldDefinition;
import org.thinkit.common.catalog.PrimitiveDataType;
import org.thinkit.common.catalog.Delimiter;
import org.thinkit.common.catalog.Identifier;
import org.thinkit.common.catalog.Indentation;
import org.thinkit.common.catalog.Operand;

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
public final class DtoFieldDefinition extends FieldDefinition {

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
        field.append(Indentation.getIndentSpaces());

        final String dataType = super.getDataType();
        if (PrimitiveDataType.isPrimitive(dataType)) {
            field.append(DtoAnnotation.lombokNonNull()).append(Indentation.returnCode());
        }

        final String space = Indentation.space();

        field.append(Identifier.PRIVATE.toIdentifier()).append(space).append(dataType).append(space);
        field.append(super.getVariableName()).append(space);
        field.append(Operand.assignment()).append(space).append(super.getInitialValue()).append(Delimiter.semicolon());

        return field.toString();
    }
}