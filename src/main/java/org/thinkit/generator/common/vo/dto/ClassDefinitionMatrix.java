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
     * クラスメタ
     */
    private ClassMeta classMeta = null;

    /**
     * クラス作成者
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
     * @param classMeta           クラスメタ
     * @param classCreator        クラス作成者
     * @param classDefinitionList クラス定義情報群
     */
    public ClassDefinitionMatrix(@NonNull ClassMeta classMeta, @NonNull ClassCreator classCreator,
            @NonNull List<ClassDefinition> classDefinitionList) {
        this.classMeta = new ClassMeta(classMeta);
        this.classCreator = new ClassCreator(classCreator);
        this.classDefinitionList = new ArrayList<>(classDefinitionList);
    }

    /**
     * コピーコンストラクタ
     *
     * @param classMeta クラス定義情報群
     */
    public ClassDefinitionMatrix(@NonNull ClassDefinitionMatrix classDefinitionMatrix) {
        this.classMeta = new ClassMeta(classDefinitionMatrix.getClassMeta());
        this.classCreator = new ClassCreator(classDefinitionMatrix.getClassCreator());
        this.classDefinitionList = new ArrayList<>(classDefinitionMatrix.getClassDefinitionList());
    }
}
