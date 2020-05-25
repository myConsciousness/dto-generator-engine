/**
 * Project Name : Generator<br>
 * File Name : Component.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/05/25<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.rule.factory.resource;

/**
 * リソースの構成要素を抽象化したクラスです。<br>
 * この抽象クラスよりもさらに細かい単位の部品を作成する場合は、<br>
 * 必ずこの抽象クラスを継承するひつようがあります。<br>
 * <br>
 * この抽象クラスを継承した部品クラスは必ず{@link #createResource()}を実装してください。<br>
 * <br>
 * {@link #createResource()}メソッドでは各部品が担当するリソースを生成し、<br>
 * 文字列型として返却する処理を実装してください。
 * 
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
public abstract class Component {

    /**
     * リソースを生成し文字列表現として返却する抽象メソッドです。<br>
     * この抽象メソッドを定義している{@link Component}を継承した具象サブクラスは<br>
     * 必ずこの抽象メソッドを実装する必要があります。 <br>
     * <br>
     * この抽象メソッドはこの抽象クラスを継承する部品の特性に応じて実装してください。<br>
     * 例えば、この抽象クラスをJavaリソースにおけるフィールド定義を管理する部品が継承した場合、<br>
     * その部品クラスはフィールド定義を文字列として生成する処理を実装する必要があります。
     * 
     * @return 各要素を管理する部品で生成されたJavaリソース
     */
    public abstract String createResource();
}