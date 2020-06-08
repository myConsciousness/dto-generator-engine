/**
 * Project Name : Generator<br>
 * File Name : ConstructorStrategy.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/06/08<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.rule.factory.strategy.resource;

import java.util.List;

import org.thinkit.generator.rule.factory.resource.Parameter;
import org.thinkit.generator.rule.factory.resource.Process;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

/**
 * コンストラクタ定義を生成する際のストラテジーを抽象化したクラスです。<br>
 * それぞれ異なるコンストラクタ定義を生成する際に{@link ConstructorStrategy}を継承した具象クラスを定義してください。<br>
 * <br>
 * {@link ConstructorStrategy}を継承した場合は以下の抽象メソッドを必ず実装する必要があります。<br>
 * {@link #toParameter(List)}<br>
 * {@link #toProcess(List)}<br>
 * 
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 * 
 * @see #toParameter(List)
 * @see #toProcess(List)
 */
@ToString
@EqualsAndHashCode
public abstract class ConstructorStrategy {

    /**
     * 引数として渡された{@link Parameter}に格納された情報を基に<br>
     * 引数定義を生成し文字列として返却する処理を定義する抽象メソッドです。
     * 
     * @param parameter 引数情報
     * @return {@link Parameter}に格納された情報を基に生成された引数定義
     * 
     * @exception NullPointerException 引数として{@code null}が渡された場合
     */
    public abstract String toParameter(@NonNull List<Parameter> parameters);

    /**
     * 引数として渡された{@link Process}に格納された情報を基に<br>
     * 処理定義を生成し文字列として返却する処理を定義する抽象メソッドです。
     * 
     * @param parameter 処理情報
     * @return {@link Process}に格納された情報を基に生成された処理定義
     * 
     * @exception NullPointerException 引数として{@code null}が渡された場合
     */
    public abstract String toProcess(@NonNull List<Process> processes);
}