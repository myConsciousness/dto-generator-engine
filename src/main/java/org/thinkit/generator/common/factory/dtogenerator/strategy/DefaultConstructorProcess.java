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

package org.thinkit.generator.common.factory.dtogenerator.strategy;

import org.apache.commons.lang3.StringUtils;
import org.thinkit.generator.common.factory.resource.strategy.ConstructorProcessStrategy;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

/**
 * デフォルトコンストラクタの処理定義を生成する際に使用するストラテジーを実装した具象クラスです。
 * <p>
 * 以下の機能を提供しています。<br>
 * {@link #toConstructorProcess(String, String)}<br>
 * <p>
 * デフォルトコンストラクタでは処理情報が存在しないため各機能は必ず空文字列を返却します。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 *
 * @see #toConstructorProcess(String, String)
 */
@ToString
@EqualsAndHashCode(callSuper = false)
public class DefaultConstructorProcess extends ConstructorProcessStrategy {

    @Override
    public String toConstructorProcess(@NonNull String variableName, @NonNull String getterName) {
        return StringUtils.EMPTY;
    }
}