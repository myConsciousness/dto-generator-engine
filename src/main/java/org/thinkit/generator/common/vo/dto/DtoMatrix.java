/**
 * Project Name : generator-commons<br>
 * File Name : DtoMatrix.java<br>
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
 * DTOマトリクスを管理するデータクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@Getter
@ToString
@EqualsAndHashCode
public final class DtoMatrix {

    /**
     * クラスメタ
     */
    private DtoMeta dtoMeta = null;

    /**
     * クラス作成者
     */
    private DtoCreator dtoCreator = null;

    /**
     * クラス定義情報群
     */
    private List<DtoDefinition> dtoDefinitionList = null;

    /**
     * デフォルトコンストラクタ
     */
    @SuppressWarnings("unused")
    private DtoMatrix() {
    }

    /**
     * コンストラクタ
     *
     * @param dtoMeta           クラスメタ
     * @param dtoCreator        クラス作成者
     * @param dtoDefinitionList クラス定義情報群
     */
    public DtoMatrix(@NonNull DtoMeta dtoMeta, @NonNull DtoCreator dtoCreator,
            @NonNull List<DtoDefinition> dtoDefinitionList) {
        this.dtoMeta = new DtoMeta(dtoMeta);
        this.dtoCreator = new DtoCreator(dtoCreator);
        this.dtoDefinitionList = new ArrayList<>(dtoDefinitionList);
    }

    /**
     * コピーコンストラクタ
     *
     * @param DtoMeta クラス定義情報群
     */
    public DtoMatrix(@NonNull DtoMatrix dtoMatrix) {
        this.dtoMeta = new DtoMeta(dtoMatrix.getDtoMeta());
        this.dtoCreator = new DtoCreator(dtoMatrix.getDtoCreator());
        this.dtoDefinitionList = new ArrayList<>(dtoMatrix.getDtoDefinitionList());
    }
}
