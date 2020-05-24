/**
 * Project Name :Generator<br>
 * File Name : ClassDefinitionMatrixManager.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/04/23<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.rule.dtogenerator;

import org.apache.commons.lang3.StringUtils;
import org.thinkit.common.rule.AbstractRule;
import org.thinkit.common.util.ExcelHandler;
import org.thinkit.generator.dtogenerator.ClassDefinitionMatrix;
import org.thinkit.generator.rule.Sheet;

import com.google.common.flogger.FluentLogger;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Excelに記述された定義書シートからクラス定義マトリクス情報を抽出する処理を行うルールです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@ToString
@EqualsAndHashCode(callSuper = false)
public final class ClassDefinitionMatrixManager extends AbstractRule {

    /**
     * ログ出力オブジェクト
     */
    private static final FluentLogger logger = FluentLogger.forEnclosingClass();

    /**
     * ファイルパス
     */
    @Getter(AccessLevel.PRIVATE)
    private String filePath = "";

    /**
     * クラス定義情報群
     */
    @Getter
    private ClassDefinitionMatrix classDefinitionMatrix = null;

    /**
     * デフォルトコンストラクタ
     */
    @SuppressWarnings("unused")
    private ClassDefinitionMatrixManager() {
    }

    /**
     * コンストラクタ
     *
     * @param filePath DTO定義書のファイルパス
     * @exception IllegalArgumentException ファイルパスがnullまたは空文字列の場合
     */
    public ClassDefinitionMatrixManager(String filePath) {
        logger.atInfo().log("ファイルパス = (%s)", filePath);

        if (StringUtils.isEmpty(filePath)) {
            logger.atInfo().log("初期化処理でエラーが発生しました。");
            logger.atInfo().log("ファイルパスがnullまたは空文字列です。");
            throw new IllegalArgumentException("wrong parameter was given. File path is required.");
        }

        this.filePath = filePath;
    }

    /**
     * シート名定数
     */
    private enum SheetName implements Sheet {
        定義書;

        @Override
        public String getString() {
            return this.name();
        }
    }

    @Override
    public boolean execute() {
        logger.atInfo().log("START");

        final ExcelHandler excelHandler = new ExcelHandler.Builder().fromFile(this.getFilePath()).build();
        final ExcelHandler.SheetHandler sheetHandler = excelHandler.sheet(SheetName.定義書.name());

        final ClassCreatorDefinitionManager classCreatorDefinitionManager = new ClassCreatorDefinitionManager(
                sheetHandler);

        if (!classCreatorDefinitionManager.execute()) {
            logger.atSevere().log("クラス作成者情報の取得処理が異常終了しました。");
            return false;
        }

        final ClassNameDefinitionManager classNameDefinitionManager = new ClassNameDefinitionManager(sheetHandler);

        if (!classNameDefinitionManager.execute()) {
            logger.atSevere().log("クラス名定義情報の取得処理が異常終了しました。");
            return false;
        }

        final ClassDefinitionManager classDefinitionManager = new ClassDefinitionManager(sheetHandler);

        if (!classDefinitionManager.execute()) {
            logger.atSevere().log("クラス定義情報の取得処理が異常終了しました。");
            return false;
        }

        final ClassDefinitionMatrix classDefinitionMatrix = new ClassDefinitionMatrix(
                classNameDefinitionManager.getClassNameDefinition(),
                classCreatorDefinitionManager.getClassCreatorDefinition(),
                classDefinitionManager.getClassDefinitionList());

        this.classDefinitionMatrix = classDefinitionMatrix;

        logger.atInfo().log("クラス定義情報マトリクス = (%s)", classDefinitionMatrix);
        logger.atInfo().log("END");
        return true;
    }
}
