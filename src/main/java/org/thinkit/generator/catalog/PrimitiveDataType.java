/**
 * Project Name : Generator<br>
 * File Name : PrimitiveDataType.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/06/04<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.catalog;

import org.thinkit.common.catalog.Catalog;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * プリミティブデータ型区分を管理するカテゴリです。
 * 
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@RequiredArgsConstructor
public enum PrimitiveDataType implements Catalog<PrimitiveDataType> {

    /**
     * 真偽値
     */
    BOOLEAN(0),

    /**
     * Unicodeを表現する16bitの整数
     */
    CHAR(1),

    /**
     * 8bitの整数
     */
    BYTE(2),

    /**
     * 16bitの整数
     */
    SHORT(3),

    /**
     * 16bitの整数
     */
    INT(4),

    /**
     * 32bitの整数
     */
    LONG(5),

    /**
     * 32bitの実数
     */
    FLOAT(6),

    /**
     * 64bitの実数
     */
    DOUBLE(7);

    /**
     * コード値
     */
    @Getter
    private final int code;

    /**
     * 引数として渡されたデータ型がプリミティブ型か判定します。<br>
     * 以下の型をプリミティブ型として判定します。<br>
     * <br>
     * 1, {@link #BOOLEAN}<br>
     * 2, {@link #CHAR}<br>
     * 3, {@link #BYTE}<br>
     * 4, {@link #SHORT}<br>
     * 5, {@link #INT}<br>
     * 6, {@link #LONG}<br>
     * 7, {@link #FLOAT}<br>
     * 8, {@link #DOUBLE}<br>
     * 
     * @param dataType データ型
     * @return 引数として渡されたデータ型がプリミティブ型の場合は{@code true}、それ以外は{@code false}
     */
    public static boolean isPrimitive(String dataType) {
        final PrimitiveDataType[] primitiveDataTypes = values();

        for (PrimitiveDataType primitiveDataType : primitiveDataTypes) {
            if (primitiveDataType.name().toLowerCase().equals(dataType)) {
                return true;
            }
        }

        return false;
    }
}