/**
 * Project Name : Generator<br>
 * File Name : DtoAnnotation.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/06/05<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.catalog.dtogenerator;

import org.thinkit.common.catalog.Catalog;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * DTOクラスで使用するアノテーションを管理するカタログです。<br>
 * {@link #getAnnotation()}を使用することで各要素のアノテーションを取得することができます。<br>
 * <br>
 * 以下の静的メソッドを使用することでも各アノテーションを取得することができます。<br>
 * {@link #author()}<br>
 * {@link #since()}<br>
 * {@link #version()}<br>
 * {@link #lombokNonNull()}<br>
 * 
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@Getter
@RequiredArgsConstructor
public enum DtoAnnotation implements Catalog<DtoAnnotation> {

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
     * NonNull(Lombok)
     */
    LOMBOK_NON_NULL(3, "@NonNull");

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
}