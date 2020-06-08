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

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.thinkit.generator.rule.factory.resource.Parameter;
import org.thinkit.generator.rule.factory.resource.Process;
import org.thinkit.generator.rule.factory.strategy.resource.ConstructorStrategy;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

/**
 * デフォルトコンストラクタ定義を生成する際に使用するストラテジを実装した具象クラスです。<br>
 * <br>
 * 以下の機能を提供しています。<br>
 * {@link #toParameter(List)}<br>
 * {@link #toProcess(List)}<br>
 * <br>
 * デフォルトコンストラクタでは引数情報と処理情報が存在しないため各機能は必ず空文字列を返却します。
 * 
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 * 
 * @see #toParameter(List)
 * @see #toProcess(List)
 */
@ToString
@EqualsAndHashCode(callSuper = false)
public class DtoDefaultConstructorStrategy extends ConstructorStrategy {

    public DtoDefaultConstructorStrategy() {
    }

    @Override
    public String toParameter(@NonNull List<Parameter> parameters) {
        return StringUtils.EMPTY;
    }

    @Override
    public String toProcess(@NonNull List<Process> processes) {
        return StringUtils.EMPTY;
    }
}