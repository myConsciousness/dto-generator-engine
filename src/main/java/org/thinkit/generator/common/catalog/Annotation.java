/**
 * Project Name : generator-commons<br>
 * File Name : Annotation.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/06/05<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.common.catalog;

import org.thinkit.common.catalog.Catalog;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * クラスで使用するアノテーションを管理するカタログです。<br>
 * {@link #getAnnotation()}を使用することで各要素のアノテーションを取得することができます。
 * <p>
 * 以下の静的メソッドを使用することでも各アノテーションを取得することができます。<br>
 * {@link #author()}<br>
 * {@link #since()}<br>
 * {@link #version()}<br>
 * {@link #lombokNonNull()}<br>
 * {@link #lombokToString()}<br>
 * {@link #lombokEqualsAndHashCode()}<br>
 * {@link #param()}<br>
 * {@link #lombokSetter()}<br>
 * {@link #lombokGetter()}<br>
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@Getter
@RequiredArgsConstructor
public enum Annotation implements Catalog<Annotation> {

    /**
     * 作成者
     */
    AUTHOR(0, "@author"),

    /**
     * 作成時バージョン
     */
    SINCE(1, "@since"),

    /**
     * 現行バージョン
     */
    VERSION(2, "@version"),

    /**
     * NonNull (Lombok)
     */
    LOMBOK_NON_NULL(3, "@NonNull"),

    /**
     * ToString (Lombok)
     */
    LOMBOK_TO_STRING(4, "@ToString"),

    /**
     * Equals and Hash code (Lombok)
     */
    LOMBOK_EQUALS_AND_HASH_CODE(5, "@EqualsAndHashCode"),

    /**
     * 引数情報
     */
    PARAM(6, "@param"),

    /**
     * Setter (Lombok)
     */
    LOMBOK_SETTER(7, "@Setter"),

    /**
     * Getter (Lombok)
     */
    LOMBOK_GETTER(8, "@Getter");

    /**
     * コード値
     */
    private final int code;

    /**
     * アノテーション
     */
    private final String annotation;

    /**
     * 作成者のアノテーションを返却します。
     *
     * @return 作成者のアノテーション
     * @see #AUTHOR
     */
    public static String author() {
        return AUTHOR.getAnnotation();
    }

    /**
     * 作成時バージョンのアノテーションを返却します。
     *
     * @return 作成時バージョンのアノテーションを返却します。
     * @see #SINCE
     */
    public static String since() {
        return SINCE.getAnnotation();
    }

    /**
     * 現行バージョンのアノテーションを返却します。
     *
     * @return 現行バージョンのアノテーションを返却します。
     * @see #VERSION
     */
    public static String version() {
        return VERSION.getAnnotation();
    }

    /**
     * LombokのNonNullアノテーションを返却します。
     *
     * @return LombokのNonNullアノテーションを返却します。
     * @see #LOMBOK_NON_NULL
     */
    public static String lombokNonNull() {
        return LOMBOK_NON_NULL.getAnnotation();
    }

    /**
     * LombokのToStringアノテーションを返却します。
     *
     * @return LombokのToStringアノテーションを返却します。
     * @see #LOMBOK_TO_STRING
     */
    public static String lombokToString() {
        return LOMBOK_TO_STRING.getAnnotation();
    }

    /**
     * LombokのEqualsAndHashCodeアノテーションを返却します。
     *
     * @return LombokのEqualsAndHashCodeアノテーションを返却します。
     * @see #EqualsAndHashCode
     */
    public static String lombokEqualsAndHashCode() {
        return LOMBOK_EQUALS_AND_HASH_CODE.getAnnotation();
    }

    /**
     * 引数情報のアノテーションを返却します。
     *
     * @return 引数情報のアノテーションを返却します。
     * @see #PARAM
     */
    public static String param() {
        return PARAM.getAnnotation();
    }

    /**
     * LombokのSetterアノテーションを返却します。
     *
     * @return LombokのSetterアノテーションを返却します。
     * @see #LOMBOK_SETTER
     */
    public static String lombokSetter() {
        return LOMBOK_SETTER.getAnnotation();
    }

    /**
     * LombokのGetterアノテーションを返却します。
     *
     * @return LombokのGetterアノテーションを返却します。
     * @see #LOMBOK_GETTER
     */
    public static String lombokGetter() {
        return LOMBOK_GETTER.getAnnotation();
    }
}