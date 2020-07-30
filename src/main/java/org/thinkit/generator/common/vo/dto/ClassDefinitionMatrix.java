/**
 * Project Name : generator-commons<br>
 * File Name : ClassDefinitionMatrix.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/04/23<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.<br>
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.common.dto.dtogenerator;

import java.util.ArrayList;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 * クラスの定義情報群を管理するデータクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@Getter
@ToString
@EqualsAndHashCode
public final class ClassDefinitionMatrix {

    /**
     * クラス名定義情報
     */
    private ClassNameDefinition classNameDefinition = null;

    /**
     * クラス作成者情報
     */
    private ClassCreatorDefinition classCreatorDefinition = null;

    /**
     * クラス定義情報群
     */
    private List<ClassDefinition> classDefinitionList = null;

    /**
     * デフォルトコンストラクタ
     */
    @SuppressWarnings("unused")
    private ClassDefinitionMatrix() {
    }

    /**
     * コンストラクタ
     *
     * @param classNameDefinition    クラス名定義情報
     * @param classCreatorDefinition クラス作成者情報
     * @param classDefinitionList    クラス定義情報群
     */
    public ClassDefinitionMatrix(@NonNull ClassNameDefinition classNameDefinition,
            @NonNull ClassCreatorDefinition classCreatorDefinition,
            @NonNull List<ClassDefinition> classDefinitionList) {
        this.classNameDefinition = new ClassNameDefinition(classNameDefinition);
        this.classCreatorDefinition = new ClassCreatorDefinition(classCreatorDefinition);
        this.classDefinitionList = new ArrayList<>(classDefinitionList);
    }

    /**
     * コピーコンストラクタ
     *
     * @param classNameDefinition クラス定義情報群
     */
    public ClassDefinitionMatrix(@NonNull ClassDefinitionMatrix classDefinitionMatrix) {
        this.classNameDefinition = new ClassNameDefinition(classDefinitionMatrix.getClassNameDefinition());
        this.classCreatorDefinition = new ClassCreatorDefinition(classDefinitionMatrix.getClassCreatorDefinition());
        this.classDefinitionList = new ArrayList<>(classDefinitionMatrix.getClassDefinitionList());
    }
}
