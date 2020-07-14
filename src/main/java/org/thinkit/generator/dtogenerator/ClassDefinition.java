/**
 * Project Name : Generator<br>
 * File Name : ClassDefinition.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/05/05<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.dtogenerator;

import java.util.ArrayList;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

/**
 * クラス定義の情報を管理するデータクラスです。 当該クラスはイミュータブルです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
public final class ClassDefinition {

    /**
     * クラス名
     */
    private String className = "";

    /**
     * 説明
     */
    private String description = "";

    /**
     * クラス項目定義群
     */
    private List<ClassItemDefinition> classItemDefinitionList = new ArrayList<>();

    /**
     * デフォルトコンストラクタ
     */
    public ClassDefinition() {
    }

    /**
     * コピーコンストラクタ
     *
     * @param classDefinition クラス定義情報
     */
    public ClassDefinition(@NonNull ClassDefinition classDefinition) {
        this.className = classDefinition.getClassName();
        this.description = classDefinition.getDescription();
        this.classItemDefinitionList = new ArrayList<>(classDefinition.getClassItemDefinitionList());
    }
}
