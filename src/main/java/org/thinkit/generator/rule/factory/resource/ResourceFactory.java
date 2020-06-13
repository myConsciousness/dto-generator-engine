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
 * {@link #createCopyright(String, String, String, String, String)} <br>
 * {@link #createClassDescription(String, String, String)} <br>
 * {@link #createDescription(String)} <br>
 * {@link #createFieldDefinition(String, String, String)} <br>
 * {@link #createConstructor(String, FunctionDescription)} <br>
 * {@link #createConstructorProcess(String, String)} <br>
 * {@link #createParameter(String, String)} <br>
 * {@link #createConstructorProcess(String)} <br>
 * {@link #createResource(Copyright, String, ClassDescription, String)} <br>
 * 
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
public abstract class ResourceFactory {

        /**
         * {@link Copyright}のインスタンスを生成し返却する抽象メソッドです。<br>
         * {@link ResourceFactory}を継承した具象クラスは必ず{@link #createCopyright(String, String, String, String, String)}を実装してください。
         * 
         * @param projectName  プロジェクト名
         * @param fileName     ファイル名
         * @param encoding     エンコード
         * @param creator      作成者
         * @param creationDate 作成日付
         * @return {@link Copyright}のインスタンス
         * 
         * @exception NullPointerException 引数として{@code null}が渡された場合
         */
        public abstract Copyright createCopyright(String projectName, String fileName, String encoding, String creator,
                        String creationDate);

        /**
         * {@link ClassDescription}のインスタンスを生成し返却する抽象メソッドです。<br>
         * {@link ResourceFactory}を継承した具象クラスは必ず{@link #createClassDescription(String, String, String)}を実装してください。
         * 
         * @param description 説明
         * @param creator     作成者
         * @param version     現行バージョン
         * @return {@link ClassDescription}のインスタンス
         * 
         * @exception NullPointerException 引数として{@code null}が渡された場合
         */
        public abstract ClassDescription createClassDescription(String description, String creator, String version);

        /**
         * {@link Description}のインスタンスを生成し返却する抽象メソッドです。<br>
         * {@link ResourceFactory}を継承した具象クラスは必ず{@link #createDescription(String)}を実装してください。
         * 
         * @param description 説明
         * @return {@link Description}のインスタンス
         * 
         * @exception NullPointerException 引数として{@code null}が渡された場合
         */
        public abstract Description createDescription(String description);

        /**
         * {@link FieldDefinition}のインスタンスを生成し返却する抽象メソッドです。<br>
         * {@link ResourceFactory}を継承した具象クラスは必ず{@link #createFieldDefinition(String, String, String)}を実装してください。
         * 
         * @param dataType     データ型
         * @param variableName 変数名
         * @param initialValue 初期値
         * @return {@link FieldDefinition}のインスタンス
         * 
         * @exception NullPointerException 引数として{@code null}が渡された場合
         */
        public abstract FieldDefinition createFieldDefinition(String dataType, String variableName,
                        String initialValue);

        /**
         * {@link FunctionDescription}のインスタンスを生成し返却する抽象メソッドです。<br>
         * {@link ResourceFactory}を継承した具象クラスは必ず{@link #createFunctionDescription(String)}を実装してください。
         * 
         * @param description 説明
         * @return {@link FunctionDescription}のインスタンス
         * 
         * @exception NullPointerException 引数として{@code null}が渡された場合
         */
        public abstract FunctionDescription createFunctionDescription(String description);

        /**
         * {@link FunctionParamAnnotation}のインスタンスを生成し返却する抽象メソッドです。<br>
         * {@link ResourceFactory}を継承した具象クラスは必ず{@link #createFunctionParamAnnotation(String, String)}を実装してください。
         * 
         * @param variableName 変数名
         * @param description  説明
         * @return {@link FunctionParamAnnotation}のインスタンス
         * 
         * @exception NullPointerException 引数として{@code null}が渡された場合
         */
        public abstract FunctionParamAnnotation createFunctionParamAnnotation(String variableName, String description);

        /**
         * {@link Constructor}のインスタンスを生成し返却する抽象メソッドです。<br>
         * {@link ResourceFactory}を継承した具象クラスは必ず{@link #createConstructor(String, FunctionDescription)}を実装してください。
         * 
         * @param functionName        機能名
         * @param functionDescription 機能の説明
         * @return {@link Constructor}のインスタンス
         * 
         * @exception NullPointerException 引数として{@code null}が渡された場合
         */
        public abstract Constructor createConstructor(String functionName, FunctionDescription functionDescription);

        /**
         * {@link Parameter}のインスタンスを生成し返却する抽象メソッドです。<br>
         * {@link ResourceFactory}を継承した具象クラスは必ず{@link #createParameter(String, String)}を実装してください。
         * 
         * @param dataType     データ型
         * @param variableName 変数名
         * @return {@link Parameter}のインスタンス
         * 
         * @exception NullPointerException 引数として{@code null}が渡された場合
         */
        public abstract Parameter createParameter(String dataType, String variableName);

        /**
         * {@link ConstructorProcess}のインスタンスを生成し返却する抽象メソッドです。<br>
         * {@link ResourceFactory}を継承した具象クラスは必ず{@link #createConstructorProcess(String)}を実装してください。
         * 
         * @param variableName 変数名
         * @return {@link ConstructorProcess}のインスタンス
         * 
         * @exception NullPointerException 引数として{@code null}が渡された場合
         */
        public abstract ConstructorProcess createConstructorProcess(String variableName);

        /**
         * {@link ConstructorProcess}のインスタンスを生成し返却する抽象メソッドです。<br>
         * {@link ResourceFactory}を継承した具象クラスは必ず{@link #createConstructorProcess(String, String)}を実装してください。
         * 
         * @param variableName 変数名
         * @param getterName   ゲッター名
         * @return {@link ConstructorProcess}のインスタンス
         * 
         * @exception NullPointerException 引数として{@code null}が渡された場合
         */
        public abstract ConstructorProcess createConstructorProcess(String variableName, String getterName);

        /**
         * {@link Resource}のインスタンスを生成し返却する抽象メソッドです。<br>
         * {@link ResourceFactory}を継承した具象クラスは必ず{@link #createResource(Copyright, String, ClassDescription, String, Field)}を実装してください。
         * 
         * @param copyright        著作権
         * @param packageName      パッケージ名
         * @param classDescription クラスの説明
         * @param resourceName     リソース名
         * @return {@link Resource}のインスタンス
         * 
         * @exception NullPointerException 引数として{@code null}が渡された場合
         */
        public abstract Resource createResource(Copyright copyright, String packageName,
                        ClassDescription classDescription, String resourceName);
}