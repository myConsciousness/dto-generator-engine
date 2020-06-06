/**
 * Project Name : Generator<br>
 * File Name : DtoProcess.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/06/05<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.rule.factory.dtofactory;

import org.thinkit.common.catalog.Delimiter;
import org.thinkit.common.catalog.Indentation;
import org.thinkit.common.catalog.Operand;
import org.thinkit.generator.rule.factory.resource.Process;

import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * DTOクラスの処理を生成する具象クラスです。<br>
 * DTOに必要な処理を生成する処理を{@link Component#createResource()}に実装します。
 * 
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 * @see Component
 * @see Component#createResource()
 * @see Process
 */
@ToString
@EqualsAndHashCode(callSuper = false)
public final class DtoProcess extends Process {

    /**
     * カレントオブジェクト
     */
    private static final String CURRENT_OBJECT = "this";

    /**
     * コンストラクタ
     * 
     * @param variableName 変数名
     * @exception NulLPointerException 引数として{@code null}が渡された場合
     */
    public DtoProcess(String variableName) {
        super(variableName);
    }

    @Override
    public String createResource() {
        final String variableName = super.getVariableName();
        final String space = Indentation.space();

        final StringBuilder process = new StringBuilder();
        process.append(CURRENT_OBJECT).append(Delimiter.period()).append(variableName).append(space);
        process.append(Operand.assignment()).append(space).append(variableName).append(Delimiter.semicolon());

        return process.toString();
    }
}