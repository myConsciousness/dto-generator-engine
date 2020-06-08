/**
 * Project Name : Generator<br>
 * File Name : ConstructorContext.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/06/08<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.rule.strategy.resource;

import org.thinkit.generator.rule.factory.resource.Parameter;
import org.thinkit.generator.rule.factory.resource.Process;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;
import lombok.Getter;

/**
 * コンストラクタ定義をする際のストラテジーを判断するコンテキストを抽象化したクラスです。<br>
 * {#link ConstructorContext}を継承した具象クラスは以下の抽象メソッドを実装する必要があります。<br>
 * {@link #toParameter(Parameter)}<br>
 * {@link #toProcess(Process)}<br>
 * 
 * @author 1.0
 * @since 1.0
 * @version 1.0
 * 
 * @see #toParameter(Parameter)
 * @see #toProcess(Process)
 */
@ToString
@EqualsAndHashCode
public abstract class ConstructorContext {

    /**
     * コンストラクタストラテジー
     */
    @Getter(AccessLevel.PROTECTED)
    private ConstructorStrategy constructorStrategy = null;

    /**
     * デフォルトコンストラクタ
     */
    @SuppressWarnings("unused")
    private ConstructorContext() {
    }

    /**
     * コンストラクタ
     * 
     * @param constructorStrategy コンストラクタストラテジー
     * 
     * @exception NullPointerException 引数として{@code null}が渡された場合
     */
    protected ConstructorContext(@NonNull ConstructorStrategy constructorStrategy) {
        this.constructorStrategy = constructorStrategy;
    }

    /**
     * 引数として渡された{@link Parameter}に格納された情報を基に<br>
     * 引数定義を生成し文字列として返却する処理を定義する抽象メソッドです。
     * 
     * @param parameter 引数情報
     * @return {@link Parameter}に格納された情報を基に生成された引数定義
     * 
     * @exception NullPointerException 引数として{@code null}が渡された場合
     */
    protected abstract String toParameter(@NonNull Parameter parameter);

    /**
     * 引数として渡された{@link Process}に格納された情報を基に<br>
     * 処理定義を生成し文字列として返却する処理を定義する抽象メソッドです。
     * 
     * @param parameter 処理情報
     * @return {@link Process}に格納された情報を基に生成された処理定義
     * 
     * @exception NullPointerException 引数として{@code null}が渡された場合
     */
    protected abstract String toProcess(@NonNull Process process);
}