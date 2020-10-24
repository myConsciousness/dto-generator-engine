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

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 * プログラムリソースにおけるクラスの説明を抽象化した抽象クラスです。
 * <p>
 * この抽象クラスではクラスの説明定義に必要な情報を保持します。<br>
 * この抽象クラスを継承する具象クラスは必ず {@link Component#createResource()} を実装してください。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 *
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
    private String creator;

    /**
     * バージョン
     */
    @NonNull
    @Getter(AccessLevel.PROTECTED)
    private String version;

    /**
     * コンストラクタ
     *
     * @param creator クラスの作成者
     * @param version クラスのバージョン
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    protected ClassDescription(@NonNull String creator, @NonNull String version) {
        super("");
        this.creator = creator;
        this.version = version;
    }

    /**
     * コンストラクタ
     *
     * @param description クラスの説明
     * @param creator     クラスの作成者
     * @param version     クラスのバージョン
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    protected ClassDescription(@NonNull String description, @NonNull String creator, @NonNull String version) {
        super(description);
        this.creator = creator;
        this.version = version;
    }
}