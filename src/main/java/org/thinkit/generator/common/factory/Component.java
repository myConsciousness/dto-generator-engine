/**
 * Project Name : generator-commons<br>
 * File Name : Component.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/05/25<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.common.factory;

/**
 * 構成部品を抽象化したインターフェースです。
 * <p>
 * このインターフェースを実装する部品クラスは必ず {@link #createResource()} を実装してください。
 * <p>
 * {@link #createResource()} メソッドでは各部品が担当するリソースを生成し文字列型として返却する処理を実装してください。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
public interface Component {

    /**
     * リソースを生成し文字列表現として返却する処理を定義するメソッドです。<br>
     * このメソッドを定義している {@link Component} を継承した具象サブクラスは<br>
     * 必ずこのメソッドを実装する必要があります。
     *
     *
     * @return 各要素を管理する部品で生成されたJavaリソース
     */
    public String createResource();
}