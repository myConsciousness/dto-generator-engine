/**
 * Project Name : Generator<br>
 * File Name : DtoConstructor.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/06/04<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */
package org.thinkit.generator.rule.factory.dtofactory;

import org.thinkit.generator.rule.factory.strategy.dtogenerator.DtoConstructorContext;
import org.thinkit.generator.rule.factory.strategy.dtogenerator.DtoCopyingConstructorStrategy;
import org.thinkit.generator.rule.factory.strategy.dtogenerator.DtoDefaultConstructorStrategy;
import org.thinkit.generator.rule.factory.strategy.dtogenerator.DtoRequiredConstructorStrategy;
import org.thinkit.generator.rule.factory.strategy.resource.ConstructorContext;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.thinkit.common.catalog.Brace;
import org.thinkit.generator.rule.factory.resource.Constructor;
import org.thinkit.generator.rule.factory.resource.FunctionDescription;

import org.thinkit.common.catalog.Identifier;
import org.thinkit.common.catalog.Indentation;
import org.thinkit.common.catalog.Parenthesis;

/**
 * DTOクラスのコンストラクタを生成する具象クラスです。<br>
 * DTOに必要なコンストラクタを生成する処理を{@link Component#createResource()}に実装します。
 * 
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 * @see Component
 * @see Component#createResource()
 * @see Constructor
 */
@ToString
@EqualsAndHashCode(callSuper = false)
final class DtoConstructor extends Constructor {

    /**
     * コンストラクタ
     * 
     * @param constructorName     コンストラクタ名
     * @param functionDescription 関数の説明
     * @exception NullPointerException 引数として{@code null}が渡された場合
     */
    public DtoConstructor(String constructorName, FunctionDescription functionDescription) {
        super(constructorName, functionDescription);
    }

    @Override
    public String createResource() {
        final String indentSpaces = Indentation.getIndentSpaces();
        final String space = Indentation.space();
        final String returnCode = Indentation.returnCode();

        final ConstructorContext constructorContext = this.getConstructorContext();
        final StringBuilder constructor = new StringBuilder();

        constructor.append(super.getFunctionDescription().createResource()).append(returnCode);
        constructor.append(indentSpaces).append(Identifier.PUBLIC.toIdentifier()).append(space)
                .append(super.getFunctionName());

        constructor.append(Parenthesis.start()).append(constructorContext.toParameter(super.getParameters()))
                .append(Parenthesis.end()).append(space).append(Brace.start()).append(returnCode);
        constructor.append(constructorContext.toProcess(super.getProcesses())).append(returnCode);

        constructor.append(Indentation.getIndentSpaces()).append(Brace.end());

        return constructor.toString();
    }

    /**
     * 設定された{@link ConstructorState}の値を基にコンストラクタ定義を生成する際のコンテキストを取得し返却します。<br>
     * 以下のストラテジーを使用します。<br>
     * {@link DtoDefaultConstructorStrategy}<br>
     * {@link DtoRequiredConstructorStrategy}<br>
     * {@link DtoCopyingConstructorStrategy}<br>
     * 
     * @return コンストラクタ定義を生成する際に使用するコンテキスト
     */
    private ConstructorContext getConstructorContext() {

        switch (super.getConstructorState()) {
            case REQUIRED:
                return new DtoConstructorContext(new DtoRequiredConstructorStrategy());
            case COPYING:
                return new DtoConstructorContext(new DtoCopyingConstructorStrategy());
            default:
                return new DtoConstructorContext(new DtoDefaultConstructorStrategy());
        }
    }
}