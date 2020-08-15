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
 * DTO作成者の情報を管理するデータクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@Getter
@ToString
@EqualsAndHashCode
public final class DtoCreator implements Serializable {

    /**
     * シリアルバージョンUID
     */
    private static final long serialVersionUID = 2088194268330223984L;

    /**
     * 作成者
     */
    private String creator;

    /**
     * 作成日付
     */
    private String creationDate;

    /**
     * 更新日付
     */
    private String updateDate;

    /**
     * デフォルトコンストラクタ
     */
    private DtoCreator() {
    }

    /**
     * コンストラクタ
     *
     * @param creator      作成者
     * @param creationDate 作成日付
     * @param updateDate   更新日付
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private DtoCreator(@NonNull String creator, @NonNull String creationDate, @NonNull String updateDate) {
        this.creator = creator;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
    }

    /**
     * コピーコンストラクタ
     *
     * @param dtoCreator DTO作成者
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private DtoCreator(@NonNull DtoCreator dtoCreator) {
        this.creator = dtoCreator.getCreator();
        this.creationDate = dtoCreator.getCreationDate();
        this.updateDate = dtoCreator.getUpdateDate();
    }

    /**
     * 引数として渡された情報を基に {@link DtoCreator} クラスの新しいインスタンスを生成し返却します。
     *
     * @param creator      作成者
     * @param creationDate 作成日付
     * @param updateDate   更新日付
     * @return {@link DtoCreator} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static DtoCreator of(@NonNull String creator, @NonNull String creationDate, @NonNull String updateDate) {
        return new DtoCreator(creator, creationDate, updateDate);
    }

    /**
     * 引数として渡された {@code dtoCreator} オブジェクトの情報を基に {@link DtoCreator}
     * クラスの新しいインスタンスを生成し返却します。
     *
     * @param dtoCreator DTO作成者
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static DtoCreator of(@NonNull DtoCreator dtoCreator) {
        return new DtoCreator(dtoCreator);
    }
}
