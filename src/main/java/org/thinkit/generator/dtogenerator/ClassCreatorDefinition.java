/**
 * Project Name : Generator<br>
 * File Name : ClassCreatorDefinition.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/05/02<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.dtogenerator;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 * クラス作成者の情報を管理するデータクラスです。 当該クラスはイミュータブルです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@Getter
@ToString
@EqualsAndHashCode
public final class ClassCreatorDefinition {

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
    private ClassCreatorDefinition() {
    }

    /**
     * コンストラクタ
     *
     * @param creator      作成者
     * @param creationDate 作成日付
     * @param updateDate   更新日付
     */
    public ClassCreatorDefinition(@NonNull String creator, @NonNull String creationDate, @NonNull String updateDate) {
        this.creator = creator;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
    }

    /**
     * コピーコンストラクタ
     *
     * @param classCreatorDefinition クラス作成者定義情報
     */
    public ClassCreatorDefinition(@NonNull ClassCreatorDefinition classCreatorDefinition) {
        this.creator = classCreatorDefinition.getCreator();
        this.creationDate = classCreatorDefinition.getCreationDate();
        this.updateDate = classCreatorDefinition.getUpdateDate();
    }
}
