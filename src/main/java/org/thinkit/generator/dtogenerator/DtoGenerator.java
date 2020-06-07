/**
 * Project Name : Generator<br>
 * File Name : DtoGenerator.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/04/21<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.dtogenerator;

import org.thinkit.common.catalog.Extension;
import org.thinkit.common.util.FileHandler;
import org.thinkit.generator.AbstractGenerator;
import org.thinkit.generator.rule.dtogenerator.ClassDefinitionMatrixFormatter;
import org.thinkit.generator.rule.dtogenerator.ClassDefinitionMatrixManager;

import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.google.common.flogger.FluentLogger;

/**
 * DTO定義書を解析してDTOクラスを生成する処理を定義したクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
public final class DtoGenerator extends AbstractGenerator {

    /**
     * ログ出力オブジェクト
     */
    private static final FluentLogger logger = FluentLogger.forEnclosingClass();

    /**
     * コンストラクタ
     *
     * @param filePath Excelで定義されたDTO定義書までのファイルパス
     */
    public DtoGenerator(final String filePath) {
        super(filePath);
    }

    @Override
    protected boolean run() {
        logger.atInfo().log("START");

        final ClassDefinitionMatrixManager classDefinitionMatrixManager = new ClassDefinitionMatrixManager(
                super.getFilePath());

        if (!classDefinitionMatrixManager.execute()) {
            logger.atSevere().log("クラス定義情報群の生成処理が異常終了しました。");
            return false;
        }

        final ClassDefinitionMatrixFormatter classDefinitionMatrixFormatter = new ClassDefinitionMatrixFormatter(
                classDefinitionMatrixManager.getClassDefinitionMatrix());

        if (!classDefinitionMatrixFormatter.execute()) {
            logger.atSevere().log("クラス定義情報の整形処理が異常終了しました。");
            return false;
        }

        final Map<String, String> formattedResources = classDefinitionMatrixFormatter.getFormattedResources();

        final FileHandler fileHandler = new FileHandler("C:\\Users\\yourd\\Desktop");
        final Set<Entry<String, String>> entrySet = formattedResources.entrySet();

        for (Entry<String, String> entry : entrySet) {
            if (!fileHandler.write(entry.getKey(), Extension.java(), entry.getValue())) {
                logger.atSevere().log("リソース情報の書き込み処理が異常終了しました。");
                return false;
            }
        }

        logger.atInfo().log("END");
        return true;
    }
}
