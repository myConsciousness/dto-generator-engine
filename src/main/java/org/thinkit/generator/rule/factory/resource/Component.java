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

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * リソースの構成要素を抽象化したインターフェースです。<br>
 * この抽象クラスよりもさらに細かい単位の部品を作成する場合は、<br>
 * 必ずこのインターフェースを実装する必要があります。<br>
 * <br>
 * このインターフェースを実装する部品クラスは必ず{@link #createResource()}を実装してください。<br>
 * <br>
 * {@link #createResource()}メソッドでは各部品が担当するリソースを生成し、<br>
 * 文字列型として返却する処理を実装してください。
 * 
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
public interface Component {

    /**
     * インデント定数
     */
    @RequiredArgsConstructor
    public enum Indentation {

        /**
         * 半角スペース
         */
        SPACE(" "),

        /**
         * 水平タブ
         */
        TAB("¥t"),

        /**
         * 改行
         */
        RETURN("¥r¥n");

        /**
         * インデント文字列
         */
        @Getter(AccessLevel.PRIVATE)
        private final String character;

        /**
         * 半角空白を返却します。
         * 
         * @return 半角空白
         * @see #SPACE
         */
        public static String getSpace() {
            return SPACE.getCharacter();
        }

        /**
         * タブを返却します。
         * 
         * @return タブ
         * @see #TAB
         */
        public static String getTab() {
            return TAB.getCharacter();
        }

        /**
         * 改行を返却します。
         * 
         * @return 改行
         * @see #RETURN
         */
        public static String getReturn() {
            return RETURN.getCharacter();
        }

        /**
         * インデント用の半角空白を返却します。<br>
         * このメソッドからは必ず半角空白4個を結合したインデント用文字列が返却されます。<br>
         * 任意の個数のインデント用文字列を取得したい場合は{@link #getIndentSpaces(int)}を使用してください。
         * 
         * @return 半角空白4個のインデント用文字列
         * @see #getIndentSpaces(int)
         */
        public static String getIndentSpaces() {
            return getIndentSpaces(4);
        }

        /**
         * 引数として指定された個数分の半角空白を返却します。<br>
         * このメソッドからは引数として指定された任意の個数分のインデント用文字列が返却されます。<br>
         * 半角空白4個のインデント用文字列を取得したい場合はこのメソッドを使用しても問題はありませんが、<br>
         * 専用のメソッドを用意しているので{@link #getIndentSpaces()}を使用するようにしてください。
         * 
         * @param number インデント用文字列を生成する際に使用する任意の空白数
         * @return 指定された空白数に対応したインデント用文字列
         * @see #getIndentSpaces()
         */
        public static String getIndentSpaces(int number) {

            final StringBuilder indentSpaces = new StringBuilder(number);
            final String space = getSpace();

            for (int i = 0; i < number; i++) {
                indentSpaces.append(space);
            }

            return indentSpaces.toString();
        }
    }

    /**
     * リソースを生成し文字列表現として返却する処理を定義するメソッドです。<br>
     * このメソッドを定義している{@link Component}を継承した具象サブクラスは<br>
     * 必ずこのメソッドを実装する必要があります。 <br>
     * <br>
     * この抽象メソッドはこの抽象クラスを継承する部品の特性に応じて実装してください。<br>
     * 例えば、この抽象クラスをJavaリソースにおけるフィールド定義を管理する部品が継承した場合、<br>
     * その部品クラスはフィールド定義を文字列として生成する処理を実装する必要があります。
     * 
     * @return 各要素を管理する部品で生成されたJavaリソース
     */
    public String createResource();
}