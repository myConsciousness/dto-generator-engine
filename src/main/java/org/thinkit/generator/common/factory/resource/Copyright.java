/**
 * Project Name : generator-commons<br>
 * File Name : Copyright.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/06/07<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.common.factory.resource;

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
    private String fileName = "";

    /**
     * エンコード
     */
    @NonNull
    @Getter(AccessLevel.PROTECTED)
    private String encoding = "";

    /**
     * 作成者
     */
    @NonNull
    @Getter(AccessLevel.PROTECTED)
    private String creator = "";

    /**
     * 作成日付
     */
    @NonNull
    @Getter(AccessLevel.PROTECTED)
    private String creationDate = "";

    /**
     * デフォルトコンストラクタ
     */
    @SuppressWarnings("unused")
    private Copyright() {
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
    protected Copyright(String projectName, String fileName, String encoding, String creator, String creationDate) {
        this.projectName = projectName;
        this.fileName = fileName;
        this.encoding = encoding;
        this.creator = creator;
        this.creationDate = creationDate;
    }

    /**
     * {@link #creationDate}から上4桁（yyyy）を抽出して返却します。
     *
     * @return {@link #creationDate} から抽出した上4桁（yyyy）
     * @throws LogicException {@link #creationDate} が4桁未満の場合
     */
    protected String getCreationYear() {

        final String creationDate = this.getCreationDate();

        if (creationDate.length() < 4) {
            throw new LogicException(String
                    .format("The creation date must be in at least the yyyyMMdd format. %s is set.", creationDate));
        }

        return this.getCreationDate().substring(0, 4);
    }
}