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

import org.thinkit.generator.catalog.GeneratorDivision;
import org.thinkit.generator.dtogenerator.DtoGenerator;

/**
 * 各業務に応じた生成器を生成する抽象生成器ファクトリクラスの実装クラスです。
 * 
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
final class GeneratorFactory extends AbstractGeneratorFactory {

    /**
     * {@link GeneratorFactory}のシングルトンインスタンスを保持するインナークラスです。<br>
     * {@link GeneratorFactory}シングルトンインスタンスは初回参照時にメモリに読み込まれます。
     */
    private static class InstanceHolder {

        /**
         * シングルトンインスタンス
         */
        private static final GeneratorFactory ISNTANCE = new GeneratorFactory();
    }

    /**
     * デフォルトコンストラクタ
     */
    private GeneratorFactory() {
    }

    /**
     * 生成器ファクトリクラスのシングルトンインスタンスを返却します。
     * 
     * @return 生成器ファクトリクラスのシングルトンインスタンス
     */
    protected static AbstractGeneratorFactory getInstance() {
        return InstanceHolder.ISNTANCE;
    }

    @Override
    protected Generator createGenerator(GeneratorDivision generatorDivision, String filePath, String outputPath) {

        Generator generator = null;

        if (generatorDivision == GeneratorDivision.DTO_DEFINITOON) {
            generator = new DtoGenerator(filePath, outputPath);
        }

        return generator;
    }
}