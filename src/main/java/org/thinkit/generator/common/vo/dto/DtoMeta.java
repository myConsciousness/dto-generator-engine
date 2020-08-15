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

package org.thinkit.generator.common.vo.dto;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 * DTOメタ情報を管理するデータクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@Getter
@ToString
@EqualsAndHashCode
public final class DtoMeta implements Serializable {

    /**
     * シリアルバージョンUID
     */
    private static final long serialVersionUID = 570555546726415110L;

    /**
     * バージョン
     */
    private String version;

    /**
     * プロジェクト名
     */
    private String projectName;

    /**
     * パッケージ名
     */
    private String packageName;

    /**
     * クラスの物理名
     */
    private String physicalName;

    /**
     * クラスの論理名
     */
    private String logicalName;

    /**
     * クラスの補足
     */
    private String description;

    /**
     * デフォルトコンストラクタ
     */
    private DtoMeta() {
    }

    /**
     * コンストラクタ
     *
     * @param version      バージョン
     * @param projectName  プロジェクト名
     * @param packageName  パッケージ名
     * @param physicalName 物理名
     * @param logicalName  論理名
     * @param description  補足
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private DtoMeta(@NonNull final String version, @NonNull final String projectName, @NonNull final String packageName,
            @NonNull String physicalName, @NonNull String logicalName, @NonNull String description) {
        this.version = version;
        this.projectName = projectName;
        this.packageName = packageName;
        this.logicalName = logicalName;
        this.physicalName = physicalName;
        this.description = description;
    }

    /**
     * コピーコンストラクタ
     *
     * @param dtoMeta DTOメタ
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private DtoMeta(@NonNull DtoMeta dtoMeta) {
        this.version = dtoMeta.getVersion();
        this.projectName = dtoMeta.getProjectName();
        this.packageName = dtoMeta.getPackageName();
        this.logicalName = dtoMeta.getLogicalName();
        this.physicalName = dtoMeta.getPhysicalName();
        this.description = dtoMeta.getDescription();
    }

    /**
     * 引数として指定された情報を基に {@link DtoMeta} クラスの新しいインスタンスを生成し返却します。
     *
     * @param version      バージョン
     * @param projectName  プロジェクト名
     * @param packageName  パッケージ名
     * @param physicalName 物理名
     * @param logicalName  論理名
     * @param description  補足
     * @return {@link DtoMeta} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static DtoMeta of(@NonNull final String version, @NonNull final String projectName,
            @NonNull final String packageName, @NonNull String physicalName, @NonNull String logicalName,
            @NonNull String description) {
        return new DtoMeta(version, projectName, packageName, physicalName, logicalName, description);
    }

    /**
     * 引数として指定された {@code dtoMeta} オブジェクトの情報を基に {@link DtoMeta}
     * クラスの新しいインスタンスを生成し返却します。
     *
     * @param dtoMeta DTOメタ
     * @return {@link DtoMeta} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static DtoMeta of(@NonNull DtoMeta dtoMeta) {
        return new DtoMeta(dtoMeta);
    }
}
