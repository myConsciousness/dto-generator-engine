/**
 * Project Name : generator-commons<br>
 * File Name : DtoConstructorProcess.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/06/05<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.rule.factory.dtofactory;

import org.thinkit.generator.catalog.ConstructorState;
import org.thinkit.generator.rule.factory.resource.ConstructorProcess;
import org.thinkit.generator.rule.factory.strategy.dtogenerator.DtoConstructorProcessContext;
import org.thinkit.generator.rule.factory.strategy.dtogenerator.DtoCopyingConstructorProcessStrategy;
import org.thinkit.generator.rule.factory.strategy.dtogenerator.DtoDefaultConstructorProcessStrategy;
import org.thinkit.generator.rule.factory.strategy.dtogenerator.DtoRequiredConstructorProcessStrategy;
import org.thinkit.generator.rule.factory.strategy.resource.ConstructorProcessContext;

import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * DTOクラスのコンストラクタ処理を生成する具象クラスです。<br>
 * DTOに必要なコンストラクタ処理を生成する処理を{@link Component#createResource()}に実装します。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 * @see Component
 * @see Component#createResource()
 * @see ConstructorProcess
 */
@ToString
@EqualsAndHashCode(callSuper = false)
final class DtoConstructorProcess extends ConstructorProcess {

    /**
     * コンストラクタ
     *
     * @param variableName 変数名
     * @exception NullPointerException 引数として{@code null}が渡された場合
     */
    public DtoConstructorProcess(String variableName) {
        super(variableName);
    }

    /**
     * コンストラクタ
     *
     * @param variableName 変数名
     * @param getterName   ゲッター名
     * @exception NullPointerException 引数として{@code null}が渡された場合
     */
    public DtoConstructorProcess(String variableName, String getterName) {
        super(variableName, getterName);
    }

    @Override
    public String createResource() {
        return this.getConstructorProcessContext().toConstructorProcess(super.getVariableName(), super.getGetterName());
    }

    /**
     * 設定された{@link ConstructorState}の値を基にコンストラクタの処理定義を生成する際のコンテキストを取得し返却します。<br>
     * 以下のストラテジーを使用します。<br>
     * {@link DtoDefaultConstructorProcessStrategy}<br>
     * {@link DtoRequiredConstructorProcessStrategy}<br>
     * {@link DtoCopyingConstructorProcessStrategy}<br>
     *
     * @return コンストラクタ定義を生成する際に使用するコンテキスト
     */
    private ConstructorProcessContext getConstructorProcessContext() {

        switch (super.getConstructorState()) {
            case REQUIRED:
                return new DtoConstructorProcessContext(new DtoRequiredConstructorProcessStrategy());
            case COPYING:
                return new DtoConstructorProcessContext(new DtoCopyingConstructorProcessStrategy());
            default:
                return new DtoConstructorProcessContext(new DtoDefaultConstructorProcessStrategy());
        }
    }
}