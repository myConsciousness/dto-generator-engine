/**
 * Project Name : Generator<br>
 * File Name : Sheet.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/05/11<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */
package org.thinkit.generator.rule;

/**
 * シート要素のEnum定数で必須となる処理を定義したインターフェースです。</br>
 * シート要素のEnum定数を定義する際は必ず当該インターフェースを実装してください。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
public interface Sheet {

    /**
     * Enum要素の文字列表現を返却します。
     *
     * @return Enum要素の文字列表現。
     */
    public String getString();
}
