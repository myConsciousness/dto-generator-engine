/**
 * Project Name : generator-commons<br>
 * File Name : ClassDescription.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/05/26<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.common.factory.resource;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 * プログラムリソースにおけるクラスの説明を抽象化した抽象クラスです。<br>
 * この抽象クラスではクラスの説明定義に必要な情報を保持します。<br>
 * この抽象クラスを継承する具象クラスは必ず{@link Component#createResource()}を実装してください。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 * @see Component
 * @see Component#createResource()
 * @see Description
 */
@ToString
@EqualsAndHashCode(callSuper = false)
public abstract class ClassDescription extends Description {

    /**
     * 作成者
     */
    @NonNull
    @Getter(AccessLevel.PROTECTED)
    private String creator = "";

    /**
     * バージョン
     */
    @NonNull
    @Getter(AccessLevel.PROTECTED)
    private String version = "";

    /**
     * コンストラクタ
     *
     * @param description クラスの説明
     * @param creator     クラスの作成者
     * @param version     クラスのバージョン
     * @exception NullPointerException 引数として{@code null}が渡された場合
     */
    protected ClassDescription(String description, String creator, String version) {
        super(description);
        this.creator = creator;
        this.version = version;
    }
}