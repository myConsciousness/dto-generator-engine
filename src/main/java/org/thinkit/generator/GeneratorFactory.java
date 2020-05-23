/**
 * Project Name : Generator<br>
 * File Name : GeneratorFactory.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/05/23<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator;

import java.util.Objects;

import org.thinkit.generator.catalog.GeneratorDivision;
import org.thinkit.generator.dtogenerator.DtoGenerator;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;

/**
 * 各業務に応じた生成器を生成する抽象生成器ファクトリクラスの実装クラスです。
 * 
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
public class GeneratorFactory extends AbstractGeneratorFactory {

    /**
     * ファイルパス
     */
    @NonNull
    @Getter(AccessLevel.PRIVATE)
    private String filePath = "";

    /**
     * デフォルトコンストラクタ
     */
    @SuppressWarnings("unused")
    private GeneratorFactory() {
    }

    /**
     * コンストラクタ
     * 
     * @param filePath ファイルパス
     */
    protected GeneratorFactory(final String filePath) {
        this.filePath = filePath;
    }

    @Override
    protected Generator createGenerator(GeneratorDivision generatorDivision) {
        Objects.requireNonNull(generatorDivision, "GeneratorDivision must not be null.");

        Generator generator = null;

        if (generatorDivision == GeneratorDivision.DTO_DEFINITOON) {
            generator = new DtoGenerator(this.getFilePath());
        }

        return generator;
    }
}