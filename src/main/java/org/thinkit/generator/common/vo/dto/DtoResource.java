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
 * DTOリソースを管理するデータクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@Getter
@ToString
@EqualsAndHashCode
public final class DtoResource implements Serializable {

    /**
     * シリアルバージョンUID
     */
    private static final long serialVersionUID = -4582640533481509620L;

    /**
     * パッケージ名
     */
    private String packageName;

    /**
     * リソース名
     */
    private String resourceName;

    /**
     * リソース
     */
    private String resource;

    /**
     * デフォルトコンストラクタ
     */
    private DtoResource() {
    }

    /**
     * コンストラクタ
     *
     * @param packageName  パッケージ名
     * @param resourceName リソース名
     * @param resource     リソース
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private DtoResource(@NonNull String packageName, @NonNull String resourceName, @NonNull String resource) {
        this.packageName = packageName;
        this.resourceName = resourceName;
        this.resource = resource;
    }

    /**
     * コピーコンストラクタ
     *
     * @param dtoResource DTOリソース
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private DtoResource(@NonNull DtoResource dtoResource) {
        this.packageName = dtoResource.getPackageName();
        this.resourceName = dtoResource.getResourceName();
        this.resource = dtoResource.getResource();
    }

    /**
     * 引数として指定された情報を基に {@link DtoResource} クラスの新しいインスタンスを生成し返却します。
     *
     * @param packageName  パッケージ名
     * @param resourceName リソース名
     * @param resource     リソース
     * @return {@link DtoResource} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static DtoResource of(@NonNull String packageName, @NonNull String resourceName, @NonNull String resource) {
        return new DtoResource(packageName, resourceName, resource);
    }

    /**
     * 引数として指定された {@code dtoResource} オブジェクトの情報を基に {@link DtoResource}
     * クラスの新しいインスタンスを生成し返却します。
     *
     * @param dtoResource DTOリソース
     * @return {@link DtoResource} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static DtoResource of(@NonNull DtoResource dtoResource) {
        return new DtoResource(dtoResource);
    }
}