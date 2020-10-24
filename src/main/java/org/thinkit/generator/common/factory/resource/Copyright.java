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

import java.time.LocalDate;

import org.apache.commons.lang3.StringUtils;
import org.thinkit.common.exception.LogicException;
import org.thinkit.generator.common.factory.Component;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 * プログラムリソースにおける著作権を抽象化した抽象クラスです。<br>
 * この抽象クラスでは著作権定義に必要な情報を保持します。<br>
 * この抽象クラスを継承する具象クラスは必ず {@link Component#createResource()} を実装してください。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 *
 * @see Component
 * @see Component#createResource()
 */
@ToString
@EqualsAndHashCode
public abstract class Copyright implements Component {

    /**
     * プロジェクト名
     */
    @NonNull
    @Getter(AccessLevel.PROTECTED)
    private String projectName = "";

    /**
     * ファイル名
     */
    @NonNull
    @Getter(AccessLevel.PROTECTED)
    private String fileName;

    /**
     * エンコード
     */
    @NonNull
    @Getter(AccessLevel.PROTECTED)
    private String encoding;

    /**
     * 作成者
     */
    @NonNull
    @Getter(AccessLevel.PROTECTED)
    private String creator;

    /**
     * 作成日付
     */
    @NonNull
    @Getter(AccessLevel.PROTECTED)
    private String creationDate;

    /**
     * デフォルトコンストラクタ
     */
    protected Copyright() {
    }

    /**
     * コンストラクタ
     *
     * @param creator 作成者
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    protected Copyright(@NonNull String creator) {
        this.creator = creator;
    }

    /**
     * コンストラクタ
     *
     * @param projectName  プロジェクト名
     * @param fileName     ファイル名
     * @param encoding     エンコード
     * @param creator      作成者
     * @param creationDate 作成日付
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    protected Copyright(@NonNull String projectName, @NonNull String fileName, @NonNull String encoding,
            @NonNull String creator, @NonNull String creationDate) {
        this.projectName = projectName;
        this.fileName = fileName;
        this.encoding = encoding;
        this.creator = creator;
        this.creationDate = creationDate;
    }

    /**
     * {@link #creationDate} から上4桁（yyyy）を抽出して返却します。
     * <p>
     * {@link #creationDate} から取得した文字列が {@code null} または空文字列の場合はローカル日付から取得した年を
     * {@code yyyy} 形式で返却します。
     *
     * @return {@link #creationDate} から抽出した上4桁（yyyy）、または、 {@link #creationDate}
     *         から取得した文字列が {@code null} または空文字列の場合はローカル日付から取得した年
     *
     * @throws LogicException {@link #creationDate} から取得した文字列が {@code null}
     *                        または空文字列ではなく、かつ文字列長が4桁未満の場合
     */
    protected String getCreationYear() {

        final String creationDate = this.getCreationDate();

        if (StringUtils.isEmpty(creationDate)) {
            return String.valueOf(LocalDate.now().getYear());
        }

        if (creationDate.length() < 4) {
            throw new LogicException(String
                    .format("The creation date must be in at least the yyyyMMdd format. %s is set.", creationDate));
        }

        return this.getCreationDate().substring(0, 4);
    }
}