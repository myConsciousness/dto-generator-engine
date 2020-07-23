/**
 * Project Name : generator-commons<br>
 * File Name : DtoConstructorProcessContext.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/06/09<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.common.factory.dtogenerator.strategy;

import org.thinkit.generator.rule.factory.strategy.resource.ConstructorProcessContext;
import org.thinkit.generator.rule.factory.strategy.resource.ConstructorProcessStrategy;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

/**
 * DTOのコンストラクタ処理定義を生成する際のストラテジーを判断するコンテキストを実装した具象クラスです。<br>
 * {@link ConstructorProcessContext}を実装し、{@link ConstructorProcessStrategy}の処理を委譲しています。<br>
 * <br>
 * 以下の機能を提供しています。<br>
 * {@link #toConstructorProcess(String, String)}<br>
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 *
 * @see #toConstructorProcess(String, String)
 */
@ToString
@EqualsAndHashCode(callSuper = false)
public class DtoConstructorProcessContext extends ConstructorProcessContext {

    /**
     * コンストラクタ
     *
     * @param constructorProcessStrategy コンストラクタ処理ストラテジー
     *
     * @exception NullPointerException 引数として{@code null}が渡された場合
     */
    public DtoConstructorProcessContext(@NonNull ConstructorProcessStrategy constructorProcessStrategy) {
        super(constructorProcessStrategy);
    }

    @Override
    public String toConstructorProcess(@NonNull String variableName, @NonNull String getterName) {
        return super.getConstructorProcessStrategy().toConstructorProcess(variableName, getterName);
    }
}