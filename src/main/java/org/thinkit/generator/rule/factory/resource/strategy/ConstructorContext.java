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

package org.thinkit.generator.rule.factory.resource.strategy;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;
import lombok.Getter;

/**
 * コンストラクタ定義をする際のストラテジーを判断するコンテキストを抽象化したクラスです。<br>
 * {#link ConstructorContext}を継承した具象クラスは以下の抽象メソッドを実装する必要があります。<br>
 * {@link #createResource()}
 * 
 * @author 1.0
 * @since 1.0
 * @version 1.0
 * 
 * @see #createResource()
 */
@ToString
@EqualsAndHashCode
public abstract class ConstructorContext {

    /**
     * コンストラクタストラテジー
     */
    @Getter(AccessLevel.PRIVATE)
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

    public abstract String createResource();
}