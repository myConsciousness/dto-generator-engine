/*
 * Copyright 2020 Kato Shinya.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package org.thinkit.generator.common.factory.dtogenerator;

import org.thinkit.generator.common.factory.dtogenerator.strategy.CopyingConstructorProcess;
import org.thinkit.generator.common.factory.dtogenerator.strategy.DefaultConstructorProcess;
import org.thinkit.generator.common.factory.dtogenerator.strategy.DtoConstructorProcessContext;
import org.thinkit.generator.common.factory.dtogenerator.strategy.RequiredConstructorProcess;
import org.thinkit.generator.common.factory.resource.ConstructorProcess;

import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * DTOクラスのコンストラクタ処理を生成する具象クラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 *
 * @see ConstructorProcess
 */
@ToString
@EqualsAndHashCode(callSuper = false)
final class DtoConstructorProcess extends ConstructorProcess {

    /**
     * コンストラクタ
     *
     * @param variableName 変数名
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public DtoConstructorProcess(String variableName) {
        super(variableName);
    }

    /**
     * コンストラクタ
     *
     * @param variableName 変数名
     * @param getterName   ゲッター名
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public DtoConstructorProcess(String variableName, String getterName) {
        super(variableName, getterName);
    }

    @Override
    public String createResource() {
        return this.getConstructorProcessContext().toConstructorProcess(super.getVariableName(), super.getGetterName());
    }

    /**
     * 設定された {@link ConstructorState} の値を基にコンストラクタの処理定義を生成する際のコンテキストを取得し返却します。
     * <p>
     * 以下のストラテジーを使用します。
     * <p>
     * {@link DefaultConstructorProcess} <br>
     * {@link RequiredConstructorProcess} <br>
     * {@link CopyingConstructorProcess} <br>
     *
     * @return コンストラクタ定義を生成する際に使用するコンテキスト
     */
    private DtoConstructorProcessContext getConstructorProcessContext() {

        return switch (super.getConstructorType()) {
            case REQUIRED -> new DtoConstructorProcessContext(new RequiredConstructorProcess());
            case COPYING -> new DtoConstructorProcessContext(new CopyingConstructorProcess());
            default -> new DtoConstructorProcessContext(new DefaultConstructorProcess());
        };
    }
}