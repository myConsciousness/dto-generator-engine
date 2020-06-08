/**
 * Project Name : Generator<br>
 * File Name : DtoDefaultConstructorStrategy.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/06/08<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.rule.factory.strategy.dtogenerator;

import org.apache.commons.lang3.StringUtils;
import org.thinkit.generator.rule.factory.resource.Parameter;
import org.thinkit.generator.rule.factory.resource.Process;
import org.thinkit.generator.rule.factory.strategy.resource.ConstructorStrategy;

import lombok.NonNull;

/**
 * デフォルトコンストラクタ定義を生成する際に使用するストラテジを実装した具象クラスです。<br>
 * <br>
 * 以下の機能を提供しています。<br>
 * {@link #toParameter(Parameter)}<br>
 * {@link #toProcess(Process)}<br>
 * <br>
 * デフォルトコンストラクタでは引数情報と処理情報が存在しないため各機能は必ず空文字列を返却します。
 * 
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
public class DtoDefaultConstructorStrategy extends ConstructorStrategy {

    public DtoDefaultConstructorStrategy() {
    }

    @Override
    public String toParameter(@NonNull Parameter parameter) {
        return StringUtils.EMPTY;
    }

    @Override
    public String toProcess(@NonNull Process process) {
        return StringUtils.EMPTY;
    }
}