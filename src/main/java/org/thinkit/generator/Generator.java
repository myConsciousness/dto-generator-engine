/**
 * Project Name : Generator<br>
 * File Name : Generator.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/05/14<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator;

/**
 * 生成器インターフェース
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
interface Generator {

    /**
     * 生成処理を開始する処理を定義するメソッドです。
     *
     * @return 処理が正常終了した場合は{@code true}、それ以外は{@code false}
     */
    public boolean execute();
}
