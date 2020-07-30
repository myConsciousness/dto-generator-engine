/**
 * Project Name : generator-commons<br>
 * File Name : ClassCreator.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/05/02<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.common.vo.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 * クラス作成者の情報を管理するデータクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@Getter
@ToString
@EqualsAndHashCode
public final class ClassCreator {

    /**
     * 作成者
     */
    private String creator = "";

    /**
     * 作成日付
     */
    private String creationDate = "";

    /**
     * 更新日付
     */
    private String updateDate = "";

    /**
     * デフォルトコンストラクタ
     */
    @SuppressWarnings("unused")
    private ClassCreator() {
    }

    /**
     * コンストラクタ
     *
     * @param creator      作成者
     * @param creationDate 作成日付
     * @param updateDate   更新日付
     */
    public ClassCreator(@NonNull String creator, @NonNull String creationDate, @NonNull String updateDate) {
        this.creator = creator;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
    }

    /**
     * コピーコンストラクタ
     *
     * @param classCreator クラス作成者
     */
    public ClassCreator(@NonNull ClassCreator classCreator) {
        this.creator = classCreator.getCreator();
        this.creationDate = classCreator.getCreationDate();
        this.updateDate = classCreator.getUpdateDate();
    }
}
