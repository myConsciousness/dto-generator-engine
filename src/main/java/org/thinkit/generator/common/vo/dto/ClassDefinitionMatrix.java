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
package org.thinkit.generator.common.vo.dto;

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
    private ClassName className = null;

    /**
     * クラス作成者情報
     */
    private ClassCreator classCreator = null;

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
     * @param className           クラス名定義情報
     * @param classCreator        クラス作成者情報
     * @param classDefinitionList クラス定義情報群
     */
    public ClassDefinitionMatrix(@NonNull ClassName className, @NonNull ClassCreator classCreator,
            @NonNull List<ClassDefinition> classDefinitionList) {
        this.className = new ClassName(className);
        this.classCreator = new ClassCreator(classCreator);
        this.classDefinitionList = new ArrayList<>(classDefinitionList);
    }

    /**
     * コピーコンストラクタ
     *
     * @param className クラス定義情報群
     */
    public ClassDefinitionMatrix(@NonNull ClassDefinitionMatrix classDefinitionMatrix) {
        this.className = new ClassName(classDefinitionMatrix.getClassName());
        this.classCreator = new ClassCreator(classDefinitionMatrix.getClassCreator());
        this.classDefinitionList = new ArrayList<>(classDefinitionMatrix.getClassDefinitionList());
    }
}
