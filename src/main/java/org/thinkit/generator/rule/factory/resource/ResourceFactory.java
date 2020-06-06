/**
 * Project Name : Generator<br>
 * File Name : ResourceFactory.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/06/06<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.rule.factory.resource;

/**
 * プログラムリソースの各構成要素を抽象化したオブジェクトを返却するファクトリクラスです。<br>
 * {#link ResourceFactory}を継承した具象クラスは必ず各抽象メソッドを実装してください。<br>
 * <br>
 * 以下のファクトリメソッドが正しく実装されることが期待されます。<br>
 * {@link #createClassDescription()} <br>
 * {@link #createDescription()} <br>
 * {@link #createField()} <br>
 * {@link #createFieldDefinition()} <br>
 * {@link #createConstructor()} <br>
 * {@link #createParameter()} <br>
 * {@link #createProcess()} <br>
 * {@link #createResource()} <br>
 * 
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
public abstract class ResourceFactory {

    /**
     * {@link ClassDescription}のインスタンスを生成し返却する抽象メソッドです。<br>
     * {@link ResourceFactory}を継承した具象クラスは必ず{@link #createClassDescription()}を実装してください。
     * 
     * @return {@link ClassDescription}のインスタンス
     */
    public abstract ClassDescription createClassDescription();

    /**
     * {@link Description}のインスタンスを生成し返却する抽象メソッドです。<br>
     * {@link ResourceFactory}を継承した具象クラスは必ず{@link #createDescription()}を実装してください。
     * 
     * @return {@link Description}のインスタンス
     */
    public abstract Description createDescription();

    /**
     * {@link Field}のインスタンスを生成し返却する抽象メソッドです。<br>
     * {@link ResourceFactory}を継承した具象クラスは必ず{@link #createField()}を実装してください。
     * 
     * @return {@link Field}のインスタンス
     */
    public abstract Field createField();

    /**
     * {@link FieldDefinition}のインスタンスを生成し返却する抽象メソッドです。<br>
     * {@link ResourceFactory}を継承した具象クラスは必ず{@link #createFieldDefinition()}を実装してください。
     * 
     * @return {@link FieldDefinition}のインスタンス
     */
    public abstract FieldDefinition createFieldDefinition();

    /**
     * {@link Constructor}のインスタンスを生成し返却する抽象メソッドです。<br>
     * {@link ResourceFactory}を継承した具象クラスは必ず{@link #createConstructor()}を実装してください。
     * 
     * @return {@link Constructor}のインスタンス
     */
    public abstract Constructor createConstructor();

    /**
     * {@link Parameter}のインスタンスを生成し返却する抽象メソッドです。<br>
     * {@link ResourceFactory}を継承した具象クラスは必ず{@link #createParameter()}を実装してください。
     * 
     * @return {@link Parameter}のインスタンス
     */
    public abstract Parameter createParameter();

    /**
     * {@link Process}のインスタンスを生成し返却する抽象メソッドです。<br>
     * {@link ResourceFactory}を継承した具象クラスは必ず{@link #createProcess()}を実装してください。
     * 
     * @return {@link Process}のインスタンス
     */
    public abstract Process createProcess();

    /**
     * {@link Resource}のインスタンスを生成し返却する抽象メソッドです。<br>
     * {@link ResourceFactory}を継承した具象クラスは必ず{@link #createResource()}を実装してください。
     * 
     * @return {@link Resource}のインスタンス
     */
    public abstract Resource createResource();
}