/**
 * Project Name : Generator<br>
 * File Name : ConstructorProcessStrategy.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/06/09<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.rule.factory.strategy.resource;

import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

/**
 * コンストラクタの処理定義を生成する際のストラテジーを抽象化したクラスです。<br>
 * それぞれ異なるコンストラクタの処理定義を生成する際に{@link ConstructorProcessStrategy}を継承した具象クラスを定義してください。<br>
 * <br>
 * {@link ConstructorProcessStrategy}を継承した場合は以下の抽象メソッドを必ず実装する必要があります。<br>
 * {@link #toConstructorProcess(String, List)}<br>
 * 
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 * 
 * @see #toConstructorProcess(String, List)
 */
@ToString
@EqualsAndHashCode(callSuper = false)
public abstract class ConstructorProcessStrategy {

    /**
     * 引数として渡された情報を基にコンストラクタ処理定義を生成し文字列として返却する処理を定義する抽象メソッドです。
     * 
     * @param variableName  変数名
     * @param variableNames 変数名リスト
     * @return 渡された引数情報を基に生成されたコンストラクタ処理定義
     * 
     * @exception NullPointerException 引数として{@code null}が渡された場合
     */
    public abstract String toConstructorProcess(@NonNull String variableName, @NonNull List<String> variableNames);
}
