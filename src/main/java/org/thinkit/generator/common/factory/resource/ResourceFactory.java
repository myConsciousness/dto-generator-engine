/*
 * Copyright 2020 Kato Shinya.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package org.thinkit.generator.common.factory.resource;

import lombok.NonNull;

/**
 * プログラムリソースの各構成要素を抽象化したオブジェクトを返却するファクトリクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
public abstract class ResourceFactory {

        /**
         * {@link Copyright}のインスタンスを生成し返却する抽象メソッドです。<br>
         * {@link ResourceFactory}を継承した具象クラスは必ず
         * {@link #createCopyright(String, String, String, String, String)} を実装してください。
         *
         * @param projectName  プロジェクト名
         * @param fileName     ファイル名
         * @param encoding     エンコード
         * @param creator      作成者
         * @param creationDate 作成日付
         * @return {@link Copyright} のインスタンス
         *
         * @exception NullPointerException 引数として {@code null} が渡された場合
         */
        public abstract Copyright createCopyright(@NonNull String projectName, @NonNull String fileName,
                        @NonNull String encoding, @NonNull String creator, @NonNull String creationDate);

        /**
         * {@link ClassDescription} のインスタンスを生成し返却する抽象メソッドです。<br>
         * {@link ResourceFactory} を継承した具象クラスは必ず
         * {@link #createClassDescription(String, String, String)} を実装してください。
         *
         * @param description 説明
         * @param creator     作成者
         * @param version     現行バージョン
         * @return {@link ClassDescription} のインスタンス
         *
         * @exception NullPointerException 引数として {@code null} が渡された場合
         */
        public abstract ClassDescription createClassDescription(@NonNull String description, @NonNull String creator,
                        @NonNull String version);

        /**
         * {@link Description} のインスタンスを生成し返却する抽象メソッドです。<br>
         * {@link ResourceFactory} を継承した具象クラスは必ず {@link #createDescription(String)}
         * を実装してください。
         *
         * @param description 説明
         * @return {@link Description} のインスタンス
         *
         * @exception NullPointerException 引数として {@code null} が渡された場合
         */
        public abstract Description createDescription(@NonNull String description);

        /**
         * {@link Inheritance} のインスタンスを生成し返却する抽象メソッドです。<br>
         * {@link ResourceFactory} を継承した具象クラスは必ず {@link #createDescription(String)}
         * を実装してください。
         *
         * @param literal 継承名
         * @return {@link Inheritance} のインスタンス
         *
         * @exception NullPointerException 引数として {@code null} が渡された場合
         */
        public abstract Inheritance createInheritance(@NonNull String literal);

        /**
         * {@link Inheritance} のインスタンスを生成し返却する抽象メソッドです。<br>
         * {@link ResourceFactory} を継承した具象クラスは必ず {@link #createDescription(String)}
         * を実装してください。
         *
         * @param literal  継承名
         * @param generics 総称型
         * @return {@link Inheritance} のインスタンス
         *
         * @exception NullPointerException 引数として {@code null} が渡された場合
         */
        public abstract Inheritance createInheritance(@NonNull String literal, @NonNull Generics generics);

        /**
         * {@link Interface} のインスタンスを生成し返却する抽象メソッドです。<br>
         * {@link ResourceFactory} を継承した具象クラスは必ず {@link #createDescription(String)}
         * を実装してください。
         *
         * @param literal インターフェース名
         * @return {@link Interface} のインスタンス
         *
         * @exception NullPointerException 引数として {@code null} が渡された場合
         */
        public abstract Interface createInterface(@NonNull String literal);

        /**
         * {@link Interface} のインスタンスを生成し返却する抽象メソッドです。<br>
         * {@link ResourceFactory} を継承した具象クラスは必ず {@link #createDescription(String)}
         * を実装してください。
         *
         * @param literal  インターフェース名
         * @param generics 総称型
         * @return {@link Interface} のインスタンス
         *
         * @exception NullPointerException 引数として {@code null} が渡された場合
         */
        public abstract Interface createInterface(@NonNull String literal, @NonNull Generics generics);

        /**
         * {@link Generics} のインスタンスを生成し返却する抽象メソッドです。<br>
         * {@link ResourceFactory} を継承した具象クラスは必ず {@link #createDescription(String)}
         * を実装してください。
         *
         * @return {@link Generics} のインスタンス
         *
         * @exception NullPointerException 引数として {@code null} が渡された場合
         */
        public abstract Generics createGenerics();

        /**
         * {@link EnumDefinition} のインスタンスを生成し返却する抽象メソッドです。<br>
         * {@link ResourceFactory} を継承した具象クラスは必ず {@link #createEnumeration(String)}
         * を実装してください。
         *
         * @param literal 列挙定数の名称
         * @return {@link EnumDefinition} のインスタンス
         *
         * @exception NullPointerException 引数として {@code null} が渡された場合
         */
        public abstract EnumDefinition createEnumDefinition(@NonNull String literal);

        /**
         * {@link Enumeration} のインスタンスを生成し返却する抽象メソッドです。<br>
         * {@link ResourceFactory} を継承した具象クラスは必ず {@link #createEnumeration(String)}
         * を実装してください。
         *
         * @param enumDefinition 列挙子定義
         * @param description    列挙子の説明
         * @return {@link Enumeration} のインスタンス
         *
         * @exception NullPointerException 引数として {@code null} が渡された場合
         */
        public abstract Enumeration createEnumeration(@NonNull EnumDefinition enumDefinition,
                        @NonNull Description description);

        /**
         * {@link FieldDefinition} のインスタンスを生成し返却する抽象メソッドです。<br>
         * {@link ResourceFactory} を継承した具象クラスは必ず
         * {@link #createFieldDefinition(String, String, String)} を実装してください。
         *
         * @param dataType     データ型
         * @param variableName 変数名
         * @param initialValue 初期値
         * @return {@link FieldDefinition} のインスタンス
         *
         * @exception NullPointerException 引数として {@code null} が渡された場合
         */
        public abstract FieldDefinition createFieldDefinition(@NonNull String dataType, @NonNull String variableName,
                        @NonNull String initialValue);

        /**
         * {@link FunctionDescription} のインスタンスを生成し返却する抽象メソッドです。<br>
         * {@link ResourceFactory} を継承した具象クラスは必ず
         * {@link #createFunctionDescription(String)} を実装してください。
         *
         * @param description 説明
         * @return {@link FunctionDescription} のインスタンス
         *
         * @exception NullPointerException 引数として {@code null} が渡された場合
         */
        public abstract FunctionDescription createFunctionDescription(@NonNull String description);

        /**
         * {@link DescriptionTag} のインスタンスを生成し返却する抽象メソッドです。<br>
         * {@link ResourceFactory} を継承した具象クラスは必ず
         * {@link #createDescriptionTag(String, String)} を実装してください。
         *
         * @param variableName 変数名
         * @param description  説明
         * @return {@link DescriptionTag} のインスタンス
         *
         * @exception NullPointerException 引数として {@code null} が渡された場合
         */
        public abstract DescriptionTag createDescriptionTag(@NonNull String variableName, @NonNull String description);

        /**
         * {@link Constructor} のインスタンスを生成し返却する抽象メソッドです。<br>
         * {@link ResourceFactory} を継承した具象クラスは必ず
         * {@link #createConstructor(String, FunctionDescription)} を実装してください。
         *
         * @param functionName        機能名
         * @param functionDescription 機能の説明
         * @return {@link Constructor} のインスタンス
         *
         * @exception NullPointerException 引数として {@code null} が渡された場合
         */
        public abstract Constructor createConstructor(@NonNull String functionName,
                        @NonNull FunctionDescription functionDescription);

        /**
         * {@link Parameter} のインスタンスを生成し返却する抽象メソッドです。<br>
         * {@link ResourceFactory} を継承した具象クラスは必ず
         * {@link #createParameter(String, String)} を実装してください。
         *
         * @param dataType     データ型
         * @param variableName 変数名
         * @return {@link Parameter} のインスタンス
         *
         * @exception NullPointerException 引数として {@code null} が渡された場合
         */
        public abstract Parameter createParameter(@NonNull String dataType, @NonNull String variableName);

        /**
         * {@link ConstructorProcess} のインスタンスを生成し返却する抽象メソッドです。<br>
         * {@link ResourceFactory} を継承した具象クラスは必ず
         * {@link #createConstructorProcess(String)} を実装してください。
         *
         * @param variableName 変数名
         * @return {@link ConstructorProcess} のインスタンス
         *
         * @exception NullPointerException 引数として {@code null} が渡された場合
         */
        public abstract ConstructorProcess createConstructorProcess(String variableName);

        /**
         * {@link ConstructorProcess} のインスタンスを生成し返却する抽象メソッドです。<br>
         * {@link ResourceFactory} を継承した具象クラスは必ず
         * {@link #createConstructorProcess(String, String)} を実装してください。
         *
         * @param variableName 変数名
         * @param getterName   ゲッター名
         * @return {@link ConstructorProcess} のインスタンス
         *
         * @exception NullPointerException 引数として {@code null} が渡された場合
         */
        public abstract ConstructorProcess createConstructorProcess(@NonNull String variableName,
                        @NonNull String getterName);

        /**
         * {@link Resource} のインスタンスを生成し返却する抽象メソッドです。<br>
         * {@link ResourceFactory} を継承した具象クラスは必ず
         * {@link #createResource(Copyright, String, ClassDescription, String, Field)}
         * を実装してください。
         *
         * @param copyright        著作権
         * @param packageName      パッケージ名
         * @param classDescription クラスの説明
         * @param resourceName     リソース名
         * @return {@link Resource} のインスタンス
         *
         * @exception NullPointerException 引数として {@code null} が渡された場合
         */
        public abstract Resource createResource(@NonNull Copyright copyright, @NonNull String packageName,
                        @NonNull ClassDescription classDescription, @NonNull String resourceName);
}