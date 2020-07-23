/**
 * Project Name : generator-commons<br>
 * File Name : DefaultConstructorProcess.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/06/09<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
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